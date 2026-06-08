package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.model.CategoryRef;
import org.openapitools.model.RelatedParty;
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
 * A collection of Product Offerings, intended for a specific DistributionChannel, enhanced with additional information such as SLA parameters, invoicing and shipping details Skipped properties: id,href,lastUpdate
 */

@Schema(name = "Catalog_Update", description = "A collection of Product Offerings, intended for a specific DistributionChannel, enhanced with additional information such as SLA parameters, invoicing and shipping details Skipped properties: id,href,lastUpdate")
@JsonTypeName("Catalog_Update")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T16:22:46.010747900+05:30[Asia/Calcutta]", comments = "Generator version: 7.22.0")
public class CatalogUpdate {

  private @Nullable String catalogType;

  private @Nullable String description;

  private @Nullable String lifecycleStatus;

  private @Nullable String name;

  private @Nullable String version;

  @Valid
  private List<@Valid CategoryRef> category = new ArrayList<>();

  @Valid
  private List<@Valid RelatedParty> relatedParty = new ArrayList<>();

  private @Nullable TimePeriod validFor;

  private @Nullable String atBaseType;

  private @Nullable URI atSchemaLocation;

  private @Nullable String atType;

  public CatalogUpdate catalogType(@Nullable String catalogType) {
    this.catalogType = catalogType;
    return this;
  }

  /**
   * Indicates if the catalog is a product, service or resource catalog
   * @return catalogType
   */
  
  @Schema(name = "catalogType", description = "Indicates if the catalog is a product, service or resource catalog", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("catalogType")
  public @Nullable String getCatalogType() {
    return catalogType;
  }

  @JsonProperty("catalogType")
  public void setCatalogType(@Nullable String catalogType) {
    this.catalogType = catalogType;
  }

  public CatalogUpdate description(@Nullable String description) {
    this.description = description;
    return this;
  }

  /**
   * Description of this catalog
   * @return description
   */
  
  @Schema(name = "description", description = "Description of this catalog", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public @Nullable String getDescription() {
    return description;
  }

  @JsonProperty("description")
  public void setDescription(@Nullable String description) {
    this.description = description;
  }

  public CatalogUpdate lifecycleStatus(@Nullable String lifecycleStatus) {
    this.lifecycleStatus = lifecycleStatus;
    return this;
  }

  /**
   * Used to indicate the current lifecycle status
   * @return lifecycleStatus
   */
  
  @Schema(name = "lifecycleStatus", description = "Used to indicate the current lifecycle status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lifecycleStatus")
  public @Nullable String getLifecycleStatus() {
    return lifecycleStatus;
  }

  @JsonProperty("lifecycleStatus")
  public void setLifecycleStatus(@Nullable String lifecycleStatus) {
    this.lifecycleStatus = lifecycleStatus;
  }

  public CatalogUpdate name(@Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the catalog
   * @return name
   */
  
  @Schema(name = "name", description = "Name of the catalog", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public @Nullable String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(@Nullable String name) {
    this.name = name;
  }

  public CatalogUpdate version(@Nullable String version) {
    this.version = version;
    return this;
  }

  /**
   * Catalog version
   * @return version
   */
  
  @Schema(name = "version", description = "Catalog version", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("version")
  public @Nullable String getVersion() {
    return version;
  }

  @JsonProperty("version")
  public void setVersion(@Nullable String version) {
    this.version = version;
  }

  public CatalogUpdate category(List<@Valid CategoryRef> category) {
    this.category = category;
    return this;
  }

  public CatalogUpdate addCategoryItem(CategoryRef categoryItem) {
    if (this.category == null) {
      this.category = new ArrayList<>();
    }
    this.category.add(categoryItem);
    return this;
  }

  /**
   * List of root categories contained in this catalog
   * @return category
   */
  @Valid 
  @Schema(name = "category", description = "List of root categories contained in this catalog", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("category")
  public List<@Valid CategoryRef> getCategory() {
    return category;
  }

  @JsonProperty("category")
  public void setCategory(List<@Valid CategoryRef> category) {
    this.category = category;
  }

  public CatalogUpdate relatedParty(List<@Valid RelatedParty> relatedParty) {
    this.relatedParty = relatedParty;
    return this;
  }

  public CatalogUpdate addRelatedPartyItem(RelatedParty relatedPartyItem) {
    if (this.relatedParty == null) {
      this.relatedParty = new ArrayList<>();
    }
    this.relatedParty.add(relatedPartyItem);
    return this;
  }

  /**
   * List of parties involved in this catalog
   * @return relatedParty
   */
  @Valid 
  @Schema(name = "relatedParty", description = "List of parties involved in this catalog", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("relatedParty")
  public List<@Valid RelatedParty> getRelatedParty() {
    return relatedParty;
  }

  @JsonProperty("relatedParty")
  public void setRelatedParty(List<@Valid RelatedParty> relatedParty) {
    this.relatedParty = relatedParty;
  }

  public CatalogUpdate validFor(@Nullable TimePeriod validFor) {
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

  public CatalogUpdate atBaseType(@Nullable String atBaseType) {
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

  public CatalogUpdate atSchemaLocation(@Nullable URI atSchemaLocation) {
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

  public CatalogUpdate atType(@Nullable String atType) {
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
    CatalogUpdate catalogUpdate = (CatalogUpdate) o;
    return Objects.equals(this.catalogType, catalogUpdate.catalogType) &&
        Objects.equals(this.description, catalogUpdate.description) &&
        Objects.equals(this.lifecycleStatus, catalogUpdate.lifecycleStatus) &&
        Objects.equals(this.name, catalogUpdate.name) &&
        Objects.equals(this.version, catalogUpdate.version) &&
        Objects.equals(this.category, catalogUpdate.category) &&
        Objects.equals(this.relatedParty, catalogUpdate.relatedParty) &&
        Objects.equals(this.validFor, catalogUpdate.validFor) &&
        Objects.equals(this.atBaseType, catalogUpdate.atBaseType) &&
        Objects.equals(this.atSchemaLocation, catalogUpdate.atSchemaLocation) &&
        Objects.equals(this.atType, catalogUpdate.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(catalogType, description, lifecycleStatus, name, version, category, relatedParty, validFor, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CatalogUpdate {\n");
    sb.append("    catalogType: ").append(toIndentedString(catalogType)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    lifecycleStatus: ").append(toIndentedString(lifecycleStatus)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    relatedParty: ").append(toIndentedString(relatedParty)).append("\n");
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

