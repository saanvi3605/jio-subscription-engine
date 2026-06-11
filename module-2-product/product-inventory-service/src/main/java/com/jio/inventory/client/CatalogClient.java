package com.jio.inventory.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * HTTP client for TMF620 Product Catalog Service (port 8081).
 * Used by ProductService to validate that a ProductOffering exists in the
 * catalog before creating an inventory item that references it.
 */
@Component
public class CatalogClient {

    private static final Logger log = LoggerFactory.getLogger(CatalogClient.class);

    private final RestTemplate restTemplate;

    @Value("${services.tmf620.url}")
    private String catalogServiceUrl;

    public CatalogClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean offeringExists(String offeringId) {
        try {
            restTemplate.getForObject(
                catalogServiceUrl + "/tmf-api/productCatalogManagement/v4/productOffering/" + offeringId,
                Object.class
            );
            return true;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.warn("[TMF637→TMF620] ProductOffering {} not found in catalog", offeringId);
                return false;
            }
            log.warn("[TMF637→TMF620] Catalog service returned {}: {}", e.getStatusCode(), e.getMessage());
            return true;
        } catch (Exception e) {
            log.warn("[TMF637→TMF620] Catalog service unreachable ({}), proceeding", e.getMessage());
            return true;
        }
    }
}
