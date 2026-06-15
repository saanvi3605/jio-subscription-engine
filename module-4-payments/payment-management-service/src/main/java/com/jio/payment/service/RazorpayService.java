package com.jio.payment.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RazorpayService {

    @Value("${razorpay.key-id}")
    private String keyId;

    @Value("${razorpay.webhook-secret}")
    private String webhookSecret;

    private final RazorpayClient razorpayClient;

    public RazorpayService(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    /**
     * Creates a Razorpay order. Amount must be in paise (INR × 100).
     * Receipt is a unique reference — we use the internal payment ID.
     */
    public Order createOrder(long amountInPaise, String currency, String receiptId) throws RazorpayException {
        JSONObject req = new JSONObject();
        req.put("amount", amountInPaise);
        req.put("currency", currency);
        req.put("receipt", receiptId);
        return razorpayClient.orders.create(req);
    }

    /**
     * Verifies Razorpay webhook signature using HMAC-SHA256.
     * Razorpay sends X-Razorpay-Signature header with the signed raw body.
     */
    public boolean verifyWebhookSignature(String rawBody, String signature) {
        try {
            return Utils.verifyWebhookSignature(rawBody, signature, webhookSecret);
        } catch (RazorpayException e) {
            return false;
        }
    }

    public String getKeyId() {
        return keyId;
    }
}
