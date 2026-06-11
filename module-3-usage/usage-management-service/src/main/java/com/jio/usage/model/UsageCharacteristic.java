package com.jio.usage.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

/**
 * A key/value pair describing the detail of a usage record.
 * e.g. name="dataConsumed", valueType="decimal", value="1.2", valueUnit="GB"
 *      name="callDuration", valueType="integer", value="342",  valueUnit="seconds"
 *      name="destination",  valueType="string",  value="9876543210"
 */
@Embeddable
public class UsageCharacteristic {

    @NotBlank
    private String name;

    private String valueType;

    private String value;

    /** Unit of measurement — GB, MB, seconds, minutes, count, etc. */
    private String valueUnit;

    public UsageCharacteristic() {}

    public String getName()                  { return name; }
    public void setName(String name)         { this.name = name; }

    public String getValueType()             { return valueType; }
    public void setValueType(String vt)      { this.valueType = vt; }

    public String getValue()                 { return value; }
    public void setValue(String value)       { this.value = value; }

    public String getValueUnit()             { return valueUnit; }
    public void setValueUnit(String vu)      { this.valueUnit = vu; }
}
