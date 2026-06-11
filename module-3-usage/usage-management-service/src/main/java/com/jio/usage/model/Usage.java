package com.jio.usage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.lang.Nullable;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * TMF635 Usage Management — Usage resource.
 *
 * Represents a single usage event — a data session, a voice call, an SMS.
 * e.g. "Arjun used 1.2 GB of data on 10-Jun-2026 at 14:35"
 *      "Rohan made a 5-minute call to 9876543210 on 10-Jun-2026"
 *
 * Status lifecycle:
 *   received → guided → rated → billed
 *   (received=raw event came in, rated=priced, billed=included in invoice)
 *
 * Cross-service references:
 *   customerId  → jio_customer.customer.id      (TMF629)
 *   productId   → jio_product_inventory.product.id (TMF637)
 *
 * Table: usage
 */
@Entity
@Table(name = "usage_record")
@Schema(name = "Usage", description = "A single usage event — data, voice, or SMS consumed by a customer")
public class Usage {

    // ── Primary Key ──────────────────────────────────────────────────────────
    @Id
    @UuidGenerator
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    // ── Core fields ──────────────────────────────────────────────────────────
    /**
     * Type of usage: DATA | VOICE | SMS | ROAMING_DATA | ROAMING_VOICE
     */
    @NotBlank
    @JsonProperty("usageType")
    private String usageType;

    /**
     * Lifecycle status: received | guided | rated | rerate | billed
     */
    @JsonProperty("status")
    private String status = "received";

    /** When the usage event actually occurred */
    @NotNull
    @JsonProperty("usageDate")
    private OffsetDateTime usageDate;

    @JsonProperty("description")
    private @Nullable String description;

    // ── Quantity ─────────────────────────────────────────────────────────────
    /** How much was consumed — e.g. 1.2 (GB), 342 (seconds), 1 (SMS) */
    @NotNull
    @JsonProperty("quantity")
    private Double quantity;

    /** Unit of quantity — GB, MB, seconds, minutes, count */
    @NotBlank
    @JsonProperty("unit")
    private String unit;

    // ── Rating ───────────────────────────────────────────────────────────────
    /** When the usage was rated (priced) */
    @JsonProperty("ratingDate")
    private @Nullable OffsetDateTime ratingDate;

    /** Cost assigned after rating — in INR */
    @JsonProperty("ratedAmount")
    private @Nullable Double ratedAmount;

    // ── Cross-service references ─────────────────────────────────────────────
    /** Customer who generated this usage — references jio_customer.customer.id (TMF629) */
    @NotBlank
    @JsonProperty("customerId")
    private String customerId;

    @JsonProperty("customerName")
    private @Nullable String customerName;

    /**
     * The product inventory item being consumed — references
     * jio_product_inventory.product.id (TMF637)
     */
    @JsonProperty("productId")
    private @Nullable String productId;

    @JsonProperty("productName")
    private @Nullable String productName;

    // ── Characteristics ──────────────────────────────────────────────────────
    @Valid
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "usage_characteristic",
        joinColumns = @JoinColumn(name = "usage_id")
    )
    @JsonProperty("usageCharacteristic")
    private List<UsageCharacteristic> usageCharacteristic = new ArrayList<>();

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
    public Usage() {}

    // ── Getters & Setters ─────────────────────────────────────────────────────
    public String getId()                        { return id; }
    public void setId(String id)                 { this.id = id; }

    public String getUsageType()                 { return usageType; }
    public void setUsageType(String ut)          { this.usageType = ut; }

    public String getStatus()                    { return status; }
    public void setStatus(String status)         { this.status = status; }

    public OffsetDateTime getUsageDate()         { return usageDate; }
    public void setUsageDate(OffsetDateTime ud)  { this.usageDate = ud; }

    public String getDescription()               { return description; }
    public void setDescription(String desc)      { this.description = desc; }

    public Double getQuantity()                  { return quantity; }
    public void setQuantity(Double quantity)     { this.quantity = quantity; }

    public String getUnit()                      { return unit; }
    public void setUnit(String unit)             { this.unit = unit; }

    public OffsetDateTime getRatingDate()                { return ratingDate; }
    public void setRatingDate(OffsetDateTime rd)         { this.ratingDate = rd; }

    public Double getRatedAmount()               { return ratedAmount; }
    public void setRatedAmount(Double ra)        { this.ratedAmount = ra; }

    public String getCustomerId()                { return customerId; }
    public void setCustomerId(String cid)        { this.customerId = cid; }

    public String getCustomerName()              { return customerName; }
    public void setCustomerName(String cn)       { this.customerName = cn; }

    public String getProductId()                 { return productId; }
    public void setProductId(String pid)         { this.productId = pid; }

    public String getProductName()               { return productName; }
    public void setProductName(String pn)        { this.productName = pn; }

    public List<UsageCharacteristic> getUsageCharacteristic()                      { return usageCharacteristic; }
    public void setUsageCharacteristic(List<UsageCharacteristic> characteristics)  { this.usageCharacteristic = characteristics; }

    public OffsetDateTime getCreatedAt()                 { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt)   { this.createdAt = createdAt; }

    public OffsetDateTime getUpdatedAt()                 { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt)   { this.updatedAt = updatedAt; }

    public String getAtBaseType()                { return atBaseType; }
    public void setAtBaseType(String v)          { this.atBaseType = v; }

    public URI getAtSchemaLocation()                         { return atSchemaLocation; }
    public void setAtSchemaLocation(URI atSchemaLocation)    { this.atSchemaLocation = atSchemaLocation; }

    public String getAtType()                    { return atType; }
    public void setAtType(String atType)         { this.atType = atType; }
}
