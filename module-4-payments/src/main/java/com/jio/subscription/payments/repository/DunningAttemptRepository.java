package com.jio.subscription.payments.repository;

import com.jio.subscription.payments.domain.DunningAttempt;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DunningAttemptRepository extends JpaRepository<DunningAttempt, Long> {

    List<DunningAttempt> findByStatusAndNextAttemptAtBefore(String status, OffsetDateTime due);

    List<DunningAttempt> findByPaymentIdOrderByAttemptNumberAsc(String paymentId);
}
