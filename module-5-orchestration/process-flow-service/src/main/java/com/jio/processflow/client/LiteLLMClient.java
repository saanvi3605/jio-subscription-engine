package com.jio.processflow.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class LiteLLMClient {

    private final RestTemplate restTemplate;
    private final String liteLLMUrl;

    private static final String MASTER_KEY = "sk-jio-1234";

    public LiteLLMClient(RestTemplate restTemplate,
                         @Value("${services.litellm.url:http://localhost:4000}") String liteLLMUrl) {
        this.restTemplate = restTemplate;
        this.liteLLMUrl = liteLLMUrl;
    }

    public String chat(String systemPrompt, String userMessage, String model) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + MASTER_KEY);
            headers.set("Content-Type", "application/json");

            var body = Map.of(
                "model", model,
                "messages", List.of(
                    Map.of("role", "system", "content", systemPrompt),
                    Map.of("role", "user", "content", userMessage)
                )
            );

            ChatResponse response = restTemplate.postForObject(
                liteLLMUrl + "/v1/chat/completions", new HttpEntity<>(body, headers), ChatResponse.class
            );
            if (response == null || response.choices() == null || response.choices().isEmpty()) {
                return "Unable to generate recommendation at this time.";
            }
            return response.choices().get(0).message().content();
        } catch (Exception e) {
            return "AI unavailable: " + e.getMessage();
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ChatResponse(List<Choice> choices) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Choice(Message message) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Message(String content) {}
}
