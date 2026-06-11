package com.jio.usage.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * HTTP client for TMF637 Product Inventory Service (port 8084).
 * Used by UsageService to validate that a Product exists before
 * recording a usage event against it.
 */
@Component
public class InventoryClient {

    private static final Logger log = LoggerFactory.getLogger(InventoryClient.class);

    private final RestTemplate restTemplate;

    @Value("${services.tmf637.url}")
    private String inventoryServiceUrl;

    public InventoryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean productExists(String productId) {
        try {
            restTemplate.getForObject(
                inventoryServiceUrl + "/tmf-api/productInventoryManagement/v4/product/" + productId,
                Object.class
            );
            return true;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.warn("[TMF635→TMF637] Product {} not found in inventory", productId);
                return false;
            }
            log.warn("[TMF635→TMF637] Inventory service returned {}: {}", e.getStatusCode(), e.getMessage());
            return true;
        } catch (Exception e) {
            log.warn("[TMF635→TMF637] Inventory service unreachable ({}), proceeding", e.getMessage());
            return true;
        }
    }
}
