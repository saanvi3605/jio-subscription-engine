package com.jio.usage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TMF635 Usage Management Service
 * Port: 8085  |  DB: jio_usage
 *
 * Endpoints:
 *   /tmf-api/usageManagement/v4/usage
 *   /swagger-ui/index.html
 *
 * Cross-service references:
 *   customerId → TMF629 jio_customer.customer.id
 *   productId  → TMF637 jio_product_inventory.product.id
 */
@SpringBootApplication
public class UsageManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(UsageManagementApplication.class, args);
    }
}
