package com.jio.processflow.model;

import jakarta.persistence.Embeddable;

/**
 * Key-value input parameter passed when starting a ProcessFlow.
 * e.g. { name: "productOfferingId", value: "offer-abc-123" }
 */
@Embeddable
public class FlowCharacteristic {

    private String name;
    private String value;

    public FlowCharacteristic() {}

    public FlowCharacteristic(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}
