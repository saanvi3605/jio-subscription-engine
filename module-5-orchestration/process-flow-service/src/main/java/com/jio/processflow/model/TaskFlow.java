package com.jio.processflow.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

/**
 * A single step within a running ProcessFlow.
 * status lifecycle: pending → inProgress → completed | failed
 */
@Entity
@Table(name = "task_flow")
public class TaskFlow {

    @Id
    private String id;

    private String name;
    private String status;          // pending, inProgress, completed, failed
    private int sequenceNumber;

    private String relatedEntityId;     // ID of the resource created/verified by this task
    private String relatedEntityType;   // e.g. Individual, Customer, ProductOrder

    @Column(length = 1000)
    private String note;                // success message or error detail

    private OffsetDateTime startDate;
    private OffsetDateTime completionDate;

    // ── getters & setters ────────────────────────────────────────────────────

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getSequenceNumber() { return sequenceNumber; }
    public void setSequenceNumber(int sequenceNumber) { this.sequenceNumber = sequenceNumber; }

    public String getRelatedEntityId() { return relatedEntityId; }
    public void setRelatedEntityId(String relatedEntityId) { this.relatedEntityId = relatedEntityId; }

    public String getRelatedEntityType() { return relatedEntityType; }
    public void setRelatedEntityType(String relatedEntityType) { this.relatedEntityType = relatedEntityType; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public OffsetDateTime getStartDate() { return startDate; }
    public void setStartDate(OffsetDateTime startDate) { this.startDate = startDate; }

    public OffsetDateTime getCompletionDate() { return completionDate; }
    public void setCompletionDate(OffsetDateTime completionDate) { this.completionDate = completionDate; }
}
