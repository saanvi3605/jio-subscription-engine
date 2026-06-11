package com.jio.communication.service;

import com.jio.communication.model.CommunicationMessage;
import com.jio.communication.repository.CommunicationMessageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommunicationService {

    private final CommunicationMessageRepository repository;

    public CommunicationService(CommunicationMessageRepository repository) {
        this.repository = repository;
    }

    /**
     * Accepts a message, marks it as "sent" immediately (simulated delivery).
     * In a real system this would call an SMS/email provider and set status
     * to "delivered" or "failed" based on the provider response.
     */
    public CommunicationMessage send(CommunicationMessage message) {
        if (message.getType() == null || message.getType().isBlank()) {
            message.setType("SMS");
        }
        // Simulate immediate delivery
        message.setStatus("sent");
        message.setSentAt(OffsetDateTime.now());
        return repository.save(message);
    }

    @Transactional(readOnly = true)
    public List<CommunicationMessage> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<CommunicationMessage> findById(String id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<CommunicationMessage> findByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Transactional(readOnly = true)
    public List<CommunicationMessage> findByRelatedEntity(String type, String id) {
        return repository.findByRelatedEntityTypeAndRelatedEntityId(type, id);
    }

    public Optional<CommunicationMessage> patch(String id, CommunicationMessage patch) {
        return repository.findById(id).map(existing -> {
            if (patch.getStatus()  != null) existing.setStatus(patch.getStatus());
            if (patch.getReceiver() != null) existing.setReceiver(patch.getReceiver());
            if (patch.getContent()  != null) existing.setContent(patch.getContent());
            return repository.save(existing);
        });
    }

    public boolean delete(String id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
