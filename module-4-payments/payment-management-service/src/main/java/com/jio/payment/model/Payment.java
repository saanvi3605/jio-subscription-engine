package com.jio.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.lang.Nullable;

import java.net.URI;
import java.time.OffsetDateTime;

/**
 * TMF676 Payment Management — Payment resource.
 *
 * Represents a single payment transaction made by a customer
 * against their account for a product/subscription.
 *
 * e.g. "Arjun paid ₹999 via UPI on 10-Jun-2026 for his Postpaid Elite plan"
 *
 * Status lifecycle:
 *   received → settled   (payment confirmed)
 *   received → rejected  (payment failed)
 *   received → held      (under review)
 *
 * Cross-service references:
 *   customerId        → jio_customer.customer.id          (TMF629)
 *   customerAccountId → jio_customer.customer_account.id  (TMF629)
 *   productId         → jio_product_inventory.product.id  (TMF637)
 *   paymentMethodId   → jio_payment_method (TMF671 — to be built)
 *
 * Table: payment
 */
@Entity
@Table(name = "payment")
@Schema(name = "Payment", description = "A payment transaction made by a customer for a product or subscription")
public class Payment {

    // ── Primary Key ──────────────────────────────────────────────────────────
    @Id
    @UuidGenerator
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    // ── Core fields ──────────────────────────────────────────────────────────
    /** When the payment was made */
    @NotNull
    @JsonProperty("paymentDate")
    private OffsetDateTime paymentDate;

    /** Amount paid — must be positive */
    @NotNull
    @Positive
    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("currency")
    private String currency = "INR";

    /**
     * Lifecycle: received | settled | rejected | held
     */
    @JsonProperty("status")
    private String status = "received";

    @JsonProperty("description")
    private @Nullable String description;

    /** Authorization/reference code from payment gateway */
    @JsonProperty("authorizationCode")
    private @Nullable String authorizationCode;

    // ── Payment method ────────────────────────────────────────────────────────
    /**
     * How the customer paid: UPI | CREDIT_CARD | DEBIT_CARD | NET_BANKING | WALLET
     */
    @NotBlank
    @JsonProperty("paymentMethodType")
    private String paymentMethodType;

    /** Reference to TMF671 PaymentMethod.id (to be built) */
    @JsonProperty("paymentMethodId")
    private @Nullable String paymentMethodId;

    /** Human-readable method detail — "UPI: arjun@jio", "HDFC Visa ****4242" */
    @JsonProperty("paymentMethodDetail")
    private @Nullable String paymentMethodDetail;

    // ── Cross-service references ─────────────────────────────────────────────
    /** Customer who made the payment — references jio_customer.customer.id (TMF629) */
    @NotBlank
    @JsonProperty("customerId")
    private String customerId;

    @JsonProperty("customerName")
    private @Nullable String customerName;

    /** Billing account charged — references jio_customer.customer_account.id (TMF629) */
    @NotBlank
    @JsonProperty("customerAccountId")
    private String customerAccountId;

    @JsonProperty("customerAccountName")
    private @Nullable String customerAccountName;

    /** The product this payment is for — references jio_product_inventory.product.id (TMF637) */
    @JsonProperty("productId")
    private @Nullable String productId;

    @JsonProperty("productName")
    private @Nullable String productName;

    // ── Billing period ────────────────────────────────────────────────────────
    /** Start of the billing period this payment covers */
    @JsonProperty("billingPeriodStart")
    private @Nullable OffsetDateTime billingPeriodStart;

    /** End of the billing period this payment covers */
    @JsonProperty("billingPeriodEnd")
    private @Nullable OffsetDateTime billingPeriodEnd;

    // ── Timestamps ────────────────────────────────────────────────────────────
    @JsonProperty("createdAt")
    private OffsetDateTime createdAt;

    @JsonProperty("updatedAt")
    private @Nullable OffsetDateTime updatedAt;

    // ── TM Forum extension fields ─────────────────────────────────────────────
    @JsonProperty("@baseType")
    private @Nullable String atBaseType;

    @Transient
    @JsonProperty("@schemaLocation")
    private @Nullable URI atSchemaLocation;

    @JsonProperty("@type")
    private @Nullable String atType;

    // ── Constructors ──────────────────────────────────────────────────────────
    public Payment() {}

    // ── Getters & Setters ─────────────────────────────────────────────────────
    public String getId()                            { return id; }
    public void setId(String id)                     { this.id = id; }

    public OffsetDateTime getPaymentDate()               { return paymentDate; }
    public void setPaymentDate(OffsetDateTime pd)        { this.paymentDate = pd; }

    public Double getAmount()                        { return amount; }
    public void setAmount(Double amount)             { this.amount = amount; }

    public String getCurrency()                      { return currency; }
    public void setCurrency(String currency)         { this.currency = currency; }

    public String getStatus()                        { return status; }
    public void setStatus(String status)             { this.status = status; }

    public String getDescription()                   { return description; }
    public void setDescription(String desc)          { this.description = desc; }

    public String getAuthorizationCode()                     { return authorizationCode; }
    public void setAuthorizationCode(String ac)              { this.authorizationCode = ac; }

    public String getPaymentMethodType()                     { return paymentMethodType; }
    public void setPaymentMethodType(String pmt)             { this.paymentMethodType = pmt; }

    public String getPaymentMethodId()                       { return paymentMethodId; }
    public void setPaymentMethodId(String pmId)              { this.paymentMethodId = pmId; }

    public String getPaymentMethodDetail()                   { return paymentMethodDetail; }
    public void setPaymentMethodDetail(String pmd)           { this.paymentMethodDetail = pmd; }

    public String getCustomerId()                    { return customerId; }
    public void setCustomerId(String cid)            { this.customerId = cid; }

    public String getCustomerName()                  { return customerName; }
    public void setCustomerName(String cn)           { this.customerName = cn; }

    public String getCustomerAccountId()                     { return customerAccountId; }
    public void setCustomerAccountId(String caid)            { this.customerAccountId = caid; }

    public String getCustomerAccountName()                   { return customerAccountName; }
    public void setCustomerAccountName(String can)           { this.customerAccountName = can; }

    public String getProductId()                     { return productId; }
    public void setProductId(String pid)             { this.productId = pid; }

    public String getProductName()                   { return productName; }
    public void setProductName(String pn)            { this.productName = pn; }

    public OffsetDateTime getBillingPeriodStart()            { return billingPeriodStart; }
    public void setBillingPeriodStart(OffsetDateTime bps)    { this.billingPeriodStart = bps; }

    public OffsetDateTime getBillingPeriodEnd()              { return billingPeriodEnd; }
    public void setBillingPeriodEnd(OffsetDateTime bpe)      { this.billingPeriodEnd = bpe; }

    public OffsetDateTime getCreatedAt()                     { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt)       { this.createdAt = createdAt; }

    public OffsetDateTime getUpdatedAt()                     { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt)       { this.updatedAt = updatedAt; }

    public String getAtBaseType()                    { return atBaseType; }
    public void setAtBaseType(String v)              { this.atBaseType = v; }

    public URI getAtSchemaLocation()                         { return atSchemaLocation; }
    public void setAtSchemaLocation(URI v)                   { this.atSchemaLocation = v; }

    public String getAtType()                        { return atType; }
    public void setAtType(String v)                  { this.atType = v; }
}
