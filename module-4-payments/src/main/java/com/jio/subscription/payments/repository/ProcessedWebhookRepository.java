package com.jio.subscription.payments.repository;

import com.jio.subscription.payments.domain.ProcessedWebhook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedWebhookRepository extends JpaRepository<ProcessedWebhook, String> {
}
