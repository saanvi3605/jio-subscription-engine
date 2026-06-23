package com.jio.communication.controller;

import com.jio.communication.client.LiteLLMClient;
import com.jio.communication.model.CommunicationMessage;
import com.jio.communication.service.CommunicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tmf-api/communicationManagement/v4")
public class CommunicationMessageController {

    private final CommunicationService service;
    private final LiteLLMClient liteLLMClient;

    public CommunicationMessageController(CommunicationService service, LiteLLMClient liteLLMClient) {
        this.service = service;
        this.liteLLMClient = liteLLMClient;
    }

    /** Send a new message (simulates delivery immediately). */
    @PostMapping("/communicationMessage")
    public ResponseEntity<CommunicationMessage> send(@RequestBody CommunicationMessage message) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.send(message));
    }

    /** List all messages. Optionally filter by customerId or relatedEntity. */
    @GetMapping("/communicationMessage")
    public ResponseEntity<List<CommunicationMessage>> list(
            @RequestParam(required = false) String customerId,
            @RequestParam(required = false) String relatedEntityType,
            @RequestParam(required = false) String relatedEntityId) {

        if (customerId != null) {
            return ResponseEntity.ok(service.findByCustomerId(customerId));
        }
        if (relatedEntityType != null && relatedEntityId != null) {
            return ResponseEntity.ok(service.findByRelatedEntity(relatedEntityType, relatedEntityId));
        }
        return ResponseEntity.ok(service.findAll());
    }

    /** Get a single message by id. */
    @GetMapping("/communicationMessage/{id}")
    public ResponseEntity<CommunicationMessage> get(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** Update status or content of a message. */
    @PatchMapping("/communicationMessage/{id}")
    public ResponseEntity<CommunicationMessage> patch(
            @PathVariable String id,
            @RequestBody CommunicationMessage patch) {
        return service.patch(id, patch)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** Delete a message. */
    @DeleteMapping("/communicationMessage/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    /**
     * AI-generate a notification message and send it.
     * Body: { "eventType": "payment_success", "customerId": "123",
     *         "customerName": "Priya", "details": "Rs 599 for Jio Fiber" }
     */
    @PostMapping("/communicationMessage/ai-generate")
    public ResponseEntity<CommunicationMessage> aiGenerate(@RequestBody Map<String, String> body) {
        String eventType    = body.getOrDefault("eventType", "notification");
        String customerId   = body.getOrDefault("customerId", "unknown");
        String customerName = body.getOrDefault("customerName", "Customer");
        String details      = body.getOrDefault("details", "");

        String systemPrompt = """
                You are a notification writer for Jio, India's leading telecom provider.
                Write a short, friendly SMS/push notification (max 2 sentences) for the given event.
                Use the customer's name, be warm and professional. No subject line needed.
                """;
        String userMessage = "Event: " + eventType + "\nCustomer: " + customerName
                + " (ID: " + customerId + ")\nDetails: " + details;

        String content = liteLLMClient.chat(systemPrompt, userMessage, "sms-writer");

        CommunicationMessage msg = new CommunicationMessage();
        msg.setCustomerId(customerId);
        msg.setType("SMS");
        msg.setContent(content);
        msg.setReceiver(customerName);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.send(msg));
    }
}
