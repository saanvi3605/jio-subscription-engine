package com.jio.subscription.payments.repository;

import com.jio.subscription.payments.domain.RefundEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundRepository extends JpaRepository<RefundEntity, String> {

    Optional<RefundEntity> findByCorrelatorId(String correlatorId);

    List<RefundEntity> findByPaymentId(String paymentId);
}
