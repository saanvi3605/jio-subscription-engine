package com.jio.inventory.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

/**
 * A key/value pair describing a specific characteristic of a Product.
 * e.g. name="dataLimit", valueType="string", value="84GB"
 *      name="validity",  valueType="string", value="84 days"
 *      name="simType",   valueType="string", value="eSIM"
 */
@Embeddable
public class ProductCharacteristic {

    @NotBlank
    private String name;

    private String valueType;

    private String value;

    public ProductCharacteristic() {}

    public ProductCharacteristic(String name, String valueType, String value) {
        this.name      = name;
        this.valueType = valueType;
        this.value     = value;
    }

    public String getName()                  { return name; }
    public void setName(String name)         { this.name = name; }

    public String getValueType()             { return valueType; }
    public void setValueType(String vt)      { this.valueType = vt; }

    public String getValue()                 { return value; }
    public void setValue(String value)       { this.value = value; }
}
