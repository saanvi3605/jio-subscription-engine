package com.jio.usage.service;

import com.jio.usage.model.ProductUsage;
import com.jio.usage.repository.ProductUsageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductUsageService {

    private final ProductUsageRepository repository;

    public ProductUsageService(ProductUsageRepository repository) {
        this.repository = repository;
    }

    /**
     * Create a new product usage summary.
     * Calculates remaining = allowance - consumed on creation.
     * Marks status "exhausted" if consumed >= allowance.
     */
    public ProductUsage create(ProductUsage productUsage) {
        productUsage.setCreatedAt(OffsetDateTime.now());
        if (productUsage.getStatus() == null || productUsage.getStatus().isBlank()) {
            productUsage.setStatus("active");
        }
        recalculate(productUsage);
        return repository.save(productUsage);
    }

    @Transactional(readOnly = true)
    public List<ProductUsage> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<ProductUsage> findById(String id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<ProductUsage> findByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Transactional(readOnly = true)
    public List<ProductUsage> findByProductId(String productId) {
        return repository.findByProductId(productId);
    }

    public Optional<ProductUsage> patch(String id, ProductUsage patch) {
        return repository.findById(id).map(existing -> {
            if (patch.getProductName()   != null) existing.setProductName(patch.getProductName());
            if (patch.getCustomerName()  != null) existing.setCustomerName(patch.getCustomerName());
            if (patch.getAllowance()     != null) existing.setAllowance(patch.getAllowance());
            if (patch.getConsumed()     != null) existing.setConsumed(patch.getConsumed());
            if (patch.getUnit()         != null) existing.setUnit(patch.getUnit());
            if (patch.getStatus()       != null) existing.setStatus(patch.getStatus());
            if (patch.getPeriodStart()  != null) existing.setPeriodStart(patch.getPeriodStart());
            if (patch.getPeriodEnd()    != null) existing.setPeriodEnd(patch.getPeriodEnd());
            recalculate(existing);
            existing.setUpdatedAt(OffsetDateTime.now());
            return repository.save(existing);
        });
    }

    public boolean delete(String id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    /**
     * Recalculates remaining and auto-sets status to "exhausted"
     * when consumed >= allowance.
     */
    private void recalculate(ProductUsage pu) {
        if (pu.getAllowance() != null && pu.getConsumed() != null) {
            double remaining = pu.getAllowance() - pu.getConsumed();
            pu.setRemaining(Math.max(remaining, 0.0));
            if (pu.getConsumed() >= pu.getAllowance()) {
                pu.setStatus("exhausted");
            }
        }
    }
}
