package com.jio.subscription.payments.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;

/** De-duplication record for inbound PSP webhooks, keyed on the provider event id. */
@Entity
@Table(name = "processed_webhook")
public class ProcessedWebhook {

    @Id
    @Column(name = "event_id", length = 255, nullable = false)
    private String eventId;

    @Column(name = "provider", length = 64, nullable = false)
    private String provider;

    @Column(name = "payment_id", length = 64)
    private String paymentId;

    @Column(name = "received_at", nullable = false)
    private OffsetDateTime receivedAt;

    protected ProcessedWebhook() {
    }

    public ProcessedWebhook(String eventId, String provider, String paymentId) {
        this.eventId = eventId;
        this.provider = provider;
        this.paymentId = paymentId;
    }

    @PrePersist
    void onCreate() {
        if (receivedAt == null) {
            receivedAt = OffsetDateTime.now();
        }
    }

    public String getEventId() {
        return eventId;
    }

    public String getProvider() {
        return provider;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public OffsetDateTime getReceivedAt() {
        return receivedAt;
    }
}
