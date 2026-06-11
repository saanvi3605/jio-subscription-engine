package com.jio.paymentmethod.service;

import com.jio.paymentmethod.client.CustomerClient;
import com.jio.paymentmethod.model.PaymentMethod;
import com.jio.paymentmethod.repository.PaymentMethodRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentMethodService {

    private final PaymentMethodRepository repository;
    private final CustomerClient customerClient;

    public PaymentMethodService(PaymentMethodRepository repository, CustomerClient customerClient) {
        this.repository = repository;
        this.customerClient = customerClient;
    }

    public PaymentMethod create(PaymentMethod method) {
        // Wire 2: validate that the customer exists in TMF629 before adding a payment method
        if (!customerClient.customerExists(method.getCustomerId())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                "customerId '" + method.getCustomerId() +
                "' does not exist in Customer Management (TMF629). " +
                "Create the Customer first at POST /tmf-api/customerManagement/v4/customer");
        }
        method.setCreatedAt(OffsetDateTime.now());
        if (method.getStatus() == null || method.getStatus().isBlank()) {
            method.setStatus("active");
        }
        // If this is set as default, unset any existing default for this customer
        if (method.isDefault()) {
            repository.findByCustomerIdAndIsDefaultTrue(method.getCustomerId())
                    .ifPresent(existing -> {
                        existing.setDefault(false);
                        repository.save(existing);
                    });
        }
        return repository.save(method);
    }

    @Transactional(readOnly = true)
    public List<PaymentMethod> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<PaymentMethod> findById(String id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<PaymentMethod> findByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Transactional(readOnly = true)
    public Optional<PaymentMethod> findDefaultByCustomerId(String customerId) {
        return repository.findByCustomerIdAndIsDefaultTrue(customerId);
    }

    public Optional<PaymentMethod> patch(String id, PaymentMethod patch) {
        return repository.findById(id).map(existing -> {
            if (patch.getName()             != null) existing.setName(patch.getName());
            if (patch.getStatus()           != null) existing.setStatus(patch.getStatus());
            if (patch.getUpiId()            != null) existing.setUpiId(patch.getUpiId());
            if (patch.getCardLast4()        != null) existing.setCardLast4(patch.getCardLast4());
            if (patch.getCardBrand()        != null) existing.setCardBrand(patch.getCardBrand());
            if (patch.getCardHolderName()   != null) existing.setCardHolderName(patch.getCardHolderName());
            if (patch.getExpiryMonth()      != null) existing.setExpiryMonth(patch.getExpiryMonth());
            if (patch.getExpiryYear()       != null) existing.setExpiryYear(patch.getExpiryYear());
            if (patch.getBankName()         != null) existing.setBankName(patch.getBankName());
            if (patch.getAccountLast4()     != null) existing.setAccountLast4(patch.getAccountLast4());
            if (patch.getIfscCode()         != null) existing.setIfscCode(patch.getIfscCode());
            if (patch.getWalletProvider()   != null) existing.setWalletProvider(patch.getWalletProvider());
            if (patch.getWalletId()         != null) existing.setWalletId(patch.getWalletId());
            if (patch.getValidFrom()        != null) existing.setValidFrom(patch.getValidFrom());
            if (patch.getValidTo()          != null) existing.setValidTo(patch.getValidTo());
            // Handle default switch
            if (patch.isDefault() && !existing.isDefault()) {
                repository.findByCustomerIdAndIsDefaultTrue(existing.getCustomerId())
                        .ifPresent(old -> { old.setDefault(false); repository.save(old); });
                existing.setDefault(true);
            }
            existing.setUpdatedAt(OffsetDateTime.now());
            return repository.save(existing);
        });
    }

    public boolean delete(String id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
