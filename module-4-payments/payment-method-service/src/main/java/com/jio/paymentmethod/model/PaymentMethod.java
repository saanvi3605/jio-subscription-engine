package com.jio.paymentmethod.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.lang.Nullable;

import java.net.URI;
import java.time.OffsetDateTime;

/**
 * TMF671 Payment Method Management — PaymentMethod resource.
 *
 * Represents a payment instrument registered by a customer —
 * a UPI ID, credit/debit card, net banking account, or digital wallet.
 *
 * e.g. "Arjun's HDFC Visa ending 4242"
 *      "Rohan's UPI: rohan.mehta@hdfc"
 *      "Saanvi's JioMoney wallet"
 *
 * Type-specific fields are nullable — only the relevant ones
 * are populated based on the type field:
 *   UPI         → upiId
 *   CREDIT_CARD / DEBIT_CARD → cardLast4, cardBrand, cardHolderName, expiryMonth, expiryYear
 *   NET_BANKING → bankName, accountLast4, ifscCode
 *   WALLET      → walletProvider, walletId
 *
 * Cross-service reference:
 *   customerId → jio_customer.customer.id (TMF629)
 *
 * Table: payment_method
 */
@Entity
@Table(name = "payment_method")
@Schema(name = "PaymentMethod", description = "A payment instrument registered by a customer (UPI, card, net banking, wallet)")
public class PaymentMethod {

    // ── Primary Key ──────────────────────────────────────────────────────────
    @Id
    @UuidGenerator
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    // ── Core fields ──────────────────────────────────────────────────────────
    /** Human-readable label — e.g. "Arjun's HDFC Visa", "My UPI" */
    @NotBlank
    @JsonProperty("name")
    private String name;

    /**
     * Instrument type: UPI | CREDIT_CARD | DEBIT_CARD | NET_BANKING | WALLET
     */
    @NotBlank
    @JsonProperty("type")
    private String type;

    /**
     * Status: active | inactive | expired
     */
    @JsonProperty("status")
    private String status = "active";

    /** Whether this is the customer's default payment method */
    @JsonProperty("isDefault")
    private boolean isDefault = false;

    // ── Cross-service reference ───────────────────────────────────────────────
    /** Owner of this method — references jio_customer.customer.id (TMF629) */
    @NotBlank
    @JsonProperty("customerId")
    private String customerId;

    @JsonProperty("customerName")
    private @Nullable String customerName;

    // ── UPI fields ────────────────────────────────────────────────────────────
    /** e.g. arjun.kapoor@jio, rohan@hdfc */
    @JsonProperty("upiId")
    private @Nullable String upiId;

    // ── Card fields ───────────────────────────────────────────────────────────
    /** Last 4 digits only — never store full card number */
    @JsonProperty("cardLast4")
    private @Nullable String cardLast4;

    /** VISA | MASTERCARD | RUPAY | AMEX */
    @JsonProperty("cardBrand")
    private @Nullable String cardBrand;

    @JsonProperty("cardHolderName")
    private @Nullable String cardHolderName;

    @JsonProperty("expiryMonth")
    private @Nullable Integer expiryMonth;

    @JsonProperty("expiryYear")
    private @Nullable Integer expiryYear;

    // ── Net Banking fields ────────────────────────────────────────────────────
    /** e.g. HDFC Bank, SBI, ICICI */
    @JsonProperty("bankName")
    private @Nullable String bankName;

    /** Last 4 digits of account number */
    @JsonProperty("accountLast4")
    private @Nullable String accountLast4;

    @JsonProperty("ifscCode")
    private @Nullable String ifscCode;

    // ── Wallet fields ─────────────────────────────────────────────────────────
    /** PAYTM | PHONEPE | GOOGLEPAY | AMAZONPAY | JIO_MONEY */
    @JsonProperty("walletProvider")
    private @Nullable String walletProvider;

    /** Phone number or wallet account identifier */
    @JsonProperty("walletId")
    private @Nullable String walletId;

    // ── Validity ──────────────────────────────────────────────────────────────
    @JsonProperty("validFrom")
    private @Nullable OffsetDateTime validFrom;

    @JsonProperty("validTo")
    private @Nullable OffsetDateTime validTo;

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
    public PaymentMethod() {}

    // ── Getters & Setters ─────────────────────────────────────────────────────
    public String getId()                            { return id; }
    public void setId(String id)                     { this.id = id; }

    public String getName()                          { return name; }
    public void setName(String name)                 { this.name = name; }

    public String getType()                          { return type; }
    public void setType(String type)                 { this.type = type; }

    public String getStatus()                        { return status; }
    public void setStatus(String status)             { this.status = status; }

    public boolean isDefault()                       { return isDefault; }
    public void setDefault(boolean def)              { this.isDefault = def; }

    public String getCustomerId()                    { return customerId; }
    public void setCustomerId(String cid)            { this.customerId = cid; }

    public String getCustomerName()                  { return customerName; }
    public void setCustomerName(String cn)           { this.customerName = cn; }

    public String getUpiId()                         { return upiId; }
    public void setUpiId(String upiId)               { this.upiId = upiId; }

    public String getCardLast4()                     { return cardLast4; }
    public void setCardLast4(String cl4)             { this.cardLast4 = cl4; }

    public String getCardBrand()                     { return cardBrand; }
    public void setCardBrand(String cb)              { this.cardBrand = cb; }

    public String getCardHolderName()                { return cardHolderName; }
    public void setCardHolderName(String chn)        { this.cardHolderName = chn; }

    public Integer getExpiryMonth()                  { return expiryMonth; }
    public void setExpiryMonth(Integer em)           { this.expiryMonth = em; }

    public Integer getExpiryYear()                   { return expiryYear; }
    public void setExpiryYear(Integer ey)            { this.expiryYear = ey; }

    public String getBankName()                      { return bankName; }
    public void setBankName(String bn)               { this.bankName = bn; }

    public String getAccountLast4()                  { return accountLast4; }
    public void setAccountLast4(String al4)          { this.accountLast4 = al4; }

    public String getIfscCode()                      { return ifscCode; }
    public void setIfscCode(String ifsc)             { this.ifscCode = ifsc; }

    public String getWalletProvider()                { return walletProvider; }
    public void setWalletProvider(String wp)         { this.walletProvider = wp; }

    public String getWalletId()                      { return walletId; }
    public void setWalletId(String wid)              { this.walletId = wid; }

    public OffsetDateTime getValidFrom()             { return validFrom; }
    public void setValidFrom(OffsetDateTime vf)      { this.validFrom = vf; }

    public OffsetDateTime getValidTo()               { return validTo; }
    public void setValidTo(OffsetDateTime vt)        { this.validTo = vt; }

    public OffsetDateTime getCreatedAt()             { return createdAt; }
    public void setCreatedAt(OffsetDateTime ca)      { this.createdAt = ca; }

    public OffsetDateTime getUpdatedAt()             { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime ua)      { this.updatedAt = ua; }

    public String getAtBaseType()                    { return atBaseType; }
    public void setAtBaseType(String v)              { this.atBaseType = v; }

    public URI getAtSchemaLocation()                 { return atSchemaLocation; }
    public void setAtSchemaLocation(URI v)           { this.atSchemaLocation = v; }

    public String getAtType()                        { return atType; }
    public void setAtType(String v)                  { this.atType = v; }
}
