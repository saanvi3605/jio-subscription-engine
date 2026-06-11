package com.jio.paymentmethod.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * HTTP client for TMF629 Customer Management Service (port 8083).
 *
 * Used by PaymentMethodService to validate that a Customer exists
 * before adding a payment method to their account.
 */
@Component
public class CustomerClient {

    private static final Logger log = LoggerFactory.getLogger(CustomerClient.class);

    private final RestTemplate restTemplate;

    @Value("${services.tmf629.url}")
    private String customerServiceUrl;

    public CustomerClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Returns true if the customer with the given id exists in TMF629.
     * Returns false on 404.
     * Returns true (graceful degradation) if service is unreachable.
     */
    public boolean customerExists(String customerId) {
        try {
            restTemplate.getForObject(
                customerServiceUrl + "/tmf-api/customerManagement/v4/customer/" + customerId,
                Object.class
            );
            return true;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.warn("[TMF671→TMF629] Customer {} not found in customer service", customerId);
                return false;
            }
            log.warn("[TMF671→TMF629] Customer service returned {}: {}", e.getStatusCode(), e.getMessage());
            return true; // graceful degradation
        } catch (Exception e) {
            log.warn("[TMF671→TMF629] Customer service unreachable ({}), proceeding without validation", e.getMessage());
            return true; // graceful degradation if service is down
        }
    }
}
