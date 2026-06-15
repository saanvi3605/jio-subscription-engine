package com.jio.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.lang.Nullable;

@Schema(description = "Request to initiate a Razorpay payment session")
public class PaymentInitiateRequest {

    @NotBlank
    @JsonProperty("customerId")
    @Schema(description = "TMF629 customer ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private String customerId;

    @NotBlank
    @JsonProperty("customerAccountId")
    @Schema(description = "TMF629 customer account ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private String customerAccountId;

    @NotNull
    @Positive
    @JsonProperty("amount")
    @Schema(description = "Amount in INR — e.g. 999.00", requiredMode = Schema.RequiredMode.REQUIRED)
    private Double amount;

    @JsonProperty("currency")
    private String currency = "INR";

    @NotBlank
    @JsonProperty("paymentMethodType")
    @Schema(description = "UPI | CREDIT_CARD | DEBIT_CARD | NET_BANKING | WALLET", requiredMode = Schema.RequiredMode.REQUIRED)
    private String paymentMethodType;

    @Nullable
    @JsonProperty("paymentMethodId")
    @Schema(description = "Optional TMF671 payment method ID")
    private String paymentMethodId;

    @Nullable
    @JsonProperty("productId")
    @Schema(description = "Optional TMF637 product ID")
    private String productId;

    @Nullable
    @JsonProperty("description")
    private String description;

    public String getCustomerId()           { return customerId; }
    public void setCustomerId(String v)     { this.customerId = v; }

    public String getCustomerAccountId()        { return customerAccountId; }
    public void setCustomerAccountId(String v)  { this.customerAccountId = v; }

    public Double getAmount()               { return amount; }
    public void setAmount(Double v)         { this.amount = v; }

    public String getCurrency()             { return currency; }
    public void setCurrency(String v)       { this.currency = v; }

    public String getPaymentMethodType()        { return paymentMethodType; }
    public void setPaymentMethodType(String v)  { this.paymentMethodType = v; }

    public String getPaymentMethodId()          { return paymentMethodId; }
    public void setPaymentMethodId(String v)    { this.paymentMethodId = v; }

    public String getProductId()            { return productId; }
    public void setProductId(String v)      { this.productId = v; }

    public String getDescription()          { return description; }
    public void setDescription(String v)    { this.description = v; }
}
