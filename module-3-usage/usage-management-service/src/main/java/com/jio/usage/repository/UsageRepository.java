package com.jio.usage.repository;

import com.jio.usage.model.Usage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsageRepository extends JpaRepository<Usage, String> {

    /** All usage records for a customer — "how much has Arjun used this month?" */
    List<Usage> findByCustomerId(String customerId);

    /** All usage records for a specific product inventory item */
    List<Usage> findByProductId(String productId);

    /** Filter by usage type — DATA, VOICE, SMS, ROAMING_DATA, ROAMING_VOICE */
    List<Usage> findByUsageType(String usageType);

    /** Filter by rating status — received, guided, rated, billed */
    List<Usage> findByStatus(String status);

    /** All usage for a customer of a specific type — e.g. all DATA for Arjun */
    List<Usage> findByCustomerIdAndUsageType(String customerId, String usageType);

    /** All usage for a customer filtered by status — e.g. all unbilled usage */
    List<Usage> findByCustomerIdAndStatus(String customerId, String status);
}
