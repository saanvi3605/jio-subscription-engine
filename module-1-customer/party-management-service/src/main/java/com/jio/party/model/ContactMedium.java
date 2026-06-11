package com.jio.party.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;

/**
 * Indicates the contact medium that could be used to contact the party.
 * TMF632 — stored as a collection table row per individual.
 */
@Embeddable
public class ContactMedium {

    /**
     * Type of the contact medium: mobile, home, work, email, postalAddress
     */
    @NotBlank
    @JsonProperty("contactType")
    private String contactType;

    /** True if this is the preferred contact medium */
    @JsonProperty("preferred")
    private boolean preferred;

    /** Phone number (for mobile/home/work contactType) */
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    /** Email address (for email contactType) */
    @JsonProperty("emailAddress")
    private String emailAddress;

    /** Street line 1 (for postalAddress contactType) */
    @JsonProperty("street1")
    private String street1;

    @JsonProperty("street2")
    private String street2;

    @JsonProperty("city")
    private String city;

    @JsonProperty("stateOrProvince")
    private String stateOrProvince;

    @JsonProperty("postCode")
    private String postCode;

    @JsonProperty("country")
    private String country;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "cm_valid_from")
    private OffsetDateTime validFrom;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "cm_valid_to")
    private OffsetDateTime validTo;

    public ContactMedium() {}

    // ── Getters & Setters ──────────────────────────────────────────────────

    public String getContactType() { return contactType; }
    public void setContactType(String contactType) { this.contactType = contactType; }

    public boolean isPreferred() { return preferred; }
    public void setPreferred(boolean preferred) { this.preferred = preferred; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    public String getStreet1() { return street1; }
    public void setStreet1(String street1) { this.street1 = street1; }

    public String getStreet2() { return street2; }
    public void setStreet2(String street2) { this.street2 = street2; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getStateOrProvince() { return stateOrProvince; }
    public void setStateOrProvince(String stateOrProvince) { this.stateOrProvince = stateOrProvince; }

    public String getPostCode() { return postCode; }
    public void setPostCode(String postCode) { this.postCode = postCode; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public OffsetDateTime getValidFrom() { return validFrom; }
    public void setValidFrom(OffsetDateTime validFrom) { this.validFrom = validFrom; }

    public OffsetDateTime getValidTo() { return validTo; }
    public void setValidTo(OffsetDateTime validTo) { this.validTo = validTo; }
}
