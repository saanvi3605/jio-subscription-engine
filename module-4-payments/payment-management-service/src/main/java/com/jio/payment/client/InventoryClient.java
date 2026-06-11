package com.jio.payment.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * HTTP client for TMF637 Product Inventory Service (port 8084).
 *
 * Used by PaymentService to confirm the Product being paid for actually
 * exists in the customer's inventory before recording the payment.
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

    /** Returns true if the product with the given id exists in TMF637. */
    public boolean productExists(String productId) {
        try {
            restTemplate.getForObject(
                inventoryServiceUrl + "/tmf-api/productInventoryManagement/v4/product/" + productId,
                Object.class
            );
            return true;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.warn("[TMF676→TMF637] Product {} not found in inventory service", productId);
                return false;
            }
            log.warn("[TMF676→TMF637] Inventory service returned {}: {}", e.getStatusCode(), e.getMessage());
            return true; // graceful degradation
        } catch (Exception e) {
            log.warn("[TMF676→TMF637] Inventory service unreachable ({}), proceeding without validation", e.getMessage());
            return true; // graceful degradation if service is down
        }
    }
}
