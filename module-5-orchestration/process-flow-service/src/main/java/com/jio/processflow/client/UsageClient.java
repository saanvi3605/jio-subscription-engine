package com.jio.processflow.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class UsageClient {

    private final RestTemplate restTemplate;

    @Value("${services.tmf635.url}")
    private String url;

    public UsageClient(RestTemplate restTemplate) { this.restTemplate = restTemplate; }

    /**
     * Verifies a ProductUsage tracker exists for the given productId.
     * Returns the productUsageId if found, null otherwise.
     */
    @SuppressWarnings("unchecked")
    public String findUsageByProductId(String productId) {
        try {
            List<Map<String, Object>> usages = restTemplate.exchange(
                url + "/tmf-api/usageManagement/v4/productUsage?productId=" + productId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {}
            ).getBody();

            if (usages != null && !usages.isEmpty()) {
                return (String) usages.get(0).get("id");
            }
            return null;
        } catch (Exception e) { return null; }
    }
}
