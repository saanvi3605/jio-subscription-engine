package com.jio.party.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import io.swagger.v3.oas.annotations.media.Schema;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Individual represents a single human being (a party).
 * TMF632 Party Management API resource.
 *
 * Table: individual
 * Child tables:
 *   individual_contact_medium  (FK → individual.id)
 *   individual_identification  (FK → individual.id)
 */
@Entity
@Table(name = "individual")
@Schema(name = "Individual", description = "A single human being — a party in the subscription engine")
public class Individual {

    // ── Primary Key (UUID auto-generated) ──────────────────────────────────
    @Id
    @UuidGenerator
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    // ── Core name fields ────────────────────────────────────────────────────
    /** Honorific prefix: Mr, Mrs, Ms, Dr, Prof */
    @JsonProperty("title")
    private @Nullable String title;

    @NotBlank
    @JsonProperty("givenName")
    private String givenName;

    @JsonProperty("middleName")
    private @Nullable String middleName;

    @NotBlank
    @JsonProperty("familyName")
    private String familyName;

    /** Full formatted name, e.g. "Dr. Saanvi Sharma" */
    @JsonProperty("fullName")
    private @Nullable String fullName;

    // ── Demographics ────────────────────────────────────────────────────────
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonProperty("birthDate")
    private @Nullable OffsetDateTime birthDate;

    /** MALE, FEMALE, OTHER, UNDISCLOSED */
    @JsonProperty("gender")
    private @Nullable String gender;

    @JsonProperty("maritalStatus")
    private @Nullable String maritalStatus;

    @JsonProperty("nationality")
    private @Nullable String nationality;

    @JsonProperty("placeOfBirth")
    private @Nullable String placeOfBirth;

    @JsonProperty("countryOfBirth")
    private @Nullable String countryOfBirth;

    // ── Lifecycle ───────────────────────────────────────────────────────────
    /**
     * Status: initialized | validated | bankrupt | deceased
     * Defaults to "initialized" on creation.
     */
    @JsonProperty("status")
    private String status = "initialized";

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonProperty("createdAt")
    private OffsetDateTime createdAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonProperty("updatedAt")
    private @Nullable OffsetDateTime updatedAt;

    // ── Contact mediums (phone, email, address) ─────────────────────────────
    @Valid
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "individual_contact_medium",
        joinColumns = @JoinColumn(name = "individual_id")
    )
    @JsonProperty("contactMedium")
    private List<ContactMedium> contactMedium = new ArrayList<>();

    // ── Identity documents ──────────────────────────────────────────────────
    @Valid
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "individual_identification",
        joinColumns = @JoinColumn(name = "individual_id")
    )
    @JsonProperty("identification")
    private List<Identification> identification = new ArrayList<>();

    // ── TM Forum standard extension fields ─────────────────────────────────
    @JsonProperty("@baseType")
    private @Nullable String atBaseType;

    /** URI to a JSON-Schema — not persisted, only in API layer */
    @Transient
    @JsonProperty("@schemaLocation")
    private @Nullable URI atSchemaLocation;

    @JsonProperty("@type")
    private @Nullable String atType;

    // ── Constructors ────────────────────────────────────────────────────────
    public Individual() {}

    public Individual(String givenName, String familyName) {
        this.givenName  = givenName;
        this.familyName = familyName;
    }

    // ── Getters & Setters ───────────────────────────────────────────────────
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGivenName() { return givenName; }
    public void setGivenName(String givenName) { this.givenName = givenName; }

    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public String getFamilyName() { return familyName; }
    public void setFamilyName(String familyName) { this.familyName = familyName; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public OffsetDateTime getBirthDate() { return birthDate; }
    public void setBirthDate(OffsetDateTime birthDate) { this.birthDate = birthDate; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getMaritalStatus() { return maritalStatus; }
    public void setMaritalStatus(String maritalStatus) { this.maritalStatus = maritalStatus; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getPlaceOfBirth() { return placeOfBirth; }
    public void setPlaceOfBirth(String placeOfBirth) { this.placeOfBirth = placeOfBirth; }

    public String getCountryOfBirth() { return countryOfBirth; }
    public void setCountryOfBirth(String countryOfBirth) { this.countryOfBirth = countryOfBirth; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }

    public List<ContactMedium> getContactMedium() { return contactMedium; }
    public void setContactMedium(List<ContactMedium> contactMedium) { this.contactMedium = contactMedium; }

    public List<Identification> getIdentification() { return identification; }
    public void setIdentification(List<Identification> identification) { this.identification = identification; }

    public String getAtBaseType() { return atBaseType; }
    public void setAtBaseType(String atBaseType) { this.atBaseType = atBaseType; }

    public URI getAtSchemaLocation() { return atSchemaLocation; }
    public void setAtSchemaLocation(URI atSchemaLocation) { this.atSchemaLocation = atSchemaLocation; }

    public String getAtType() { return atType; }
    public void setAtType(String atType) { this.atType = atType; }
}
