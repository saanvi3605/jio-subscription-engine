package com.jio.customer.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * HTTP client for TMF632 Party Management Service (port 8082).
 *
 * Used by CustomerService to validate that an Individual exists
 * before creating a Customer record.
 *
 * Graceful degradation: if the party service is unreachable,
 * a warning is logged and the call proceeds (availability > consistency).
 */
@Component
public class PartyClient {

    private static final Logger log = LoggerFactory.getLogger(PartyClient.class);

    private final RestTemplate restTemplate;

    @Value("${services.tmf632.url}")
    private String partyServiceUrl;

    public PartyClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Creates an Individual in TMF632 and returns the new individual's id.
     * Called during customer signup so the TMF632→TMF629 link is established.
     * Throws RuntimeException if the party service is unreachable.
     */
    public String createIndividual(String givenName, String familyName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> body = Map.of(
            "givenName",  givenName,
            "familyName", familyName
        );
        @SuppressWarnings("unchecked")
        Map<String, Object> response = restTemplate.postForObject(
            partyServiceUrl + "/tmf-api/partyManagement/v4/individual",
            new HttpEntity<>(body, headers),
            Map.class
        );
        if (response == null || !response.containsKey("id")) {
            throw new RuntimeException("Party service returned no id for new Individual");
        }
        return (String) response.get("id");
    }

    /**
     * Returns true if the individual with the given id exists in TMF632.
     * Returns false if a 404 is received.
     * Returns true (graceful degradation) if the party service is unreachable.
     */
    public boolean individualExists(String individualId) {
        try {
            restTemplate.getForObject(
                partyServiceUrl + "/tmf-api/partyManagement/v4/individual/" + individualId,
                Object.class
            );
            return true;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.warn("[TMF629→TMF632] Individual {} not found in party service", individualId);
                return false;
            }
            log.warn("[TMF629→TMF632] Party service returned {}: {}", e.getStatusCode(), e.getMessage());
            return true; // graceful degradation for non-404 errors
        } catch (Exception e) {
            log.warn("[TMF629→TMF632] Party service unreachable ({}), proceeding without validation", e.getMessage());
            return true; // graceful degradation if service is down
        }
    }
}
