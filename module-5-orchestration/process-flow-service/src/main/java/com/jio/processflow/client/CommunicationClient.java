package com.jio.processflow.client;

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
 * Wire 18: TMF701 → TMF681
 * Sends a process completion notification when a flow finishes.
 */
@Component
public class CommunicationClient {

    private static final Logger log = LoggerFactory.getLogger(CommunicationClient.class);

    private final RestTemplate restTemplate;

    @Value("${services.tmf681.url}")
    private String url;

    public CommunicationClient(RestTemplate restTemplate) { this.restTemplate = restTemplate; }

    public void sendFlowCompletion(String flowId, String definitionId, String customerId) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> body = new HashMap<>();
            body.put("type",              "SMS");
            body.put("subject",           "Your Jio service is ready");
            body.put("content",           definitionId + " process completed successfully. " +
                                          "All your Jio services are now active. Flow ID: " + flowId);
            body.put("customerId",        customerId);
            body.put("relatedEntityType", "ProcessFlow");
            body.put("relatedEntityId",   flowId);
            body.put("sender",            "Jio");

            restTemplate.postForObject(
                url + "/tmf-api/communicationManagement/v4/communicationMessage",
                new HttpEntity<>(body, headers),
                Object.class
            );
            log.info("[TMF701→TMF681] Flow completion SMS queued for flow {} / customer {}", flowId, customerId);
        } catch (Exception e) {
            log.warn("[TMF701→TMF681] Could not send flow completion message: {}", e.getMessage());
        }
    }
}
