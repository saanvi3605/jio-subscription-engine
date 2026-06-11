package com.jio.processflow.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class PaymentMethodClient {

    private final RestTemplate restTemplate;

    @Value("${services.tmf671.url}")
    private String url;

    public PaymentMethodClient(RestTemplate restTemplate) { this.restTemplate = restTemplate; }

    @SuppressWarnings("unchecked")
    public String createPaymentMethod(String customerId, String type, String detail) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of(
            "customerId",          customerId,
            "paymentMethodType",   type   != null ? type   : "UPI",
            "paymentMethodDetail", detail != null ? detail : ""
        );

        Map<String, Object> response = restTemplate.postForObject(
            url + "/tmf-api/paymentMethodManagement/v4/paymentMethod",
            new HttpEntity<>(body, headers),
            Map.class
        );
        return (String) response.get("id");
    }
}
