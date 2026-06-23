package com.jio.processflow.controller;

import com.jio.processflow.client.LiteLLMClient;
import com.jio.processflow.model.FlowCharacteristic;
import com.jio.processflow.model.ProcessFlow;
import com.jio.processflow.service.ProcessFlowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tmf-api/processFlowManagement/v4")
public class ProcessFlowController {

    private final ProcessFlowService service;
    private final LiteLLMClient liteLLMClient;

    public ProcessFlowController(ProcessFlowService service, LiteLLMClient liteLLMClient) {
        this.service = service;
        this.liteLLMClient = liteLLMClient;
    }

    /**
     * Start a new process flow.
     *
     * Body:
     * {
     *   "name": "Onboard Rohan Sharma",          (optional)
     *   "processFlowDefinition": {
     *     "id": "CustomerOnboarding"             (required)
     *            | "ExistingCustomerOrder"
     *            | "FastCheckout"
     *   },
     *   "characteristic": [
     *     { "name": "firstName",          "value": "Rohan"         },
     *     { "name": "lastName",           "value": "Sharma"        },
     *     { "name": "email",              "value": "r@jio.com"     },
     *     { "name": "phone",              "value": "+91-9876543210"},
     *     { "name": "paymentMethodType",  "value": "UPI"           },
     *     { "name": "paymentMethodDetail","value": "rohan@upi"     },
     *     { "name": "productOfferingId",  "value": "offer-abc-123" }  (FastCheckout / ExistingCustomerOrder)
     *     { "name": "customerId",         "value": "cust-xxx"      }  (ExistingCustomerOrder only)
     *   ]
     * }
     */
    @PostMapping("/processFlow")
    public ResponseEntity<ProcessFlow> start(@RequestBody Map<String, Object> body) {
        @SuppressWarnings("unchecked")
        Map<String, Object> defMap = (Map<String, Object>) body.get("processFlowDefinition");
        if (defMap == null || defMap.get("id") == null) {
            return ResponseEntity.badRequest().build();
        }

        String definitionId = (String) defMap.get("id");
        String name = (String) body.get("name");

        @SuppressWarnings("unchecked")
        List<Map<String, String>> rawChars = (List<Map<String, String>>) body.get("characteristic");
        List<FlowCharacteristic> characteristics = rawChars == null ? List.of() :
            rawChars.stream()
                .map(m -> new FlowCharacteristic(m.get("name"), m.get("value")))
                .toList();

        ProcessFlow flow = service.start(definitionId, name, characteristics);
        return ResponseEntity.status(HttpStatus.CREATED).body(flow);
    }

    /** List all process flows. Optionally filter by definitionId. */
    @GetMapping("/processFlow")
    public ResponseEntity<List<ProcessFlow>> list(
            @RequestParam(required = false) String definitionId) {
        if (definitionId != null) {
            return ResponseEntity.ok(service.findByDefinition(definitionId));
        }
        return ResponseEntity.ok(service.findAll());
    }

    /** Get a single process flow by id. */
    @GetMapping("/processFlow/{id}")
    public ResponseEntity<ProcessFlow> get(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * AI-powered subscription plan recommendation.
     * Body: { "customerId": "123", "monthlyBudget": "500",
     *         "dataUsageGb": "50", "preferences": "streaming, gaming" }
     */
    @PostMapping("/recommend")
    public ResponseEntity<Map<String, String>> recommend(@RequestBody Map<String, String> body) {
        String customerId    = body.getOrDefault("customerId", "unknown");
        String budget        = body.getOrDefault("monthlyBudget", "not specified");
        String dataUsage     = body.getOrDefault("dataUsageGb", "not specified");
        String preferences   = body.getOrDefault("preferences", "general use");

        String systemPrompt = """
                You are a Jio subscription advisor. Based on the customer's profile, recommend the best Jio plan.
                Jio plans available:
                - Jio Basic (Rs 199/mo): 1.5 GB/day, unlimited calls, 28 days
                - Jio Smart (Rs 399/mo): 2 GB/day, unlimited calls, OTT bundle, 28 days
                - Jio Fiber Silver (Rs 499/mo): 100 Mbps broadband, 100 GB data, OTT apps
                - Jio Fiber Gold (Rs 999/mo): 300 Mbps broadband, unlimited data, all OTT apps
                - Jio AirFiber (Rs 599/mo): 5G wireless home broadband, 150 Mbps, unlimited
                Respond with: recommended plan name, price, reason (2 sentences max), and one upsell tip.
                """;
        String userMessage = "Customer ID: " + customerId
                + "\nMonthly budget: Rs " + budget
                + "\nData usage: " + dataUsage + " GB/month"
                + "\nPreferences: " + preferences;

        String recommendation = liteLLMClient.chat(systemPrompt, userMessage, "subscription-advisor");
        return ResponseEntity.ok(Map.of("customerId", customerId, "recommendation", recommendation));
    }

    /**
     * Contextual AI chat assistant.
     * Body: { "message": "...", "step": "3", "context": "Customer: Saanvi, plan: Jio Postpaid Plus" }
     */
    @PostMapping("/chat")
    public ResponseEntity<Map<String, String>> chat(@RequestBody Map<String, String> body) {
        String message = body.getOrDefault("message", "");
        String step    = body.getOrDefault("step", "0");
        String context = body.getOrDefault("context", "");

        if (message.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("reply", "No message provided."));
        }

        String systemPrompt = buildChatSystemPrompt(step, context);
        String reply = liteLLMClient.chat(systemPrompt, message, "support-agent");
        return ResponseEntity.ok(Map.of("reply", reply));
    }

    private String buildChatSystemPrompt(String step, String context) {
        String stepContext = switch (step) {
            case "1" -> "The customer is signing up for a new Jio account or logging into an existing one.";
            case "2" -> "The customer is creating a billing account linked to their profile.";
            case "3" -> "The customer is browsing and selecting a subscription plan from the catalog.";
            case "4" -> "The customer is placing a product order for their chosen plan.";
            case "5" -> "The customer is adding a payment method (UPI, credit card, or debit card).";
            case "6" -> "The customer is completing their payment through Razorpay.";
            case "7" -> "The customer's payment is done and their subscription is now active.";
            default  -> "The customer is navigating the Jio Subscription Portal.";
        };
        String extra = context.isBlank() ? "" : "\nSession context: " + context;
        return """
                You are a helpful Jio customer support assistant embedded in the Jio Subscription Portal.
                Answer questions about Jio plans, payments, account setup, and the subscription process.
                Be concise (2–3 sentences), friendly, and specific to Jio services.
                Current step: """ + stepContext + extra;
    }

    /**
     * List available process flow definitions.
     * Returns the three built-in definitions with their required input fields.
     */
    @GetMapping("/processFlowDefinition")
    public ResponseEntity<List<Map<String, Object>>> listDefinitions() {
        return ResponseEntity.ok(List.of(
            Map.of(
                "id",          "CustomerOnboarding",
                "description", "Registers a new person as a Jio customer with billing account and payment method. No order placed.",
                "tasks",       4,
                "requiredInput", List.of("firstName","lastName","email","phone","paymentMethodType","paymentMethodDetail")
            ),
            Map.of(
                "id",          "ExistingCustomerOrder",
                "description", "Places and completes an order for an already-onboarded customer. Provisions product and usage tracker.",
                "tasks",       6,
                "requiredInput", List.of("customerId","productOfferingId")
            ),
            Map.of(
                "id",          "FastCheckout",
                "description", "Onboards a brand-new customer AND immediately places, completes the order, and activates the plan — all in one call.",
                "tasks",       9,
                "requiredInput", List.of("firstName","lastName","email","phone","paymentMethodType","paymentMethodDetail","productOfferingId")
            )
        ));
    }
}
