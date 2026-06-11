package com.jio.processflow.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class CatalogClient {

    private final RestTemplate restTemplate;

    @Value("${services.tmf620.url}")
    private String url;

    public CatalogClient(RestTemplate restTemplate) { this.restTemplate = restTemplate; }

    public boolean offeringExists(String offeringId) {
        try {
            restTemplate.getForObject(url + "/tmf-api/productCatalogManagement/v4/productOffering/" + offeringId, Object.class);
            return true;
        } catch (HttpClientErrorException e) {
            return e.getStatusCode() != HttpStatus.NOT_FOUND;
        } catch (Exception e) { return true; }
    }
}
