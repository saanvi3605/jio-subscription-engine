package com.jio.paymentmethod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TMF671 Payment Method Management Service
 * Port: 8087  |  DB: jio_payment_method
 *
 * Endpoints:
 *   /tmf-api/paymentMethodManagement/v4/paymentMethod
 *   /swagger-ui/index.html
 *
 * Cross-service references:
 *   customerId → TMF629 jio_customer.customer.id
 *
 * Referenced by:
 *   TMF676 payment.paymentMethodId → this service's paymentMethod.id
 */
@SpringBootApplication
public class PaymentMethodApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMethodApplication.class, args);
    }
}
