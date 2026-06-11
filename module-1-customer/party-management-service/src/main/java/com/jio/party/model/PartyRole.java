package com.jio.party.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.lang.Nullable;

import java.net.URI;

/**
 * A PartyRole defines a role that a Party (Individual or Organization) plays
 * in the context of the subscription engine.
 *
 * Examples:
 *   - Subscriber     → an individual with an active plan
 *   - ContentProvider → e.g. Hotstar, SonyLiv (B2B)
 *   - BankingPartner  → e.g. HDFC, SBI (for payment integrations)
 *   - Retailer        → a Jio franchise store
 *
 * TMF632 Party Management API resource.
 * Table: party_role
 */
@Entity
@Table(name = "party_role")
@Schema(name = "PartyRole", description = "A role that an Individual or Organization plays in the system")
public class PartyRole {

    // ── Primary Key ─────────────────────────────────────────────────────────
    @Id
    @UuidGenerator
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    // ── Role identity ────────────────────────────────────────────────────────
    /**
     * Human-readable name of the role.
     * Examples: "Subscriber", "ContentProvider", "BankingPartner", "Retailer"
     */
    @NotBlank
    @JsonProperty("name")
    private String name;

    /**
     * Category/type of the role — groups similar roles together.
     * Examples: "CustomerRole", "PartnerRole", "ChannelRole"
     */
    @JsonProperty("roleType")
    private @Nullable String roleType;

    @JsonProperty("description")
    private @Nullable String description;

    /**
     * Lifecycle status: initialized | validated | closed
     */
    @JsonProperty("status")
    private String status = "initialized";

    @JsonProperty("statusReason")
    private @Nullable String statusReason;

    // ── Engaged party (cross-service reference) ──────────────────────────────
    /**
     * The id of the Individual or Organization that holds this role.
     * References jio_party.individual.id (no DB-level FK since cross-service).
     */
    @NotBlank
    @JsonProperty("engagedPartyId")
    private String engagedPartyId;

    @JsonProperty("engagedPartyHref")
    private @Nullable String engagedPartyHref;

    @JsonProperty("engagedPartyName")
    private @Nullable String engagedPartyName;

    /**
     * The type of the engaged party: "Individual" or "Organization"
     */
    @JsonProperty("engagedPartyType")
    private @Nullable String engagedPartyType;

    // ── Validity period ──────────────────────────────────────────────────────
    @Valid
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "startDateTime", column = @Column(name = "valid_from")),
        @AttributeOverride(name = "endDateTime",   column = @Column(name = "valid_to"))
    })
    @JsonProperty("validFor")
    private @Nullable TimePeriod validFor;

    // ── TM Forum standard extension fields ───────────────────────────────────
    @JsonProperty("@baseType")
    private @Nullable String atBaseType;

    @Transient
    @JsonProperty("@schemaLocation")
    private @Nullable URI atSchemaLocation;

    @JsonProperty("@type")
    private @Nullable String atType;

    // ── Constructors ─────────────────────────────────────────────────────────
    public PartyRole() {}

    public PartyRole(String name, String engagedPartyId) {
        this.name            = name;
        this.engagedPartyId  = engagedPartyId;
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRoleType() { return roleType; }
    public void setRoleType(String roleType) { this.roleType = roleType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getStatusReason() { return statusReason; }
    public void setStatusReason(String statusReason) { this.statusReason = statusReason; }

    public String getEngagedPartyId() { return engagedPartyId; }
    public void setEngagedPartyId(String engagedPartyId) { this.engagedPartyId = engagedPartyId; }

    public String getEngagedPartyHref() { return engagedPartyHref; }
    public void setEngagedPartyHref(String engagedPartyHref) { this.engagedPartyHref = engagedPartyHref; }

    public String getEngagedPartyName() { return engagedPartyName; }
    public void setEngagedPartyName(String engagedPartyName) { this.engagedPartyName = engagedPartyName; }

    public String getEngagedPartyType() { return engagedPartyType; }
    public void setEngagedPartyType(String engagedPartyType) { this.engagedPartyType = engagedPartyType; }

    public TimePeriod getValidFor() { return validFor; }
    public void setValidFor(TimePeriod validFor) { this.validFor = validFor; }

    public String getAtBaseType() { return atBaseType; }
    public void setAtBaseType(String atBaseType) { this.atBaseType = atBaseType; }

    public URI getAtSchemaLocation() { return atSchemaLocation; }
    public void setAtSchemaLocation(URI atSchemaLocation) { this.atSchemaLocation = atSchemaLocation; }

    public String getAtType() { return atType; }
    public void setAtType(String atType) { this.atType = atType; }
}
