package com.jio.usage.service;

import com.jio.usage.client.CustomerClient;
import com.jio.usage.client.InventoryClient;
import com.jio.usage.model.Usage;
import com.jio.usage.repository.UsageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsageService {

    private final UsageRepository repository;
    private final CustomerClient customerClient;
    private final InventoryClient inventoryClient;

    public UsageService(UsageRepository repository,
                        CustomerClient customerClient,
                        InventoryClient inventoryClient) {
        this.repository      = repository;
        this.customerClient  = customerClient;
        this.inventoryClient = inventoryClient;
    }

    public Usage create(Usage usage) {
        // Wire D: validate customer exists in TMF629
        if (!customerClient.customerExists(usage.getCustomerId())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                "customerId '" + usage.getCustomerId() +
                "' does not exist in Customer Management (TMF629)");
        }

        // Wire E: validate product exists in TMF637 (only if productId is provided)
        if (usage.getProductId() != null && !usage.getProductId().isBlank()) {
            if (!inventoryClient.productExists(usage.getProductId())) {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "productId '" + usage.getProductId() +
                    "' does not exist in Product Inventory (TMF637)");
            }
        }

        usage.setCreatedAt(OffsetDateTime.now());
        if (usage.getStatus() == null || usage.getStatus().isBlank()) {
            usage.setStatus("received");
        }
        return repository.save(usage);
    }

    @Transactional(readOnly = true)
    public List<Usage> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Usage> findById(String id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Usage> findByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Transactional(readOnly = true)
    public List<Usage> findByProductId(String productId) {
        return repository.findByProductId(productId);
    }

    @Transactional(readOnly = true)
    public List<Usage> findByCustomerIdAndUsageType(String customerId, String usageType) {
        return repository.findByCustomerIdAndUsageType(customerId, usageType);
    }

    @Transactional(readOnly = true)
    public List<Usage> findByCustomerIdAndStatus(String customerId, String status) {
        return repository.findByCustomerIdAndStatus(customerId, status);
    }

    public Optional<Usage> patch(String id, Usage patch) {
        return repository.findById(id).map(existing -> {
            if (patch.getStatus()        != null) existing.setStatus(patch.getStatus());
            if (patch.getDescription()   != null) existing.setDescription(patch.getDescription());
            if (patch.getQuantity()      != null) existing.setQuantity(patch.getQuantity());
            if (patch.getUnit()          != null) existing.setUnit(patch.getUnit());
            if (patch.getRatingDate()    != null) existing.setRatingDate(patch.getRatingDate());
            if (patch.getRatedAmount()   != null) existing.setRatedAmount(patch.getRatedAmount());
            if (patch.getProductId()     != null) existing.setProductId(patch.getProductId());
            if (patch.getProductName()   != null) existing.setProductName(patch.getProductName());
            if (!patch.getUsageCharacteristic().isEmpty()) existing.setUsageCharacteristic(patch.getUsageCharacteristic());
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
