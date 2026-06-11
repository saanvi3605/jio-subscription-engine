package com.jio.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TMF676 Payment Management Service
 * Port: 8086  |  DB: jio_payment
 *
 * Endpoints:
 *   /tmf-api/paymentManagement/v4/payment
 *   /swagger-ui/index.html
 *
 * Cross-service references:
 *   customerId        → TMF629 jio_customer.customer.id
 *   customerAccountId → TMF629 jio_customer.customer_account.id
 *   productId         → TMF637 jio_product_inventory.product.id
 *   paymentMethodId   → TMF671 jio_payment_method (to be built)
 */
@SpringBootApplication
public class PaymentManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentManagementApplication.class, args);
    }
}
