package com.jio.usage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.lang.Nullable;

import java.net.URI;
import java.time.OffsetDateTime;

/**
 * TMF635 Product Usage — aggregate consumption summary for a product.
 *
 * While Usage records individual raw events ("used 1.2GB at 2:35pm"),
 * ProductUsage tracks the running total against a product's allowance:
 * "Arjun has used 45GB of his 100GB plan, 55GB remaining."
 *
 * One ProductUsage record exists per product per billing period.
 * It is updated every time a new Usage event is recorded for that product.
 *
 * Cross-service references:
 *   productId  → jio_product_inventory.product.id  (TMF637)
 *   customerId → jio_customer.customer.id           (TMF629)
 *
 * Table: product_usage
 */
@Entity
@Table(name = "product_usage")
@Schema(name = "ProductUsage", description = "Aggregate usage summary for a product — allowance vs consumed vs remaining")
public class ProductUsage {

    // ── Primary Key ──────────────────────────────────────────────────────────
    @Id
    @UuidGenerator
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    // ── Cross-service references ─────────────────────────────────────────────
    /** The product whose allowance is being tracked — references TMF637 */
    @NotBlank
    @JsonProperty("productId")
    private String productId;

    @JsonProperty("productName")
    private @Nullable String productName;

    /** The customer who owns the product — references TMF629 */
    @NotBlank
    @JsonProperty("customerId")
    private String customerId;

    @JsonProperty("customerName")
    private @Nullable String customerName;

    // ── Usage type ────────────────────────────────────────────────────────────
    /**
     * What type of allowance this tracks: DATA | VOICE | SMS
     * A single product can have multiple ProductUsage records — one per type.
     * e.g. Arjun's plan has DATA (100GB) and VOICE (unlimited) and SMS (100/day)
     */
    @NotBlank
    @JsonProperty("usageType")
    private String usageType;

    // ── Allowance vs Consumed ─────────────────────────────────────────────────
    /** Total allowance for the billing period — e.g. 100.0 (GB) */
    @JsonProperty("allowance")
    private @Nullable Double allowance;

    /** How much has been consumed so far — updated on each usage event */
    @NotNull
    @JsonProperty("consumed")
    private Double consumed = 0.0;

    /** Remaining = allowance - consumed (null if allowance is unlimited) */
    @JsonProperty("remaining")
    private @Nullable Double remaining;

    /** Unit of measurement: GB, MB, minutes, count */
    @NotBlank
    @JsonProperty("unit")
    private String unit;

    /**
     * Status: active | exhausted | suspended
     * Becomes "exhausted" when consumed >= allowance
     */
    @JsonProperty("status")
    private String status = "active";

    // ── Billing period ────────────────────────────────────────────────────────
    @JsonProperty("periodStart")
    private @Nullable OffsetDateTime periodStart;

    @JsonProperty("periodEnd")
    private @Nullable OffsetDateTime periodEnd;

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
    public ProductUsage() {}

    // ── Getters & Setters ─────────────────────────────────────────────────────
    public String getId()                            { return id; }
    public void setId(String id)                     { this.id = id; }

    public String getProductId()                     { return productId; }
    public void setProductId(String pid)             { this.productId = pid; }

    public String getProductName()                   { return productName; }
    public void setProductName(String pn)            { this.productName = pn; }

    public String getCustomerId()                    { return customerId; }
    public void setCustomerId(String cid)            { this.customerId = cid; }

    public String getCustomerName()                  { return customerName; }
    public void setCustomerName(String cn)           { this.customerName = cn; }

    public String getUsageType()                     { return usageType; }
    public void setUsageType(String ut)              { this.usageType = ut; }

    public Double getAllowance()                      { return allowance; }
    public void setAllowance(Double allowance)       { this.allowance = allowance; }

    public Double getConsumed()                      { return consumed; }
    public void setConsumed(Double consumed)         { this.consumed = consumed; }

    public Double getRemaining()                     { return remaining; }
    public void setRemaining(Double remaining)       { this.remaining = remaining; }

    public String getUnit()                          { return unit; }
    public void setUnit(String unit)                 { this.unit = unit; }

    public String getStatus()                        { return status; }
    public void setStatus(String status)             { this.status = status; }

    public OffsetDateTime getPeriodStart()               { return periodStart; }
    public void setPeriodStart(OffsetDateTime ps)        { this.periodStart = ps; }

    public OffsetDateTime getPeriodEnd()                 { return periodEnd; }
    public void setPeriodEnd(OffsetDateTime pe)          { this.periodEnd = pe; }

    public OffsetDateTime getCreatedAt()                 { return createdAt; }
    public void setCreatedAt(OffsetDateTime ca)          { this.createdAt = ca; }

    public OffsetDateTime getUpdatedAt()                 { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime ua)          { this.updatedAt = ua; }

    public String getAtBaseType()                    { return atBaseType; }
    public void setAtBaseType(String v)              { this.atBaseType = v; }

    public URI getAtSchemaLocation()                 { return atSchemaLocation; }
    public void setAtSchemaLocation(URI v)           { this.atSchemaLocation = v; }

    public String getAtType()                        { return atType; }
    public void setAtType(String v)                  { this.atType = v; }
}
