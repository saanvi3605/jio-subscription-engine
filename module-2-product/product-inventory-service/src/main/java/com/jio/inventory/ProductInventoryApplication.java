package com.jio.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TMF637 Product Inventory Management Service
 * Port: 8084  |  DB: jio_product_inventory
 *
 * Endpoints:
 *   /tmf-api/productInventoryManagement/v4/product
 *   /swagger-ui/index.html
 *
 * Cross-service references:
 *   customerId        → TMF629 jio_customer.customer.id
 *   productOrderId    → TMF622 jio_product_order.product_order.id
 *   productOfferingId → TMF620 jio_product_catalog offering
 */
@SpringBootApplication
public class ProductInventoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductInventoryApplication.class, args);
    }
}
