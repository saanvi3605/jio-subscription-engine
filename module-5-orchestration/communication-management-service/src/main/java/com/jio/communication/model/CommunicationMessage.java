package com.jio.communication.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * TMF681 CommunicationMessage — represents a notification sent to a customer.
 *
 * type:    SMS | EMAIL | PUSH
 * status:  queued → sent → delivered → failed
 *
 * relatedEntityType + relatedEntityId link this message back to the event
 * that triggered it (e.g. "Order" + orderId, "Payment" + paymentId).
 */
@Entity
@Table(name = "communication_message")
public class CommunicationMessage {

    @Id
    private String id;

    private String type;           // SMS, EMAIL, PUSH

    @Column(length = 500)
    private String subject;

    @Column(length = 2000)
    private String content;

    private String receiver;       // phone number or email address
    private String sender;         // defaults to "Jio"
    private String status;         // queued, sent, delivered, failed

    private String customerId;
    private String relatedEntityType;  // Customer, Order, Payment, Product, ProcessFlow
    private String relatedEntityId;

    private OffsetDateTime sentAt;
    private OffsetDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (this.id == null) this.id = UUID.randomUUID().toString();
        if (this.sender == null) this.sender = "Jio";
        if (this.status == null) this.status = "queued";
        this.createdAt = OffsetDateTime.now();
    }

    // ── getters & setters ────────────────────────────────────────────────────

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getReceiver() { return receiver; }
    public void setReceiver(String receiver) { this.receiver = receiver; }

    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getRelatedEntityType() { return relatedEntityType; }
    public void setRelatedEntityType(String relatedEntityType) { this.relatedEntityType = relatedEntityType; }

    public String getRelatedEntityId() { return relatedEntityId; }
    public void setRelatedEntityId(String relatedEntityId) { this.relatedEntityId = relatedEntityId; }

    public OffsetDateTime getSentAt() { return sentAt; }
    public void setSentAt(OffsetDateTime sentAt) { this.sentAt = sentAt; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
