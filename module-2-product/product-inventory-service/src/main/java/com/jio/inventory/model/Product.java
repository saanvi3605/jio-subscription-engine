package com.jio.inventory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.lang.Nullable;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * TMF637 Product Inventory — Product resource.
 *
 * Represents a product instance that a customer currently holds —
 * e.g. "Arjun Kapoor has Jio 5G 84-day plan, active from 10-Jun-2026,
 * expires 3-Sep-2026, SIM ICCID 89914XXXXXXXXX."
 *
 * Cross-service references (all stored as plain strings):
 *   customerId        → jio_customer.customer.id        (TMF629)
 *   productOrderId    → jio_product_order.product_order.id (TMF622)
 *   productOfferingId → jio_product_catalog offering.id (TMF620)
 *
 * Table: product
 */
@Entity
@Table(name = "product")
@Schema(name = "Product", description = "A product instance held by a customer after order fulfilment")
public class Product {

    // ── Primary Key ──────────────────────────────────────────────────────────
    @Id
    @UuidGenerator
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    // ── Core fields ──────────────────────────────────────────────────────────
    @NotBlank
    @JsonProperty("name")
    private String name;

    /**
     * Lifecycle: active | inactive | suspended | terminated
     */
    @JsonProperty("status")
    private String status = "active";

    @JsonProperty("statusReason")
    private @Nullable String statusReason;

    @JsonProperty("description")
    private @Nullable String description;

    @JsonProperty("isBundle")
    private boolean isBundle = false;

    @JsonProperty("isCustomerVisible")
    private boolean isCustomerVisible = true;

    // ── Dates ────────────────────────────────────────────────────────────────
    /** When the product was activated */
    @JsonProperty("startDate")
    private @Nullable OffsetDateTime startDate;

    /** When the product expires / is terminated */
    @JsonProperty("terminationDate")
    private @Nullable OffsetDateTime terminationDate;

    // ── Cross-service references ─────────────────────────────────────────────
    /** Customer who owns this product — references jio_customer.customer.id (TMF629) */
    @NotBlank
    @JsonProperty("customerId")
    private String customerId;

    @JsonProperty("customerName")
    private @Nullable String customerName;

    /** The order that created this inventory item — references TMF622 */
    @JsonProperty("productOrderId")
    private @Nullable String productOrderId;

    /** The offering from the catalog this was ordered from — references TMF620 */
    @JsonProperty("productOfferingId")
    private @Nullable String productOfferingId;

    @JsonProperty("productOfferingName")
    private @Nullable String productOfferingName;

    /** Physical/logical identifier — SIM ICCID, eSIM EID, etc. */
    @JsonProperty("productSerialNumber")
    private @Nullable String productSerialNumber;

    // ── Characteristics ──────────────────────────────────────────────────────
    @Valid
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "product_characteristic",
        joinColumns = @JoinColumn(name = "product_id")
    )
    @JsonProperty("productCharacteristic")
    private List<ProductCharacteristic> productCharacteristic = new ArrayList<>();

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
    public Product() {}

    // ── Getters & Setters ─────────────────────────────────────────────────────
    public String getId()                        { return id; }
    public void setId(String id)                 { this.id = id; }

    public String getName()                      { return name; }
    public void setName(String name)             { this.name = name; }

    public String getStatus()                    { return status; }
    public void setStatus(String status)         { this.status = status; }

    public String getStatusReason()              { return statusReason; }
    public void setStatusReason(String sr)       { this.statusReason = sr; }

    public String getDescription()               { return description; }
    public void setDescription(String desc)      { this.description = desc; }

    public boolean isBundle()                    { return isBundle; }
    public void setBundle(boolean bundle)        { isBundle = bundle; }

    public boolean isCustomerVisible()           { return isCustomerVisible; }
    public void setCustomerVisible(boolean cv)   { isCustomerVisible = cv; }

    public OffsetDateTime getStartDate()         { return startDate; }
    public void setStartDate(OffsetDateTime sd)  { this.startDate = sd; }

    public OffsetDateTime getTerminationDate()             { return terminationDate; }
    public void setTerminationDate(OffsetDateTime td)      { this.terminationDate = td; }

    public String getCustomerId()                { return customerId; }
    public void setCustomerId(String cid)        { this.customerId = cid; }

    public String getCustomerName()              { return customerName; }
    public void setCustomerName(String cn)       { this.customerName = cn; }

    public String getProductOrderId()            { return productOrderId; }
    public void setProductOrderId(String poi)    { this.productOrderId = poi; }

    public String getProductOfferingId()         { return productOfferingId; }
    public void setProductOfferingId(String id)  { this.productOfferingId = id; }

    public String getProductOfferingName()              { return productOfferingName; }
    public void setProductOfferingName(String name)     { this.productOfferingName = name; }

    public String getProductSerialNumber()              { return productSerialNumber; }
    public void setProductSerialNumber(String psn)      { this.productSerialNumber = psn; }

    public List<ProductCharacteristic> getProductCharacteristic()                      { return productCharacteristic; }
    public void setProductCharacteristic(List<ProductCharacteristic> characteristics)  { this.productCharacteristic = characteristics; }

    public OffsetDateTime getCreatedAt()                 { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt)   { this.createdAt = createdAt; }

    public OffsetDateTime getUpdatedAt()                 { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt)   { this.updatedAt = updatedAt; }

    public String getAtBaseType()                { return atBaseType; }
    public void setAtBaseType(String atBaseType) { this.atBaseType = atBaseType; }

    public URI getAtSchemaLocation()                         { return atSchemaLocation; }
    public void setAtSchemaLocation(URI atSchemaLocation)    { this.atSchemaLocation = atSchemaLocation; }

    public String getAtType()                    { return atType; }
    public void setAtType(String atType)         { this.atType = atType; }
}
