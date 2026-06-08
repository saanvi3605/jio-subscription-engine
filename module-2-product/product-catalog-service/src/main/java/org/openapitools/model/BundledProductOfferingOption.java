package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.net.URI;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * A set of numbers that specifies the lower and upper limits for a ProductOffering that can be procured as part of the related BundledProductOffering. Values can range from 0 to unbounded
 */

@Schema(name = "BundledProductOfferingOption", description = "A set of numbers that specifies the lower and upper limits for a ProductOffering that can be procured as part of the related BundledProductOffering. Values can range from 0 to unbounded")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T16:22:46.010747900+05:30[Asia/Calcutta]", comments = "Generator version: 7.22.0")
public class BundledProductOfferingOption {

  private @Nullable Integer numberRelOfferDefault;

  private @Nullable Integer numberRelOfferLowerLimit;

  private @Nullable Integer numberRelOfferUpperLimit;

  private @Nullable String atBaseType;

  private @Nullable URI atSchemaLocation;

  private @Nullable String atType;

  public BundledProductOfferingOption numberRelOfferDefault(@Nullable Integer numberRelOfferDefault) {
    this.numberRelOfferDefault = numberRelOfferDefault;
    return this;
  }

  /**
   * Default number of produc offereings that should be procured as part of the related BundledProductOffering
   * @return numberRelOfferDefault
   */
  
  @Schema(name = "numberRelOfferDefault", description = "Default number of produc offereings that should be procured as part of the related BundledProductOffering", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("numberRelOfferDefault")
  public @Nullable Integer getNumberRelOfferDefault() {
    return numberRelOfferDefault;
  }

  @JsonProperty("numberRelOfferDefault")
  public void setNumberRelOfferDefault(@Nullable Integer numberRelOfferDefault) {
    this.numberRelOfferDefault = numberRelOfferDefault;
  }

  public BundledProductOfferingOption numberRelOfferLowerLimit(@Nullable Integer numberRelOfferLowerLimit) {
    this.numberRelOfferLowerLimit = numberRelOfferLowerLimit;
    return this;
  }

  /**
   * lower limit for a product offering that can be procured as part of the related BundledProductOffering
   * @return numberRelOfferLowerLimit
   */
  
  @Schema(name = "numberRelOfferLowerLimit", description = "lower limit for a product offering that can be procured as part of the related BundledProductOffering", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("numberRelOfferLowerLimit")
  public @Nullable Integer getNumberRelOfferLowerLimit() {
    return numberRelOfferLowerLimit;
  }

  @JsonProperty("numberRelOfferLowerLimit")
  public void setNumberRelOfferLowerLimit(@Nullable Integer numberRelOfferLowerLimit) {
    this.numberRelOfferLowerLimit = numberRelOfferLowerLimit;
  }

  public BundledProductOfferingOption numberRelOfferUpperLimit(@Nullable Integer numberRelOfferUpperLimit) {
    this.numberRelOfferUpperLimit = numberRelOfferUpperLimit;
    return this;
  }

  /**
   * upper limit for a product offering that can be procured as part of the related BundledProductOffering
   * @return numberRelOfferUpperLimit
   */
  
  @Schema(name = "numberRelOfferUpperLimit", description = "upper limit for a product offering that can be procured as part of the related BundledProductOffering", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("numberRelOfferUpperLimit")
  public @Nullable Integer getNumberRelOfferUpperLimit() {
    return numberRelOfferUpperLimit;
  }

  @JsonProperty("numberRelOfferUpperLimit")
  public void setNumberRelOfferUpperLimit(@Nullable Integer numberRelOfferUpperLimit) {
    this.numberRelOfferUpperLimit = numberRelOfferUpperLimit;
  }

  public BundledProductOfferingOption atBaseType(@Nullable String atBaseType) {
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

  public BundledProductOfferingOption atSchemaLocation(@Nullable URI atSchemaLocation) {
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

  public BundledProductOfferingOption atType(@Nullable String atType) {
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
    BundledProductOfferingOption bundledProductOfferingOption = (BundledProductOfferingOption) o;
    return Objects.equals(this.numberRelOfferDefault, bundledProductOfferingOption.numberRelOfferDefault) &&
        Objects.equals(this.numberRelOfferLowerLimit, bundledProductOfferingOption.numberRelOfferLowerLimit) &&
        Objects.equals(this.numberRelOfferUpperLimit, bundledProductOfferingOption.numberRelOfferUpperLimit) &&
        Objects.equals(this.atBaseType, bundledProductOfferingOption.atBaseType) &&
        Objects.equals(this.atSchemaLocation, bundledProductOfferingOption.atSchemaLocation) &&
        Objects.equals(this.atType, bundledProductOfferingOption.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numberRelOfferDefault, numberRelOfferLowerLimit, numberRelOfferUpperLimit, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BundledProductOfferingOption {\n");
    sb.append("    numberRelOfferDefault: ").append(toIndentedString(numberRelOfferDefault)).append("\n");
    sb.append("    numberRelOfferLowerLimit: ").append(toIndentedString(numberRelOfferLowerLimit)).append("\n");
    sb.append("    numberRelOfferUpperLimit: ").append(toIndentedString(numberRelOfferUpperLimit)).append("\n");
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

