package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.model.ProductSpecificationCharacteristicRelationship;
import org.openapitools.model.ProductSpecificationCharacteristicValue;
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
 * A characteristic quality or distinctive feature of a ProductSpecification.  The characteristic can be take on a discrete value, such as color, can take on a range of values, (for example, sensitivity of 100-240 mV), or can be derived from a formula (for example, usage time (hrs) &#x3D; 30 - talk time *3). Certain characteristics, such as color, may be configured during the ordering or some other process.
 */

@Schema(name = "ProductSpecificationCharacteristic", description = "A characteristic quality or distinctive feature of a ProductSpecification.  The characteristic can be take on a discrete value, such as color, can take on a range of values, (for example, sensitivity of 100-240 mV), or can be derived from a formula (for example, usage time (hrs) = 30 - talk time *3). Certain characteristics, such as color, may be configured during the ordering or some other process.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T16:22:46.010747900+05:30[Asia/Calcutta]", comments = "Generator version: 7.22.0")
public class ProductSpecificationCharacteristic {

  private @Nullable Boolean configurable;

  private @Nullable String description;

  private @Nullable Boolean extensible;

  private @Nullable Boolean isUnique;

  private @Nullable Integer maxCardinality;

  private @Nullable Integer minCardinality;

  private @Nullable String name;

  private @Nullable String regex;

  private @Nullable String valueType;

  @Valid
  private List<@Valid ProductSpecificationCharacteristicRelationship> productSpecCharRelationship = new ArrayList<>();

  @Valid
  private List<@Valid ProductSpecificationCharacteristicValue> productSpecCharacteristicValue = new ArrayList<>();

  private @Nullable TimePeriod validFor;

  private @Nullable String atBaseType;

  private @Nullable URI atSchemaLocation;

  private @Nullable String atType;

  public ProductSpecificationCharacteristic configurable(@Nullable Boolean configurable) {
    this.configurable = configurable;
    return this;
  }

  /**
   * If true, the Boolean indicates that the ProductSpecificationCharacteristic is configurable
   * @return configurable
   */
  
  @Schema(name = "configurable", description = "If true, the Boolean indicates that the ProductSpecificationCharacteristic is configurable", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("configurable")
  public @Nullable Boolean getConfigurable() {
    return configurable;
  }

  @JsonProperty("configurable")
  public void setConfigurable(@Nullable Boolean configurable) {
    this.configurable = configurable;
  }

  public ProductSpecificationCharacteristic description(@Nullable String description) {
    this.description = description;
    return this;
  }

  /**
   * A narrative that explains in detail what the ProductSpecificationCharacteristic is
   * @return description
   */
  
  @Schema(name = "description", description = "A narrative that explains in detail what the ProductSpecificationCharacteristic is", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public @Nullable String getDescription() {
    return description;
  }

  @JsonProperty("description")
  public void setDescription(@Nullable String description) {
    this.description = description;
  }

  public ProductSpecificationCharacteristic extensible(@Nullable Boolean extensible) {
    this.extensible = extensible;
    return this;
  }

  /**
   * An indicator that specifies that the values for the characteristic can be extended by adding new values when instantiating a characteristic for a product
   * @return extensible
   */
  
  @Schema(name = "extensible", description = "An indicator that specifies that the values for the characteristic can be extended by adding new values when instantiating a characteristic for a product", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("extensible")
  public @Nullable Boolean getExtensible() {
    return extensible;
  }

  @JsonProperty("extensible")
  public void setExtensible(@Nullable Boolean extensible) {
    this.extensible = extensible;
  }

  public ProductSpecificationCharacteristic isUnique(@Nullable Boolean isUnique) {
    this.isUnique = isUnique;
    return this;
  }

  /**
   * An indicator that specifies if a value is unique for the specification. Possible values are; \"unique while value is in effect\" and \"unique whether value is in effect or not\"
   * @return isUnique
   */
  
  @Schema(name = "isUnique", description = "An indicator that specifies if a value is unique for the specification. Possible values are; \"unique while value is in effect\" and \"unique whether value is in effect or not\"", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isUnique")
  public @Nullable Boolean getIsUnique() {
    return isUnique;
  }

  @JsonProperty("isUnique")
  public void setIsUnique(@Nullable Boolean isUnique) {
    this.isUnique = isUnique;
  }

  public ProductSpecificationCharacteristic maxCardinality(@Nullable Integer maxCardinality) {
    this.maxCardinality = maxCardinality;
    return this;
  }

  /**
   * The maximum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where five is the value for the maxCardinality
   * @return maxCardinality
   */
  
  @Schema(name = "maxCardinality", description = "The maximum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where five is the value for the maxCardinality", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("maxCardinality")
  public @Nullable Integer getMaxCardinality() {
    return maxCardinality;
  }

  @JsonProperty("maxCardinality")
  public void setMaxCardinality(@Nullable Integer maxCardinality) {
    this.maxCardinality = maxCardinality;
  }

  public ProductSpecificationCharacteristic minCardinality(@Nullable Integer minCardinality) {
    this.minCardinality = minCardinality;
    return this;
  }

  /**
   * The minimum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where zero is the value for the minCardinality
   * @return minCardinality
   */
  
  @Schema(name = "minCardinality", description = "The minimum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where zero is the value for the minCardinality", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("minCardinality")
  public @Nullable Integer getMinCardinality() {
    return minCardinality;
  }

  @JsonProperty("minCardinality")
  public void setMinCardinality(@Nullable Integer minCardinality) {
    this.minCardinality = minCardinality;
  }

  public ProductSpecificationCharacteristic name(@Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the ProductSpecificationCharacteristic
   * @return name
   */
  
  @Schema(name = "name", description = "Name of the ProductSpecificationCharacteristic", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public @Nullable String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(@Nullable String name) {
    this.name = name;
  }

  public ProductSpecificationCharacteristic regex(@Nullable String regex) {
    this.regex = regex;
    return this;
  }

  /**
   * A rule or principle represented in regular expression used to derive the value of a characteristic value
   * @return regex
   */
  
  @Schema(name = "regex", description = "A rule or principle represented in regular expression used to derive the value of a characteristic value", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("regex")
  public @Nullable String getRegex() {
    return regex;
  }

  @JsonProperty("regex")
  public void setRegex(@Nullable String regex) {
    this.regex = regex;
  }

  public ProductSpecificationCharacteristic valueType(@Nullable String valueType) {
    this.valueType = valueType;
    return this;
  }

  /**
   * A kind of value that the characteristic can take on, such as numeric, text and so forth
   * @return valueType
   */
  
  @Schema(name = "valueType", description = "A kind of value that the characteristic can take on, such as numeric, text and so forth", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("valueType")
  public @Nullable String getValueType() {
    return valueType;
  }

  @JsonProperty("valueType")
  public void setValueType(@Nullable String valueType) {
    this.valueType = valueType;
  }

  public ProductSpecificationCharacteristic productSpecCharRelationship(List<@Valid ProductSpecificationCharacteristicRelationship> productSpecCharRelationship) {
    this.productSpecCharRelationship = productSpecCharRelationship;
    return this;
  }

  public ProductSpecificationCharacteristic addProductSpecCharRelationshipItem(ProductSpecificationCharacteristicRelationship productSpecCharRelationshipItem) {
    if (this.productSpecCharRelationship == null) {
      this.productSpecCharRelationship = new ArrayList<>();
    }
    this.productSpecCharRelationship.add(productSpecCharRelationshipItem);
    return this;
  }

  /**
   * An aggregation, migration, substitution, dependency or exclusivity relationship between/among Specification Characteristics.
   * @return productSpecCharRelationship
   */
  @Valid 
  @Schema(name = "productSpecCharRelationship", description = "An aggregation, migration, substitution, dependency or exclusivity relationship between/among Specification Characteristics.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("productSpecCharRelationship")
  public List<@Valid ProductSpecificationCharacteristicRelationship> getProductSpecCharRelationship() {
    return productSpecCharRelationship;
  }

  @JsonProperty("productSpecCharRelationship")
  public void setProductSpecCharRelationship(List<@Valid ProductSpecificationCharacteristicRelationship> productSpecCharRelationship) {
    this.productSpecCharRelationship = productSpecCharRelationship;
  }

  public ProductSpecificationCharacteristic productSpecCharacteristicValue(List<@Valid ProductSpecificationCharacteristicValue> productSpecCharacteristicValue) {
    this.productSpecCharacteristicValue = productSpecCharacteristicValue;
    return this;
  }

  public ProductSpecificationCharacteristic addProductSpecCharacteristicValueItem(ProductSpecificationCharacteristicValue productSpecCharacteristicValueItem) {
    if (this.productSpecCharacteristicValue == null) {
      this.productSpecCharacteristicValue = new ArrayList<>();
    }
    this.productSpecCharacteristicValue.add(productSpecCharacteristicValueItem);
    return this;
  }

  /**
   * A ProductSpecificationCharacteristicValue object is used to define a set of attributes, each of which can be assigned to a corresponding set of attributes in a ProductSpecificationCharacteristic object. The values of the attributes in the ProductSpecificationCharacteristicValue object describe the values of the attributes that a corresponding ProductSpecificationCharacteristic object can take on.
   * @return productSpecCharacteristicValue
   */
  @Valid 
  @Schema(name = "productSpecCharacteristicValue", description = "A ProductSpecificationCharacteristicValue object is used to define a set of attributes, each of which can be assigned to a corresponding set of attributes in a ProductSpecificationCharacteristic object. The values of the attributes in the ProductSpecificationCharacteristicValue object describe the values of the attributes that a corresponding ProductSpecificationCharacteristic object can take on.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("productSpecCharacteristicValue")
  public List<@Valid ProductSpecificationCharacteristicValue> getProductSpecCharacteristicValue() {
    return productSpecCharacteristicValue;
  }

  @JsonProperty("productSpecCharacteristicValue")
  public void setProductSpecCharacteristicValue(List<@Valid ProductSpecificationCharacteristicValue> productSpecCharacteristicValue) {
    this.productSpecCharacteristicValue = productSpecCharacteristicValue;
  }

  public ProductSpecificationCharacteristic validFor(@Nullable TimePeriod validFor) {
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

  public ProductSpecificationCharacteristic atBaseType(@Nullable String atBaseType) {
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

  public ProductSpecificationCharacteristic atSchemaLocation(@Nullable URI atSchemaLocation) {
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

  public ProductSpecificationCharacteristic atType(@Nullable String atType) {
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
    ProductSpecificationCharacteristic productSpecificationCharacteristic = (ProductSpecificationCharacteristic) o;
    return Objects.equals(this.configurable, productSpecificationCharacteristic.configurable) &&
        Objects.equals(this.description, productSpecificationCharacteristic.description) &&
        Objects.equals(this.extensible, productSpecificationCharacteristic.extensible) &&
        Objects.equals(this.isUnique, productSpecificationCharacteristic.isUnique) &&
        Objects.equals(this.maxCardinality, productSpecificationCharacteristic.maxCardinality) &&
        Objects.equals(this.minCardinality, productSpecificationCharacteristic.minCardinality) &&
        Objects.equals(this.name, productSpecificationCharacteristic.name) &&
        Objects.equals(this.regex, productSpecificationCharacteristic.regex) &&
        Objects.equals(this.valueType, productSpecificationCharacteristic.valueType) &&
        Objects.equals(this.productSpecCharRelationship, productSpecificationCharacteristic.productSpecCharRelationship) &&
        Objects.equals(this.productSpecCharacteristicValue, productSpecificationCharacteristic.productSpecCharacteristicValue) &&
        Objects.equals(this.validFor, productSpecificationCharacteristic.validFor) &&
        Objects.equals(this.atBaseType, productSpecificationCharacteristic.atBaseType) &&
        Objects.equals(this.atSchemaLocation, productSpecificationCharacteristic.atSchemaLocation) &&
        Objects.equals(this.atType, productSpecificationCharacteristic.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(configurable, description, extensible, isUnique, maxCardinality, minCardinality, name, regex, valueType, productSpecCharRelationship, productSpecCharacteristicValue, validFor, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductSpecificationCharacteristic {\n");
    sb.append("    configurable: ").append(toIndentedString(configurable)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    extensible: ").append(toIndentedString(extensible)).append("\n");
    sb.append("    isUnique: ").append(toIndentedString(isUnique)).append("\n");
    sb.append("    maxCardinality: ").append(toIndentedString(maxCardinality)).append("\n");
    sb.append("    minCardinality: ").append(toIndentedString(minCardinality)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    regex: ").append(toIndentedString(regex)).append("\n");
    sb.append("    valueType: ").append(toIndentedString(valueType)).append("\n");
    sb.append("    productSpecCharRelationship: ").append(toIndentedString(productSpecCharRelationship)).append("\n");
    sb.append("    productSpecCharacteristicValue: ").append(toIndentedString(productSpecCharacteristicValue)).append("\n");
    sb.append("    validFor: ").append(toIndentedString(validFor)).append("\n");
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

