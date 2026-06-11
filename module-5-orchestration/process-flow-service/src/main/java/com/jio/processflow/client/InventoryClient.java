package com.jio.processflow.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class InventoryClient {

    private final RestTemplate restTemplate;

    @Value("${services.tmf637.url}")
    private String url;

    public InventoryClient(RestTemplate restTemplate) { this.restTemplate = restTemplate; }

    /**
     * Polls TMF637 for a product with the given productOrderId.
     * Returns the productId if found, null otherwise.
     */
    @SuppressWarnings("unchecked")
    public String findProductByOrderId(String orderId) {
        try {
            List<Map<String, Object>> products = restTemplate.exchange(
                url + "/tmf-api/productInventory/v4/product?productOrderId=" + orderId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {}
            ).getBody();

            if (products != null && !products.isEmpty()) {
                return (String) products.get(0).get("id");
            }
            return null;
        } catch (Exception e) { return null; }
    }
}
