package com.jio.payment.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * HTTP client for TMF671 Payment Method Service (port 8087).
 *
 * Used by PaymentService to confirm the PaymentMethod referenced in a
 * payment actually exists before recording the payment.
 */
@Component
public class PaymentMethodClient {

    private static final Logger log = LoggerFactory.getLogger(PaymentMethodClient.class);

    private final RestTemplate restTemplate;

    @Value("${services.tmf671.url}")
    private String paymentMethodServiceUrl;

    public PaymentMethodClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /** Returns true if the paymentMethod with the given id exists in TMF671. */
    public boolean paymentMethodExists(String paymentMethodId) {
        try {
            restTemplate.getForObject(
                paymentMethodServiceUrl + "/tmf-api/paymentMethodManagement/v4/paymentMethod/" + paymentMethodId,
                Object.class
            );
            return true;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.warn("[TMF676→TMF671] PaymentMethod {} not found in payment-method service", paymentMethodId);
                return false;
            }
            log.warn("[TMF676→TMF671] Payment-method service returned {}: {}", e.getStatusCode(), e.getMessage());
            return true; // graceful degradation
        } catch (Exception e) {
            log.warn("[TMF676→TMF671] Payment-method service unreachable ({}), proceeding without validation", e.getMessage());
            return true; // graceful degradation if service is down
        }
    }
}
