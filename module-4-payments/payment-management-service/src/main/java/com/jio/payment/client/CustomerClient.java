package com.jio.payment.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

/**
 * HTTP client for TMF629 Customer Management Service (port 8083).
 *
 * Used by PaymentService to:
 *   1. Validate the customer exists before accepting a payment
 *   2. Validate the customer account exists before recording the payment
 *   3. Write-back: when a payment is settled, update the CustomerAccount's
 *      paymentStatus to "PAID" so the billing record reflects the settlement
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

    /** Validate that the Customer exists in TMF629. */
    public boolean customerExists(String customerId) {
        return exists(customerServiceUrl + "/tmf-api/customerManagement/v4/customer/" + customerId,
                      "TMF676→TMF629 customer", customerId);
    }

    /** Validate that the CustomerAccount exists in TMF629. */
    public boolean customerAccountExists(String customerAccountId) {
        return exists(customerServiceUrl + "/tmf-api/customerManagement/v4/customerAccount/" + customerAccountId,
                      "TMF676→TMF629 customerAccount", customerAccountId);
    }

    /**
     * Wire F write-back: when a payment is settled, PATCH the CustomerAccount's
     * paymentStatus to "PAID" in TMF629.
     * Fires-and-forgets — failure only produces a warning log.
     */
    public void markAccountPaid(String customerAccountId) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, String> body = Collections.singletonMap("paymentStatus", "PAID");
            HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

            restTemplate.exchange(
                customerServiceUrl + "/tmf-api/customerManagement/v4/customerAccount/" + customerAccountId,
                HttpMethod.PATCH,
                entity,
                Object.class
            );
            log.info("[TMF676→TMF629] CustomerAccount {} paymentStatus set to PAID", customerAccountId);
        } catch (Exception e) {
            log.warn("[TMF676→TMF629] Could not update CustomerAccount {} paymentStatus: {}",
                     customerAccountId, e.getMessage());
        }
    }

    private boolean exists(String url, String label, String id) {
        try {
            restTemplate.getForObject(url, Object.class);
            return true;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.warn("[{}] {} not found", label, id);
                return false;
            }
            log.warn("[{}] Service returned {}: {}", label, e.getStatusCode(), e.getMessage());
            return true;
        } catch (Exception e) {
            log.warn("[{}] Service unreachable ({}), proceeding without validation", label, e.getMessage());
            return true;
        }
    }
}
