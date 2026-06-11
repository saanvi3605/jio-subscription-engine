package com.jio.productorder.client;

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
 * Used by ProductOrderApiController to validate that the customer
 * listed in relatedParty exists before accepting a product order.
 */
@Component
public class CustomerValidationClient {

    private static final Logger log = LoggerFactory.getLogger(CustomerValidationClient.class);

    private final RestTemplate restTemplate;

    @Value("${services.tmf629.url}")
    private String customerServiceUrl;

    public CustomerValidationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Returns true if the Customer with the given id exists in TMF629.
     * Returns false on 404.
     * Returns true (graceful degradation) if the service is unreachable.
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
                log.warn("[TMF622→TMF629] Customer {} not found in customer service", customerId);
                return false;
            }
            log.warn("[TMF622→TMF629] Customer service returned {}: {}", e.getStatusCode(), e.getMessage());
            return true; // graceful degradation
        } catch (Exception e) {
            log.warn("[TMF622→TMF629] Customer service unreachable ({}), proceeding without validation", e.getMessage());
            return true; // graceful degradation
        }
    }
}
