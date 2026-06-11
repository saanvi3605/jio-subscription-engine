package com.jio.processflow.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class PartyClient {

    private final RestTemplate restTemplate;

    @Value("${services.tmf632.url}")
    private String url;

    public PartyClient(RestTemplate restTemplate) { this.restTemplate = restTemplate; }

    @SuppressWarnings("unchecked")
    public String createIndividual(String firstName, String lastName, String email, String phone) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of(
            "firstName", firstName,
            "lastName",  lastName,
            "fullName",  firstName + " " + lastName,
            "contactMedium", List.of(
                Map.of("mediumType", "email",
                       "characteristic", Map.of("emailAddress", email != null ? email : "")),
                Map.of("mediumType", "mobile",
                       "characteristic", Map.of("phoneNumber", phone != null ? phone : ""))
            )
        );

        Map<String, Object> response = restTemplate.postForObject(
            url + "/tmf-api/partyManagement/v4/individual",
            new HttpEntity<>(body, headers),
            Map.class
        );
        return (String) response.get("id");
    }
}
