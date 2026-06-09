package com.jio.subscription.payments.repository;

import com.jio.subscription.payments.domain.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethodEntity, String> {
}
