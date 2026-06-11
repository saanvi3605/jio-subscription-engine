package com.jio.inventory.client;

import com.jio.inventory.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * HTTP client for TMF635 Usage Management Service (port 8085).
 *
 * When a Product is activated in TMF637, this client automatically creates
 * a ProductUsage tracker in TMF635 so that data consumption can be
 * measured against the product from day one.
 *
 * One tracker is initialised per product with:
 *   usageType = DATA, consumed = 0.0, unit = GB, status = active
 *
 * The allowance field is left null (unlimited) until it is populated
 * by the billing system using the plan details from TMF620.
 */
@Component
public class UsageClient {

    private static final Logger log = LoggerFactory.getLogger(UsageClient.class);

    private final RestTemplate restTemplate;

    @Value("${services.tmf635.url}")
    private String usageServiceUrl;

    public UsageClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * POST a new ProductUsage tracker for the given product.
     * Fires-and-forgets — any failure only produces a warning log.
     * The product creation itself is never rolled back due to a usage init failure.
     */
    public void initProductUsage(Product product) {
        Map<String, Object> body = new HashMap<>();
        body.put("productId",    product.getId());
        body.put("productName",  product.getName());
        body.put("customerId",   product.getCustomerId());
        body.put("customerName", product.getCustomerName());
        body.put("usageType",    "DATA");
        body.put("consumed",     0.0);
        body.put("unit",         "GB");
        body.put("status",       "active");

        try {
            restTemplate.postForObject(
                usageServiceUrl + "/tmf-api/usageManagement/v4/productUsage",
                body,
                Object.class
            );
            log.info("[TMF637→TMF635] ProductUsage tracker initialised for product {}", product.getId());
        } catch (Exception e) {
            log.warn("[TMF637→TMF635] Could not initialise ProductUsage for product {} ({}). " +
                     "Tracker can be created manually via POST /tmf-api/usageManagement/v4/productUsage",
                     product.getId(), e.getMessage());
        }
    }
}
