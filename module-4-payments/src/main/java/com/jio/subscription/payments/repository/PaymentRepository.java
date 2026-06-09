package com.jio.subscription.payments.repository;

import com.jio.subscription.payments.domain.PaymentEntity;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, String> {

    Optional<PaymentEntity> findByCorrelatorId(String correlatorId);

    List<PaymentEntity> findByStatus(String status, Pageable pageable);

    List<PaymentEntity> findByStatusInAndUpdatedAtBefore(List<String> statuses, OffsetDateTime threshold);
}
