package com.jio.customer.client;

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
    private final String groqApiKey;

    private static final String GROQ_URL = "https://api.groq.com/openai/v1/chat/completions";

    public LiteLLMClient(RestTemplate restTemplate,
                         @Value("${groq.api.key:}") String groqApiKey) {
        this.restTemplate = restTemplate;
        this.groqApiKey = groqApiKey;
    }

    public String chat(String systemPrompt, String userMessage) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + groqApiKey);
            headers.set("Content-Type", "application/json");

            var body = Map.of(
                "model", "llama-3.3-70b-versatile",
                "messages", List.of(
                    Map.of("role", "system", "content", systemPrompt),
                    Map.of("role", "user", "content", userMessage)
                )
            );

            ChatResponse response = restTemplate.postForObject(
                GROQ_URL, new HttpEntity<>(body, headers), ChatResponse.class
            );
            if (response == null || response.choices() == null || response.choices().isEmpty()) {
                return "Unable to generate a response at this time.";
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
