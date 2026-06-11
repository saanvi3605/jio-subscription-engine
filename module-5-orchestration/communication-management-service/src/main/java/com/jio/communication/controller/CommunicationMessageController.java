package com.jio.communication.controller;

import com.jio.communication.model.CommunicationMessage;
import com.jio.communication.service.CommunicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tmf-api/communicationManagement/v4")
public class CommunicationMessageController {

    private final CommunicationService service;

    public CommunicationMessageController(CommunicationService service) {
        this.service = service;
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
}
