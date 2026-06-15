package com.jio.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response from payment initiation — pass these values to Razorpay Checkout JS")
public class PaymentInitiateResponse {

    @JsonProperty("internalPaymentId")
    @Schema(description = "Internal TMF676 payment record ID — store this to track the payment")
    private String internalPaymentId;

    @JsonProperty("razorpayOrderId")
    @Schema(description = "Razorpay order ID — pass as 'order_id' to Razorpay Checkout")
    private String razorpayOrderId;

    @JsonProperty("amountInPaise")
    @Schema(description = "Amount in paise (INR × 100) — pass as 'amount' to Razorpay Checkout")
    private long amountInPaise;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("razorpayKeyId")
    @Schema(description = "Razorpay publishable key — pass as 'key' to Razorpay Checkout")
    private String razorpayKeyId;

    public PaymentInitiateResponse(String internalPaymentId, String razorpayOrderId,
                                   long amountInPaise, String currency, String razorpayKeyId) {
        this.internalPaymentId = internalPaymentId;
        this.razorpayOrderId = razorpayOrderId;
        this.amountInPaise = amountInPaise;
        this.currency = currency;
        this.razorpayKeyId = razorpayKeyId;
    }

    public String getInternalPaymentId()        { return internalPaymentId; }
    public String getRazorpayOrderId()          { return razorpayOrderId; }
    public long getAmountInPaise()              { return amountInPaise; }
    public String getCurrency()                 { return currency; }
    public String getRazorpayKeyId()            { return razorpayKeyId; }
}
