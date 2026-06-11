package com.jio.usage.repository;

import com.jio.usage.model.ProductUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductUsageRepository extends JpaRepository<ProductUsage, String> {

    /** All usage summaries for a customer — "what is Arjun consuming?" */
    List<ProductUsage> findByCustomerId(String customerId);

    /** All usage summaries for a specific product */
    List<ProductUsage> findByProductId(String productId);

    /** Specific usage type for a product — e.g. DATA usage for Arjun's 84-day plan */
    Optional<ProductUsage> findByProductIdAndUsageType(String productId, String usageType);

    /** All exhausted allowances across all customers */
    List<ProductUsage> findByStatus(String status);

    /** All usage summaries for a customer filtered by type */
    List<ProductUsage> findByCustomerIdAndUsageType(String customerId, String usageType);
}
