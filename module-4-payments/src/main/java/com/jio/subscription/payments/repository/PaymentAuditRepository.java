package com.jio.subscription.payments.repository;

import com.jio.subscription.payments.domain.PaymentAudit;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentAuditRepository extends JpaRepository<PaymentAudit, Long> {

    List<PaymentAudit> findByEntityTypeAndEntityIdOrderByCreatedAtAsc(String entityType, String entityId);
}
