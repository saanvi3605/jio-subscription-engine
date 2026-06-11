package com.jio.productorder.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * HTTP client for TMF620 Product Catalog Service (port 8081).
 *
 * Used by ProductOrderApiController to validate that every productOffering
 * referenced in an order's line items actually exists in the catalog before
 * the order is accepted. Prevents orders for non-existent products.
 */
@Component
public class CatalogValidationClient {

    private static final Logger log = LoggerFactory.getLogger(CatalogValidationClient.class);

    private final RestTemplate restTemplate;

    @Value("${services.tmf620.url}")
    private String catalogServiceUrl;

    public CatalogValidationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Returns true if the ProductOffering with the given id exists in TMF620.
     * Returns false on 404 (offering genuinely missing from catalog).
     * Returns true (graceful degradation) if the catalog service is unreachable.
     */
    public boolean offeringExists(String offeringId) {
        try {
            restTemplate.getForObject(
                catalogServiceUrl + "/tmf-api/productCatalogManagement/v4/productOffering/" + offeringId,
                Object.class
            );
            return true;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.warn("[TMF622→TMF620] ProductOffering {} not found in catalog", offeringId);
                return false;
            }
            log.warn("[TMF622→TMF620] Catalog service returned {}: {}", e.getStatusCode(), e.getMessage());
            return true;
        } catch (Exception e) {
            log.warn("[TMF622→TMF620] Catalog service unreachable ({}), proceeding without validation", e.getMessage());
            return true;
        }
    }
}
