package com.jio.payment.service;

import com.jio.payment.client.CommunicationClient;
import com.jio.payment.client.CustomerClient;
import com.jio.payment.client.InventoryClient;
import com.jio.payment.client.PaymentMethodClient;
import com.jio.payment.model.Payment;
import com.jio.payment.model.PaymentInitiateRequest;
import com.jio.payment.model.PaymentInitiateResponse;
import com.jio.payment.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentRepository repository;
    private final CustomerClient customerClient;
    private final InventoryClient inventoryClient;
    private final PaymentMethodClient paymentMethodClient;
    private final CommunicationClient communicationClient;
    private final RazorpayService razorpayService;

    public PaymentService(PaymentRepository repository,
                          CustomerClient customerClient,
                          InventoryClient inventoryClient,
                          PaymentMethodClient paymentMethodClient,
                          CommunicationClient communicationClient,
                          RazorpayService razorpayService) {
        this.repository = repository;
        this.customerClient = customerClient;
        this.inventoryClient = inventoryClient;
        this.paymentMethodClient = paymentMethodClient;
        this.communicationClient = communicationClient;
        this.razorpayService = razorpayService;
    }

    public Payment create(Payment payment) {
        // Wire 4a: validate customer exists in TMF629
        if (!customerClient.customerExists(payment.getCustomerId())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                "customerId '" + payment.getCustomerId() +
                "' does not exist in Customer Management (TMF629)");
        }

        // Wire 4b: validate customerAccount exists in TMF629
        if (!customerClient.customerAccountExists(payment.getCustomerAccountId())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                "customerAccountId '" + payment.getCustomerAccountId() +
                "' does not exist in Customer Management (TMF629)");
        }

        // Wire 4c: if a productId is given, validate it exists in TMF637
        if (payment.getProductId() != null && !payment.getProductId().isBlank()) {
            if (!inventoryClient.productExists(payment.getProductId())) {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "productId '" + payment.getProductId() +
                    "' does not exist in Product Inventory (TMF637)");
            }
        }

        // Wire 4d: if a paymentMethodId is given, validate it exists in TMF671
        if (payment.getPaymentMethodId() != null && !payment.getPaymentMethodId().isBlank()) {
            if (!paymentMethodClient.paymentMethodExists(payment.getPaymentMethodId())) {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "paymentMethodId '" + payment.getPaymentMethodId() +
                    "' does not exist in Payment Method Management (TMF671)");
            }
        }

        payment.setCreatedAt(OffsetDateTime.now());
        if (payment.getStatus() == null || payment.getStatus().isBlank()) {
            payment.setStatus("received");
        }
        if (payment.getCurrency() == null || payment.getCurrency().isBlank()) {
            payment.setCurrency("INR");
        }
        return repository.save(payment);
    }

    @Transactional(readOnly = true)
    public List<Payment> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Payment> findById(String id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Payment> findByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Transactional(readOnly = true)
    public List<Payment> findByCustomerAccountId(String customerAccountId) {
        return repository.findByCustomerAccountId(customerAccountId);
    }

    @Transactional(readOnly = true)
    public List<Payment> findByProductId(String productId) {
        return repository.findByProductId(productId);
    }

    @Transactional(readOnly = true)
    public List<Payment> findByCustomerIdAndStatus(String customerId, String status) {
        return repository.findByCustomerIdAndStatus(customerId, status);
    }

    public Optional<Payment> patch(String id, Payment patch) {
        return repository.findById(id).map(existing -> {
            String statusBefore = existing.getStatus();

            if (patch.getStatus()               != null) existing.setStatus(patch.getStatus());
            if (patch.getDescription()          != null) existing.setDescription(patch.getDescription());
            if (patch.getAuthorizationCode()    != null) existing.setAuthorizationCode(patch.getAuthorizationCode());
            if (patch.getAmount()               != null) existing.setAmount(patch.getAmount());
            if (patch.getPaymentMethodType()    != null) existing.setPaymentMethodType(patch.getPaymentMethodType());
            if (patch.getPaymentMethodDetail()  != null) existing.setPaymentMethodDetail(patch.getPaymentMethodDetail());
            if (patch.getPaymentMethodId()      != null) existing.setPaymentMethodId(patch.getPaymentMethodId());
            if (patch.getProductId()            != null) existing.setProductId(patch.getProductId());
            if (patch.getProductName()          != null) existing.setProductName(patch.getProductName());
            if (patch.getBillingPeriodStart()   != null) existing.setBillingPeriodStart(patch.getBillingPeriodStart());
            if (patch.getBillingPeriodEnd()     != null) existing.setBillingPeriodEnd(patch.getBillingPeriodEnd());
            existing.setUpdatedAt(OffsetDateTime.now());

            Payment saved = repository.save(existing);

            // Wire 14 write-back: payment just became "settled" → mark CustomerAccount as PAID in TMF629
            boolean justSettled = "settled".equals(saved.getStatus())
                && !"settled".equals(statusBefore);
            if (justSettled && saved.getCustomerAccountId() != null) {
                customerClient.markAccountPaid(saved.getCustomerAccountId());

                // Wire 17: send payment receipt SMS via TMF681
                communicationClient.sendPaymentReceipt(
                    saved.getId(), saved.getCustomerId(),
                    saved.getAmount(), saved.getCurrency()
                );
            }

            return saved;
        });
    }

    /**
     * Initiates a Razorpay payment session.
     *
     * Flow:
     *   1. Validate customer + product references (same as create)
     *   2. Save a Payment record with status "pending"
     *   3. Create a Razorpay order for the amount (in paise)
     *   4. Persist the razorpayOrderId on the record
     *   5. Return the order details the frontend needs for Razorpay Checkout JS
     */
    public PaymentInitiateResponse initiatePayment(PaymentInitiateRequest req) {
        // Validate customer
        if (!customerClient.customerExists(req.getCustomerId())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                "customerId '" + req.getCustomerId() + "' not found in TMF629");
        }
        if (!customerClient.customerAccountExists(req.getCustomerAccountId())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                "customerAccountId '" + req.getCustomerAccountId() + "' not found in TMF629");
        }
        if (req.getProductId() != null && !req.getProductId().isBlank()) {
            if (!inventoryClient.productExists(req.getProductId())) {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "productId '" + req.getProductId() + "' not found in TMF637");
            }
        }
        if (req.getPaymentMethodId() != null && !req.getPaymentMethodId().isBlank()) {
            if (!paymentMethodClient.paymentMethodExists(req.getPaymentMethodId())) {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "paymentMethodId '" + req.getPaymentMethodId() + "' not found in TMF671");
            }
        }

        // Persist a pending payment record
        Payment payment = new Payment();
        payment.setCustomerId(req.getCustomerId());
        payment.setCustomerAccountId(req.getCustomerAccountId());
        payment.setAmount(req.getAmount());
        payment.setCurrency(req.getCurrency() != null ? req.getCurrency() : "INR");
        payment.setPaymentMethodType(req.getPaymentMethodType());
        payment.setPaymentMethodId(req.getPaymentMethodId());
        payment.setProductId(req.getProductId());
        payment.setDescription(req.getDescription());
        payment.setStatus("pending");
        payment.setPaymentDate(OffsetDateTime.now());
        payment.setCreatedAt(OffsetDateTime.now());
        Payment saved = repository.save(payment);

        // Create Razorpay order — amount in paise
        long amountInPaise = Math.round(saved.getAmount() * 100);
        Order razorpayOrder;
        try {
            razorpayOrder = razorpayService.createOrder(amountInPaise, saved.getCurrency(), saved.getId());
        } catch (RazorpayException e) {
            log.error("[Razorpay] Failed to create order for payment {}: {}", saved.getId(), e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                "Razorpay order creation failed: " + e.getMessage());
        }

        String razorpayOrderId = razorpayOrder.get("id");
        saved.setRazorpayOrderId(razorpayOrderId);
        repository.save(saved);

        return new PaymentInitiateResponse(
            saved.getId(),
            razorpayOrderId,
            amountInPaise,
            saved.getCurrency(),
            razorpayService.getKeyId()
        );
    }

    /**
     * Handles a Razorpay webhook event.
     *
     * Razorpay calls this endpoint after a payment is captured or failed.
     * We verify the HMAC-SHA256 signature, then update the payment status and
     * trigger downstream wires (account mark-paid + SMS receipt).
     *
     * Supported events:
     *   payment.captured → status = "settled"
     *   payment.failed   → status = "rejected"
     */
    @SuppressWarnings("unchecked")
    public void handleWebhook(String rawBody, String signature, Map<String, Object> payload) {
        if (!razorpayService.verifyWebhookSignature(rawBody, signature)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Razorpay webhook signature");
        }

        String event = (String) payload.get("event");
        if (event == null) return;

        Map<String, Object> payloadBlock = (Map<String, Object>) payload.get("payload");
        if (payloadBlock == null) return;
        Map<String, Object> paymentBlock = (Map<String, Object>) payloadBlock.get("payment");
        if (paymentBlock == null) return;
        Map<String, Object> entity = (Map<String, Object>) paymentBlock.get("entity");
        if (entity == null) return;

        String razorpayPaymentId = (String) entity.get("id");
        String razorpayOrderId   = (String) entity.get("order_id");
        String method            = (String) entity.get("method");

        if (razorpayOrderId == null) return;

        repository.findByRazorpayOrderId(razorpayOrderId).ifPresent(payment -> {
            String statusBefore = payment.getStatus();

            if ("payment.captured".equals(event)) {
                payment.setStatus("settled");
                payment.setRazorpayPaymentId(razorpayPaymentId);
                payment.setAuthorizationCode(razorpayPaymentId);
                if (method != null) payment.setPaymentMethodDetail("Razorpay: " + method);
            } else if ("payment.failed".equals(event)) {
                payment.setStatus("rejected");
                payment.setRazorpayPaymentId(razorpayPaymentId);
            } else {
                return;
            }

            payment.setUpdatedAt(OffsetDateTime.now());
            Payment saved = repository.save(payment);

            // Trigger downstream only on fresh settlement
            boolean justSettled = "settled".equals(saved.getStatus()) && !"settled".equals(statusBefore);
            if (justSettled) {
                customerClient.markAccountPaid(saved.getCustomerAccountId());
                communicationClient.sendPaymentReceipt(
                    saved.getId(), saved.getCustomerId(),
                    saved.getAmount(), saved.getCurrency()
                );
            }
        });
    }

    public boolean delete(String id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
