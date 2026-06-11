package com.jio.communication.repository;

import com.jio.communication.model.CommunicationMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunicationMessageRepository extends JpaRepository<CommunicationMessage, String> {
    List<CommunicationMessage> findByCustomerId(String customerId);
    List<CommunicationMessage> findByRelatedEntityTypeAndRelatedEntityId(String type, String id);
    List<CommunicationMessage> findByStatus(String status);
    List<CommunicationMessage> findByType(String type);
}
