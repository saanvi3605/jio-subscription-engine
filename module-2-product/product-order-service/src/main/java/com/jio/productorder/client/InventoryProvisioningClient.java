package com.jio.productorder.client;

import org.openapitools.model.ProductOrder;
import org.openapitools.model.ProductOrderItem;
import org.openapitools.model.RelatedParty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * HTTP client for TMF637 Product Inventory Service (port 8084).
 *
 * Called by ProductOrderApiController when an order's state is patched
 * to COMPLETED. For each order item that references a product offering,
 * this client creates a Product record in TMF637 with:
 *
 *   - productOrderId     ← the completed order's id  (FIXES THE NULLS)
 *   - productOfferingId  ← from the order item's productOffering.id
 *   - productOfferingName
 *   - customerId         ← extracted from order's relatedParty list
 *   - customerName
 *   - name               ← productOffering.name (falls back to order description)
 *   - status             ← "active"
 */
@Component
public class InventoryProvisioningClient {

    private static final Logger log = LoggerFactory.getLogger(InventoryProvisioningClient.class);

    private final RestTemplate restTemplate;

    @Value("${services.tmf637.url}")
    private String inventoryServiceUrl;

    public InventoryProvisioningClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * For each ProductOrderItem that has a productOffering, POST a Product
     * to TMF637. Fires-and-forgets per item — failures produce warnings only.
     */
    public void provisionInventory(ProductOrder order) {
        // Extract customer from relatedParty (role = "customer" OR @referredType = "Customer")
        String customerId = null;
        String customerName = null;
        for (RelatedParty rp : order.getRelatedParty()) {
            boolean isCustomer = "customer".equalsIgnoreCase(rp.getRole())
                || "Customer".equals(rp.getAtReferredType());
            if (isCustomer) {
                customerId = rp.getId();
                customerName = rp.getName();
                break;
            }
        }

        if (customerId == null) {
            log.warn("[TMF622→TMF637] Order {} has no relatedParty with role='customer' — " +
                     "inventory not auto-provisioned. " +
                     "Add a relatedParty entry with role='customer' and @referredType='Customer'.",
                     order.getId());
            return;
        }

        int provisioned = 0;
        for (ProductOrderItem item : order.getProductOrderItem()) {
            if (item.getProductOffering() == null) {
                log.debug("[TMF622→TMF637] Order item {} has no productOffering, skipping", item.getId());
                continue;
            }

            String offeringId   = item.getProductOffering().getId();
            String offeringName = item.getProductOffering().getName();
            String productName  = offeringName != null ? offeringName
                                : (order.getDescription() != null ? order.getDescription()
                                : "Product from order " + order.getId());

            Map<String, Object> body = new HashMap<>();
            body.put("name",                productName);
            body.put("customerId",          customerId);
            body.put("customerName",        customerName);
            body.put("productOrderId",      order.getId());
            body.put("productOfferingId",   offeringId);
            body.put("productOfferingName", offeringName);
            body.put("status",              "active");

            try {
                Object result = restTemplate.postForObject(
                    inventoryServiceUrl + "/tmf-api/productInventoryManagement/v4/product",
                    body,
                    Object.class
                );
                log.info("[TMF622→TMF637] Provisioned inventory item for order {} / offering {} (customer {})",
                         order.getId(), offeringId, customerId);
                provisioned++;
            } catch (Exception e) {
                log.warn("[TMF622→TMF637] Failed to provision inventory for order {} / offering {}: {}",
                         order.getId(), offeringId, e.getMessage());
            }
        }

        if (provisioned == 0 && !order.getProductOrderItem().isEmpty()) {
            log.warn("[TMF622→TMF637] Order {} completed but no inventory items were provisioned — " +
                     "ensure each orderItem has a productOffering with an id.", order.getId());
        }
    }
}
