package com.jio.payment.service;

import com.jio.payment.client.CommunicationClient;
import com.jio.payment.client.CustomerClient;
import com.jio.payment.client.InventoryClient;
import com.jio.payment.client.PaymentMethodClient;
import com.jio.payment.model.Payment;
import com.jio.payment.repository.PaymentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentService {

    private final PaymentRepository repository;
    private final CustomerClient customerClient;
    private final InventoryClient inventoryClient;
    private final PaymentMethodClient paymentMethodClient;
    private final CommunicationClient communicationClient;

    public PaymentService(PaymentRepository repository,
                          CustomerClient customerClient,
                          InventoryClient inventoryClient,
                          PaymentMethodClient paymentMethodClient,
                          CommunicationClient communicationClient) {
        this.repository = repository;
        this.customerClient = customerClient;
        this.inventoryClient = inventoryClient;
        this.paymentMethodClient = paymentMethodClient;
        this.communicationClient = communicationClient;
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

    public boolean delete(String id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
