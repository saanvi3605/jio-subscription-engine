package com.jio.processflow.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * TMF701 ProcessFlow — a running instance of a named process definition.
 *
 * definitionId maps to one of the three built-in definitions:
 *   - CustomerOnboarding      (4 tasks)
 *   - ExistingCustomerOrder   (6 tasks)
 *   - FastCheckout            (9 tasks)
 *
 * status: inProgress → completed | failed
 */
@Entity
@Table(name = "process_flow")
public class ProcessFlow {

    @Id
    private String id;

    private String name;
    private String definitionId;
    private String status;              // inProgress, completed, failed

    private OffsetDateTime startDate;
    private OffsetDateTime completionDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "process_flow_id")
    @OrderBy("sequenceNumber ASC")
    private List<TaskFlow> taskFlow = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "flow_characteristic", joinColumns = @JoinColumn(name = "process_flow_id"))
    private List<FlowCharacteristic> characteristic = new ArrayList<>();

    // ── getters & setters ────────────────────────────────────────────────────

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDefinitionId() { return definitionId; }
    public void setDefinitionId(String definitionId) { this.definitionId = definitionId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public OffsetDateTime getStartDate() { return startDate; }
    public void setStartDate(OffsetDateTime startDate) { this.startDate = startDate; }

    public OffsetDateTime getCompletionDate() { return completionDate; }
    public void setCompletionDate(OffsetDateTime completionDate) { this.completionDate = completionDate; }

    public List<TaskFlow> getTaskFlow() { return taskFlow; }
    public void setTaskFlow(List<TaskFlow> taskFlow) { this.taskFlow = taskFlow; }

    public List<FlowCharacteristic> getCharacteristic() { return characteristic; }
    public void setCharacteristic(List<FlowCharacteristic> characteristic) { this.characteristic = characteristic; }
}
