package com.jio.processflow.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class CustomerClient {

    private final RestTemplate restTemplate;

    @Value("${services.tmf629.url}")
    private String url;

    public CustomerClient(RestTemplate restTemplate) { this.restTemplate = restTemplate; }

    public boolean customerExists(String customerId) {
        try {
            restTemplate.getForObject(url + "/tmf-api/customerManagement/v4/customer/" + customerId, Object.class);
            return true;
        } catch (HttpClientErrorException e) {
            return e.getStatusCode() != HttpStatus.NOT_FOUND;
        } catch (Exception e) { return true; }
    }

    @SuppressWarnings("unchecked")
    public String createCustomer(String individualId, String fullName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of(
            "name",            fullName,
            "engagedPartyId",  individualId,
            "status",          "active"
        );

        Map<String, Object> response = restTemplate.postForObject(
            url + "/tmf-api/customerManagement/v4/customer",
            new HttpEntity<>(body, headers),
            Map.class
        );
        return (String) response.get("id");
    }

    @SuppressWarnings("unchecked")
    public String createCustomerAccount(String customerId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of(
            "name",     "Account",
            "customer", Map.of("id", customerId)
        );

        Map<String, Object> response = restTemplate.postForObject(
            url + "/tmf-api/customerManagement/v4/customerAccount",
            new HttpEntity<>(body, headers),
            Map.class
        );
        return (String) response.get("id");
    }
}
