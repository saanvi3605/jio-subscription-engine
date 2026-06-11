package com.jio.processflow.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class OrderClient {

    private final RestTemplate restTemplate;

    @Value("${services.tmf622.url}")
    private String url;

    public OrderClient(RestTemplate restTemplate) { this.restTemplate = restTemplate; }

    @SuppressWarnings("unchecked")
    public String createOrder(String customerId, String customerName, String offeringId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of(
            "description", "Order created by TMF701 process flow",
            "relatedParty", List.of(Map.of(
                "id",            customerId,
                "name",          customerName != null ? customerName : "",
                "role",          "customer",
                "@referredType", "Customer"
            )),
            "productOrderItem", List.of(Map.of(
                "id",              "1",
                "action",          "add",
                "quantity",        1,
                "productOffering", Map.of("id", offeringId)
            ))
        );

        Map<String, Object> response = restTemplate.postForObject(
            url + "/tmf-api/productOrderingManagement/v4/productOrder",
            new HttpEntity<>(body, headers),
            Map.class
        );
        return (String) response.get("id");
    }

    @SuppressWarnings("unchecked")
    public String completeOrder(String orderId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of("state", "completed");

        Map<String, Object> response = restTemplate.exchange(
            url + "/tmf-api/productOrderingManagement/v4/productOrder/" + orderId,
            HttpMethod.PATCH,
            new HttpEntity<>(body, headers),
            Map.class
        ).getBody();

        return response != null ? (String) response.get("state") : "completed";
    }
}
