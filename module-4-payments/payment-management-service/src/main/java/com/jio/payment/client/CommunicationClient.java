package com.jio.payment.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * HTTP client for TMF681 Communication Management Service (:8088).
 *
 * Wire 17: TMF676 → TMF681
 * Sends a payment receipt SMS when a payment transitions to SETTLED.
 * Fire-and-forget — failure only logs a warning.
 */
@Component
public class CommunicationClient {

    private static final Logger log = LoggerFactory.getLogger(CommunicationClient.class);

    private final RestTemplate restTemplate;

    @Value("${services.tmf681.url}")
    private String communicationServiceUrl;

    public CommunicationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Wire 17: send payment receipt when payment reaches SETTLED state.
     */
    public void sendPaymentReceipt(String paymentId, String customerId, Double amount, String currency) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String amountStr = (amount != null ? String.valueOf(amount) : "0") +
                               " " + (currency != null ? currency : "INR");

            Map<String, Object> body = new HashMap<>();
            body.put("type",               "SMS");
            body.put("subject",            "Jio payment received");
            body.put("content",            "Payment of " + amountStr + " has been received and settled. " +
                                           "Payment ID: " + paymentId + ". Thank you for paying on time!");
            body.put("customerId",         customerId);
            body.put("relatedEntityType",  "Payment");
            body.put("relatedEntityId",    paymentId);
            body.put("sender",             "Jio");

            restTemplate.postForObject(
                communicationServiceUrl + "/tmf-api/communicationManagement/v4/communicationMessage",
                new HttpEntity<>(body, headers),
                Object.class
            );
            log.info("[TMF676→TMF681] Payment receipt SMS queued for payment {} / customer {}", paymentId, customerId);
        } catch (Exception e) {
            log.warn("[TMF676→TMF681] Could not send payment receipt for payment {}: {}", paymentId, e.getMessage());
        }
    }
}
