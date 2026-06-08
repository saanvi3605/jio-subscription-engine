package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.net.URI;
import org.openapitools.model.Money;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * A tax item is created for each tax rate and tax type used in the bill.
 */

@Schema(name = "TaxItem", description = "A tax item is created for each tax rate and tax type used in the bill.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T16:22:46.010747900+05:30[Asia/Calcutta]", comments = "Generator version: 7.22.0")
public class TaxItem {

  private @Nullable String taxCategory;

  private @Nullable Float taxRate;

  private @Nullable Money taxAmount;

  private @Nullable String atBaseType;

  private @Nullable URI atSchemaLocation;

  private @Nullable String atType;

  public TaxItem taxCategory(@Nullable String taxCategory) {
    this.taxCategory = taxCategory;
    return this;
  }

  /**
   * Tax category
   * @return taxCategory
   */
  
  @Schema(name = "taxCategory", description = "Tax category", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("taxCategory")
  public @Nullable String getTaxCategory() {
    return taxCategory;
  }

  @JsonProperty("taxCategory")
  public void setTaxCategory(@Nullable String taxCategory) {
    this.taxCategory = taxCategory;
  }

  public TaxItem taxRate(@Nullable Float taxRate) {
    this.taxRate = taxRate;
    return this;
  }

  /**
   * Applied rate of the tax
   * @return taxRate
   */
  
  @Schema(name = "taxRate", description = "Applied rate of the tax", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("taxRate")
  public @Nullable Float getTaxRate() {
    return taxRate;
  }

  @JsonProperty("taxRate")
  public void setTaxRate(@Nullable Float taxRate) {
    this.taxRate = taxRate;
  }

  public TaxItem taxAmount(@Nullable Money taxAmount) {
    this.taxAmount = taxAmount;
    return this;
  }

  /**
   * Get taxAmount
   * @return taxAmount
   */
  @Valid 
  @Schema(name = "taxAmount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("taxAmount")
  public @Nullable Money getTaxAmount() {
    return taxAmount;
  }

  @JsonProperty("taxAmount")
  public void setTaxAmount(@Nullable Money taxAmount) {
    this.taxAmount = taxAmount;
  }

  public TaxItem atBaseType(@Nullable String atBaseType) {
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

  public TaxItem atSchemaLocation(@Nullable URI atSchemaLocation) {
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

  public TaxItem atType(@Nullable String atType) {
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
    TaxItem taxItem = (TaxItem) o;
    return Objects.equals(this.taxCategory, taxItem.taxCategory) &&
        Objects.equals(this.taxRate, taxItem.taxRate) &&
        Objects.equals(this.taxAmount, taxItem.taxAmount) &&
        Objects.equals(this.atBaseType, taxItem.atBaseType) &&
        Objects.equals(this.atSchemaLocation, taxItem.atSchemaLocation) &&
        Objects.equals(this.atType, taxItem.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taxCategory, taxRate, taxAmount, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TaxItem {\n");
    sb.append("    taxCategory: ").append(toIndentedString(taxCategory)).append("\n");
    sb.append("    taxRate: ").append(toIndentedString(taxRate)).append("\n");
    sb.append("    taxAmount: ").append(toIndentedString(taxAmount)).append("\n");
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

