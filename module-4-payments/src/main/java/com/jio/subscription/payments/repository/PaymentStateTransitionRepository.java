package com.jio.subscription.payments.repository;

import com.jio.subscription.payments.domain.PaymentStateTransition;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentStateTransitionRepository extends JpaRepository<PaymentStateTransition, Long> {

    List<PaymentStateTransition> findByPaymentIdOrderByCreatedAtAsc(String paymentId);
}
