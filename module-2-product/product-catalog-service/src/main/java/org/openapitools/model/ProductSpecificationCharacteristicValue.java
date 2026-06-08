package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.net.URI;
import org.openapitools.model.TimePeriod;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * A ProductSpecificationCharacteristicValue object is used to define a set of attributes, each of which can be assigned to a corresponding set of attributes in a ProductSpecificationCharacteristic object. The values of the attributes in the ProductSpecificationCharacteristicValue object describe the values of the attributes that a corresponding ProductSpecificationCharacteristic object can take on.
 */

@Schema(name = "ProductSpecificationCharacteristicValue", description = "A ProductSpecificationCharacteristicValue object is used to define a set of attributes, each of which can be assigned to a corresponding set of attributes in a ProductSpecificationCharacteristic object. The values of the attributes in the ProductSpecificationCharacteristicValue object describe the values of the attributes that a corresponding ProductSpecificationCharacteristic object can take on.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T16:22:46.010747900+05:30[Asia/Calcutta]", comments = "Generator version: 7.22.0")
public class ProductSpecificationCharacteristicValue {

  private @Nullable Boolean isDefault;

  private @Nullable String rangeInterval;

  private @Nullable String regex;

  private @Nullable String unitOfMeasure;

  private @Nullable String valueFrom;

  private @Nullable String valueTo;

  private @Nullable String valueType;

  private @Nullable TimePeriod validFor;

  private @Nullable Object value;

  private @Nullable String atBaseType;

  private @Nullable URI atSchemaLocation;

  private @Nullable String atType;

  public ProductSpecificationCharacteristicValue isDefault(@Nullable Boolean isDefault) {
    this.isDefault = isDefault;
    return this;
  }

  /**
   * Indicates if the value is the default value for a characteristic
   * @return isDefault
   */
  
  @Schema(name = "isDefault", description = "Indicates if the value is the default value for a characteristic", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isDefault")
  public @Nullable Boolean getIsDefault() {
    return isDefault;
  }

  @JsonProperty("isDefault")
  public void setIsDefault(@Nullable Boolean isDefault) {
    this.isDefault = isDefault;
  }

  public ProductSpecificationCharacteristicValue rangeInterval(@Nullable String rangeInterval) {
    this.rangeInterval = rangeInterval;
    return this;
  }

  /**
   * An indicator that specifies the inclusion or exclusion of the valueFrom and valueTo attributes. If applicable, possible values are \"open\", \"closed\", \"closedBottom\" and \"closedTop\".
   * @return rangeInterval
   */
  
  @Schema(name = "rangeInterval", description = "An indicator that specifies the inclusion or exclusion of the valueFrom and valueTo attributes. If applicable, possible values are \"open\", \"closed\", \"closedBottom\" and \"closedTop\".", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("rangeInterval")
  public @Nullable String getRangeInterval() {
    return rangeInterval;
  }

  @JsonProperty("rangeInterval")
  public void setRangeInterval(@Nullable String rangeInterval) {
    this.rangeInterval = rangeInterval;
  }

  public ProductSpecificationCharacteristicValue regex(@Nullable String regex) {
    this.regex = regex;
    return this;
  }

  /**
   * Regular expression to define constraint on the allowed value
   * @return regex
   */
  
  @Schema(name = "regex", description = "Regular expression to define constraint on the allowed value", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("regex")
  public @Nullable String getRegex() {
    return regex;
  }

  @JsonProperty("regex")
  public void setRegex(@Nullable String regex) {
    this.regex = regex;
  }

  public ProductSpecificationCharacteristicValue unitOfMeasure(@Nullable String unitOfMeasure) {
    this.unitOfMeasure = unitOfMeasure;
    return this;
  }

  /**
   * A length, surface, volume, dry measure, liquid measure, money, weight, time, and the like. In general, a determinate quantity or magnitude of the kind designated, taken as a standard of comparison for others of the same kind, in assigning to them numerical values, as 1 foot, 1 yard, 1 mile, 1 square foot.
   * @return unitOfMeasure
   */
  
  @Schema(name = "unitOfMeasure", description = "A length, surface, volume, dry measure, liquid measure, money, weight, time, and the like. In general, a determinate quantity or magnitude of the kind designated, taken as a standard of comparison for others of the same kind, in assigning to them numerical values, as 1 foot, 1 yard, 1 mile, 1 square foot.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("unitOfMeasure")
  public @Nullable String getUnitOfMeasure() {
    return unitOfMeasure;
  }

  @JsonProperty("unitOfMeasure")
  public void setUnitOfMeasure(@Nullable String unitOfMeasure) {
    this.unitOfMeasure = unitOfMeasure;
  }

  public ProductSpecificationCharacteristicValue valueFrom(@Nullable String valueFrom) {
    this.valueFrom = valueFrom;
    return this;
  }

  /**
   * The low range value that a characteristic can take on
   * @return valueFrom
   */
  
  @Schema(name = "valueFrom", description = "The low range value that a characteristic can take on", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("valueFrom")
  public @Nullable String getValueFrom() {
    return valueFrom;
  }

  @JsonProperty("valueFrom")
  public void setValueFrom(@Nullable String valueFrom) {
    this.valueFrom = valueFrom;
  }

  public ProductSpecificationCharacteristicValue valueTo(@Nullable String valueTo) {
    this.valueTo = valueTo;
    return this;
  }

  /**
   * The upper range value that a characteristic can take on
   * @return valueTo
   */
  
  @Schema(name = "valueTo", description = "The upper range value that a characteristic can take on", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("valueTo")
  public @Nullable String getValueTo() {
    return valueTo;
  }

  @JsonProperty("valueTo")
  public void setValueTo(@Nullable String valueTo) {
    this.valueTo = valueTo;
  }

  public ProductSpecificationCharacteristicValue valueType(@Nullable String valueType) {
    this.valueType = valueType;
    return this;
  }

  /**
   * A kind of value that the characteristic can take on, such as numeric, text, and so forth
   * @return valueType
   */
  
  @Schema(name = "valueType", description = "A kind of value that the characteristic can take on, such as numeric, text, and so forth", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("valueType")
  public @Nullable String getValueType() {
    return valueType;
  }

  @JsonProperty("valueType")
  public void setValueType(@Nullable String valueType) {
    this.valueType = valueType;
  }

  public ProductSpecificationCharacteristicValue validFor(@Nullable TimePeriod validFor) {
    this.validFor = validFor;
    return this;
  }

  /**
   * Get validFor
   * @return validFor
   */
  @Valid 
  @Schema(name = "validFor", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("validFor")
  public @Nullable TimePeriod getValidFor() {
    return validFor;
  }

  @JsonProperty("validFor")
  public void setValidFor(@Nullable TimePeriod validFor) {
    this.validFor = validFor;
  }

  public ProductSpecificationCharacteristicValue value(@Nullable Object value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
   */
  
  @Schema(name = "value", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("value")
  public @Nullable Object getValue() {
    return value;
  }

  @JsonProperty("value")
  public void setValue(@Nullable Object value) {
    this.value = value;
  }

  public ProductSpecificationCharacteristicValue atBaseType(@Nullable String atBaseType) {
    this.atBaseType = atBaseType;
    return this;
  }

  /**
   * When sub-classing, this defines the super-class
   * @return atBaseType
   */
  
  @Schema(name = "@baseType", description = "When sub-classing, this defines the super-class", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("@baseType")
  public @Nullable String getAtBaseType() {
    return atBaseType;
  }

  @JsonProperty("@baseType")
  public void setAtBaseType(@Nullable String atBaseType) {
    this.atBaseType = atBaseType;
  }

  public ProductSpecificationCharacteristicValue atSchemaLocation(@Nullable URI atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
    return this;
  }

  /**
   * A URI to a JSON-Schema file that defines additional attributes and relationships
   * @return atSchemaLocation
   */
  @Valid 
  @Schema(name = "@schemaLocation", description = "A URI to a JSON-Schema file that defines additional attributes and relationships", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("@schemaLocation")
  public @Nullable URI getAtSchemaLocation() {
    return atSchemaLocation;
  }

  @JsonProperty("@schemaLocation")
  public void setAtSchemaLocation(@Nullable URI atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
  }

  public ProductSpecificationCharacteristicValue atType(@Nullable String atType) {
    this.atType = atType;
    return this;
  }

  /**
   * When sub-classing, this defines the sub-class entity name
   * @return atType
   */
  
  @Schema(name = "@type", description = "When sub-classing, this defines the sub-class entity name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("@type")
  public @Nullable String getAtType() {
    return atType;
  }

  @JsonProperty("@type")
  public void setAtType(@Nullable String atType) {
    this.atType = atType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductSpecificationCharacteristicValue productSpecificationCharacteristicValue = (ProductSpecificationCharacteristicValue) o;
    return Objects.equals(this.isDefault, productSpecificationCharacteristicValue.isDefault) &&
        Objects.equals(this.rangeInterval, productSpecificationCharacteristicValue.rangeInterval) &&
        Objects.equals(this.regex, productSpecificationCharacteristicValue.regex) &&
        Objects.equals(this.unitOfMeasure, productSpecificationCharacteristicValue.unitOfMeasure) &&
        Objects.equals(this.valueFrom, productSpecificationCharacteristicValue.valueFrom) &&
        Objects.equals(this.valueTo, productSpecificationCharacteristicValue.valueTo) &&
        Objects.equals(this.valueType, productSpecificationCharacteristicValue.valueType) &&
        Objects.equals(this.validFor, productSpecificationCharacteristicValue.validFor) &&
        Objects.equals(this.value, productSpecificationCharacteristicValue.value) &&
        Objects.equals(this.atBaseType, productSpecificationCharacteristicValue.atBaseType) &&
        Objects.equals(this.atSchemaLocation, productSpecificationCharacteristicValue.atSchemaLocation) &&
        Objects.equals(this.atType, productSpecificationCharacteristicValue.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isDefault, rangeInterval, regex, unitOfMeasure, valueFrom, valueTo, valueType, validFor, value, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductSpecificationCharacteristicValue {\n");
    sb.append("    isDefault: ").append(toIndentedString(isDefault)).append("\n");
    sb.append("    rangeInterval: ").append(toIndentedString(rangeInterval)).append("\n");
    sb.append("    regex: ").append(toIndentedString(regex)).append("\n");
    sb.append("    unitOfMeasure: ").append(toIndentedString(unitOfMeasure)).append("\n");
    sb.append("    valueFrom: ").append(toIndentedString(valueFrom)).append("\n");
    sb.append("    valueTo: ").append(toIndentedString(valueTo)).append("\n");
    sb.append("    valueType: ").append(toIndentedString(valueType)).append("\n");
    sb.append("    validFor: ").append(toIndentedString(validFor)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    atBaseType: ").append(toIndentedString(atBaseType)).append("\n");
    sb.append("    atSchemaLocation: ").append(toIndentedString(atSchemaLocation)).append("\n");
    sb.append("    atType: ").append(toIndentedString(atType)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(@Nullable Object o) {
    return o == null ? "null" : o.toString().replace("\n", "\n    ");
  }
}

