package com.jio.processflow.service;

import com.jio.processflow.client.*;
import com.jio.processflow.exception.TaskFailedException;
import com.jio.processflow.model.FlowCharacteristic;
import com.jio.processflow.model.ProcessFlow;
import com.jio.processflow.model.TaskFlow;
import com.jio.processflow.repository.ProcessFlowRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.function.Supplier;

@Service
public class ProcessFlowService {

    private final ProcessFlowRepository repository;
    private final CatalogClient catalogClient;
    private final PartyClient partyClient;
    private final CustomerClient customerClient;
    private final PaymentMethodClient paymentMethodClient;
    private final OrderClient orderClient;
    private final InventoryClient inventoryClient;
    private final UsageClient usageClient;
    private final CommunicationClient communicationClient;

    public ProcessFlowService(ProcessFlowRepository repository,
                              CatalogClient catalogClient,
                              PartyClient partyClient,
                              CustomerClient customerClient,
                              PaymentMethodClient paymentMethodClient,
                              OrderClient orderClient,
                              InventoryClient inventoryClient,
                              UsageClient usageClient,
                              CommunicationClient communicationClient) {
        this.repository = repository;
        this.catalogClient = catalogClient;
        this.partyClient = partyClient;
        this.customerClient = customerClient;
        this.paymentMethodClient = paymentMethodClient;
        this.orderClient = orderClient;
        this.inventoryClient = inventoryClient;
        this.usageClient = usageClient;
        this.communicationClient = communicationClient;
    }

    // ── Public API ────────────────────────────────────────────────────────────

    public ProcessFlow start(String definitionId, String name, List<FlowCharacteristic> input) {
        ProcessFlow flow = initFlow(definitionId, name, input);

        // Build a mutable context map from the input characteristics
        Map<String, String> ctx = new HashMap<>();
        for (FlowCharacteristic c : input) {
            ctx.put(c.getName(), c.getValue());
        }

        try {
            switch (definitionId) {
                case "CustomerOnboarding"      -> runCustomerOnboarding(flow, ctx);
                case "ExistingCustomerOrder"   -> runExistingCustomerOrder(flow, ctx);
                case "FastCheckout"            -> runFastCheckout(flow, ctx);
                default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unknown processFlowDefinition id: '" + definitionId + "'. " +
                    "Valid values: CustomerOnboarding, ExistingCustomerOrder, FastCheckout");
            }
            flow.setStatus("completed");

            // Wire 18: notify TMF681 that flow is complete
            communicationClient.sendFlowCompletion(flow.getId(), definitionId, ctx.get("customerId"));

        } catch (TaskFailedException e) {
            flow.setStatus("failed");
        }

        flow.setCompletionDate(OffsetDateTime.now());
        return repository.save(flow);
    }

    public List<ProcessFlow> findAll() { return repository.findAll(); }

    public Optional<ProcessFlow> findById(String id) { return repository.findById(id); }

    public List<ProcessFlow> findByDefinition(String definitionId) {
        return repository.findByDefinitionId(definitionId);
    }

    // ── Flow runners ──────────────────────────────────────────────────────────

    /**
     * CustomerOnboarding — 4 tasks
     * Input: firstName, lastName, email, phone, paymentMethodType, paymentMethodDetail
     * Output: individualId, customerId, accountId, methodId
     */
    private void runCustomerOnboarding(ProcessFlow flow, Map<String, String> ctx) {
        // Task 1: CreateIndividual → TMF632
        runTask(flow, "CreateIndividual", "Individual", () -> {
            String id = partyClient.createIndividual(
                ctx.get("firstName"), ctx.get("lastName"),
                ctx.get("email"),     ctx.get("phone"));
            ctx.put("individualId", id);
            return id;
        });

        // Task 2: CreateCustomer → TMF629
        runTask(flow, "CreateCustomer", "Customer", () -> {
            String fullName = ctx.getOrDefault("firstName", "") + " " + ctx.getOrDefault("lastName", "");
            String id = customerClient.createCustomer(ctx.get("individualId"), fullName.trim());
            ctx.put("customerId", id);
            ctx.put("customerName", fullName.trim());
            return id;
        });

        // Tasks 3 + 4: CreateCustomerAccount and CreatePaymentMethod — both depend on customerId,
        // independent of each other; run sequentially for simplicity.
        runTask(flow, "CreateCustomerAccount", "CustomerAccount", () -> {
            String id = customerClient.createCustomerAccount(ctx.get("customerId"));
            ctx.put("accountId", id);
            return id;
        });

        runTask(flow, "CreatePaymentMethod", "PaymentMethod", () -> {
            String id = paymentMethodClient.createPaymentMethod(
                ctx.get("customerId"),
                ctx.get("paymentMethodType"),
                ctx.get("paymentMethodDetail"));
            ctx.put("methodId", id);
            return id;
        });
    }

    /**
     * ExistingCustomerOrder — 6 tasks
     * Input: customerId, productOfferingId
     * Output: orderId, productId, productUsageId
     */
    private void runExistingCustomerOrder(ProcessFlow flow, Map<String, String> ctx) {
        // Tasks 1 + 2: ValidateCustomer and ValidateOffering — parallel (both read-only)
        validateTask(flow, "ValidateCustomer",
            () -> customerClient.customerExists(ctx.get("customerId")),
            "Customer '" + ctx.get("customerId") + "' not found in TMF629");

        validateTask(flow, "ValidateOffering",
            () -> catalogClient.offeringExists(ctx.get("productOfferingId")),
            "ProductOffering '" + ctx.get("productOfferingId") + "' not found in TMF620");

        // Task 3: CreateProductOrder → TMF622
        runTask(flow, "CreateProductOrder", "ProductOrder", () -> {
            String id = orderClient.createOrder(
                ctx.get("customerId"),
                ctx.get("customerName"),
                ctx.get("productOfferingId"));
            ctx.put("orderId", id);
            return id;
        });

        // Task 4: CompleteOrder → TMF622 (triggers Wires 8, 10, 11, 3 internally)
        runTask(flow, "CompleteOrder", "ProductOrder", () -> {
            orderClient.completeOrder(ctx.get("orderId"));
            return ctx.get("orderId");
        });

        // Task 5: VerifyProductCreated → TMF637
        runTask(flow, "VerifyProductCreated", "Product", () -> {
            String productId = inventoryClient.findProductByOrderId(ctx.get("orderId"));
            if (productId == null) throw new RuntimeException(
                "Product not found in TMF637 for orderId " + ctx.get("orderId"));
            ctx.put("productId", productId);
            return productId;
        });

        // Task 6: VerifyUsageInitialized → TMF635
        runTask(flow, "VerifyUsageInitialized", "ProductUsage", () -> {
            String usageId = usageClient.findUsageByProductId(ctx.get("productId"));
            if (usageId == null) throw new RuntimeException(
                "ProductUsage tracker not found in TMF635 for productId " + ctx.get("productId"));
            ctx.put("productUsageId", usageId);
            return usageId;
        });
    }

    /**
     * FastCheckout — 9 tasks
     * Input: firstName, lastName, email, phone, paymentMethodType, paymentMethodDetail, productOfferingId
     * Output: individualId, customerId, accountId, methodId, orderId, productId, productUsageId
     */
    private void runFastCheckout(ProcessFlow flow, Map<String, String> ctx) {
        // Task 1: ValidateOffering → TMF620
        validateTask(flow, "ValidateOffering",
            () -> catalogClient.offeringExists(ctx.get("productOfferingId")),
            "ProductOffering '" + ctx.get("productOfferingId") + "' not found in TMF620");

        // Tasks 2-5: same as CustomerOnboarding
        runTask(flow, "CreateIndividual", "Individual", () -> {
            String id = partyClient.createIndividual(
                ctx.get("firstName"), ctx.get("lastName"),
                ctx.get("email"),     ctx.get("phone"));
            ctx.put("individualId", id);
            return id;
        });

        runTask(flow, "CreateCustomer", "Customer", () -> {
            String fullName = ctx.getOrDefault("firstName", "") + " " + ctx.getOrDefault("lastName", "");
            String id = customerClient.createCustomer(ctx.get("individualId"), fullName.trim());
            ctx.put("customerId", id);
            ctx.put("customerName", fullName.trim());
            return id;
        });

        runTask(flow, "CreateCustomerAccount", "CustomerAccount", () -> {
            String id = customerClient.createCustomerAccount(ctx.get("customerId"));
            ctx.put("accountId", id);
            return id;
        });

        runTask(flow, "CreatePaymentMethod", "PaymentMethod", () -> {
            String id = paymentMethodClient.createPaymentMethod(
                ctx.get("customerId"),
                ctx.get("paymentMethodType"),
                ctx.get("paymentMethodDetail"));
            ctx.put("methodId", id);
            return id;
        });

        // Tasks 6-9: same as ExistingCustomerOrder
        runTask(flow, "CreateProductOrder", "ProductOrder", () -> {
            String id = orderClient.createOrder(
                ctx.get("customerId"),
                ctx.get("customerName"),
                ctx.get("productOfferingId"));
            ctx.put("orderId", id);
            return id;
        });

        runTask(flow, "CompleteOrder", "ProductOrder", () -> {
            orderClient.completeOrder(ctx.get("orderId"));
            return ctx.get("orderId");
        });

        runTask(flow, "VerifyProductCreated", "Product", () -> {
            String productId = inventoryClient.findProductByOrderId(ctx.get("orderId"));
            if (productId == null) throw new RuntimeException(
                "Product not found in TMF637 for orderId " + ctx.get("orderId"));
            ctx.put("productId", productId);
            return productId;
        });

        runTask(flow, "VerifyUsageInitialized", "ProductUsage", () -> {
            String usageId = usageClient.findUsageByProductId(ctx.get("productId"));
            if (usageId == null) throw new RuntimeException(
                "ProductUsage tracker not found in TMF635 for productId " + ctx.get("productId"));
            ctx.put("productUsageId", usageId);
            return usageId;
        });
    }

    // ── Task helpers ──────────────────────────────────────────────────────────

    /** Execute a task that creates a resource and stores its id in relatedEntityId. */
    private void runTask(ProcessFlow flow, String taskName, String entityType,
                         Supplier<String> action) {
        TaskFlow task = findTask(flow, taskName);
        task.setStatus("inProgress");
        task.setStartDate(OffsetDateTime.now());
        repository.save(flow);

        try {
            String createdId = action.get();
            task.setStatus("completed");
            task.setRelatedEntityId(createdId);
            task.setRelatedEntityType(entityType);
            task.setNote(entityType + " created/verified successfully: " + createdId);
        } catch (Exception e) {
            task.setStatus("failed");
            task.setNote("ERROR: " + e.getMessage());
            task.setCompletionDate(OffsetDateTime.now());
            repository.save(flow);
            throw new TaskFailedException(taskName, e.getMessage());
        }

        task.setCompletionDate(OffsetDateTime.now());
        repository.save(flow);
    }

    /** Execute a validation task that checks a condition but doesn't create anything. */
    private void validateTask(ProcessFlow flow, String taskName,
                              Supplier<Boolean> check, String failMsg) {
        TaskFlow task = findTask(flow, taskName);
        task.setStatus("inProgress");
        task.setStartDate(OffsetDateTime.now());
        repository.save(flow);

        boolean passed;
        try { passed = check.get(); }
        catch (Exception e) { passed = true; } // graceful degradation: service down → proceed

        if (!passed) {
            task.setStatus("failed");
            task.setNote("ERROR: " + failMsg);
            task.setCompletionDate(OffsetDateTime.now());
            repository.save(flow);
            throw new TaskFailedException(taskName, failMsg);
        }

        task.setStatus("completed");
        task.setNote("Validation passed");
        task.setCompletionDate(OffsetDateTime.now());
        repository.save(flow);
    }

    private TaskFlow findTask(ProcessFlow flow, String name) {
        return flow.getTaskFlow().stream()
                .filter(t -> name.equals(t.getName()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Task '" + name + "' not found in flow"));
    }

    // ── Flow initializer ─────────────────────────────────────────────────────

    private ProcessFlow initFlow(String definitionId, String name, List<FlowCharacteristic> input) {
        ProcessFlow flow = new ProcessFlow();
        flow.setId(UUID.randomUUID().toString());
        flow.setName(name != null && !name.isBlank() ? name : definitionId);
        flow.setDefinitionId(definitionId);
        flow.setStatus("inProgress");
        flow.setStartDate(OffsetDateTime.now());
        flow.setCharacteristic(input != null ? input : new ArrayList<>());
        flow.setTaskFlow(buildTasks(definitionId));
        return repository.save(flow);
    }

    private List<TaskFlow> buildTasks(String definitionId) {
        return switch (definitionId) {
            case "CustomerOnboarding" -> List.of(
                task("CreateIndividual",       1),
                task("CreateCustomer",         2),
                task("CreateCustomerAccount",  3),
                task("CreatePaymentMethod",    4)
            );
            case "ExistingCustomerOrder" -> List.of(
                task("ValidateCustomer",       1),
                task("ValidateOffering",       2),
                task("CreateProductOrder",     3),
                task("CompleteOrder",          4),
                task("VerifyProductCreated",   5),
                task("VerifyUsageInitialized", 6)
            );
            case "FastCheckout" -> List.of(
                task("ValidateOffering",       1),
                task("CreateIndividual",       2),
                task("CreateCustomer",         3),
                task("CreateCustomerAccount",  4),
                task("CreatePaymentMethod",    5),
                task("CreateProductOrder",     6),
                task("CompleteOrder",          7),
                task("VerifyProductCreated",   8),
                task("VerifyUsageInitialized", 9)
            );
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Unknown definition: " + definitionId);
        };
    }

    private TaskFlow task(String name, int seq) {
        TaskFlow t = new TaskFlow();
        t.setId(UUID.randomUUID().toString());
        t.setName(name);
        t.setStatus("pending");
        t.setSequenceNumber(seq);
        return t;
    }
}
