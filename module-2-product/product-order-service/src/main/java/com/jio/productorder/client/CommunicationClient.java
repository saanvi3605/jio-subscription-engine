package com.jio.productorder.client;

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
 * Wire 16: TMF622 → TMF681
 * Sends an order confirmation SMS when an order transitions to COMPLETED.
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
     * Wire 16: send order confirmation when order reaches COMPLETED state.
     */
    public void sendOrderConfirmation(String orderId, String customerId) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> body = new HashMap<>();
            body.put("type",               "SMS");
            body.put("subject",            "Your Jio order is confirmed!");
            body.put("content",            "Your Jio order " + orderId +
                                           " has been completed and your plan is now active. " +
                                           "Thank you for choosing Jio!");
            body.put("customerId",         customerId);
            body.put("relatedEntityType",  "Order");
            body.put("relatedEntityId",    orderId);
            body.put("sender",             "Jio");

            restTemplate.postForObject(
                communicationServiceUrl + "/tmf-api/communicationManagement/v4/communicationMessage",
                new HttpEntity<>(body, headers),
                Object.class
            );
            log.info("[TMF622→TMF681] Order confirmation SMS queued for order {} / customer {}", orderId, customerId);
        } catch (Exception e) {
            log.warn("[TMF622→TMF681] Could not send order confirmation for order {}: {}", orderId, e.getMessage());
        }
    }
}
