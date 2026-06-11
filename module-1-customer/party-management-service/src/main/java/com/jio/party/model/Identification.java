package com.jio.party.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;

/**
 * Represents a document used to verify the identity of an individual.
 * TMF632 — stored as a collection table row per individual.
 */
@Embeddable
public class Identification {

    /**
     * Type of identity document: AADHAAR, PAN, PASSPORT, DRIVING_LICENSE, VOTER_ID
     */
    @NotBlank
    @JsonProperty("identificationType")
    private String identificationType;

    /** The actual document number */
    @NotBlank
    @JsonProperty("identificationId")
    private String identificationId;

    /** Authority that issued the document (e.g. "UIDAI", "Income Tax Dept") */
    @JsonProperty("issuingAuthority")
    private String issuingAuthority;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "id_issuing_date")
    private OffsetDateTime issuingDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "id_expiration_date")
    private OffsetDateTime expirationDate;

    /** Country where the document was issued */
    @JsonProperty("issuingCountry")
    private String issuingCountry;

    public Identification() {}

    // ── Getters & Setters ──────────────────────────────────────────────────

    public String getIdentificationType() { return identificationType; }
    public void setIdentificationType(String identificationType) { this.identificationType = identificationType; }

    public String getIdentificationId() { return identificationId; }
    public void setIdentificationId(String identificationId) { this.identificationId = identificationId; }

    public String getIssuingAuthority() { return issuingAuthority; }
    public void setIssuingAuthority(String issuingAuthority) { this.issuingAuthority = issuingAuthority; }

    public OffsetDateTime getIssuingDate() { return issuingDate; }
    public void setIssuingDate(OffsetDateTime issuingDate) { this.issuingDate = issuingDate; }

    public OffsetDateTime getExpirationDate() { return expirationDate; }
    public void setExpirationDate(OffsetDateTime expirationDate) { this.expirationDate = expirationDate; }

    public String getIssuingCountry() { return issuingCountry; }
    public void setIssuingCountry(String issuingCountry) { this.issuingCountry = issuingCountry; }
}
