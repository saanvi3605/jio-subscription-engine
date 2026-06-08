package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.model.CategoryRef;
import org.openapitools.model.ProductOfferingRef;
import org.openapitools.model.TimePeriod;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * The category resource is used to group product offerings, service and resource candidates in logical containers. Categories can contain other categories and/or product offerings, resource or service candidates.
 */

@Schema(name = "Category", description = "The category resource is used to group product offerings, service and resource candidates in logical containers. Categories can contain other categories and/or product offerings, resource or service candidates.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T16:22:46.010747900+05:30[Asia/Calcutta]", comments = "Generator version: 7.22.0")
public class Category {

  private @Nullable String id;

  private @Nullable String href;

  private @Nullable String description;

  private @Nullable Boolean isRoot;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime lastUpdate;

  private @Nullable String lifecycleStatus;

  private @Nullable String name;

  private @Nullable String parentId;

  private @Nullable String version;

  @Valid
  private List<@Valid ProductOfferingRef> productOffering = new ArrayList<>();

  @Valid
  private List<@Valid CategoryRef> subCategory = new ArrayList<>();

  private @Nullable TimePeriod validFor;

  private @Nullable String atBaseType;

  private @Nullable URI atSchemaLocation;

  private @Nullable String atType;

  public Category id(@Nullable String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier of the category
   * @return id
   */
  
  @Schema(name = "id", description = "Unique identifier of the category", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public @Nullable String getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(@Nullable String id) {
    this.id = id;
  }

  public Category href(@Nullable String href) {
    this.href = href;
    return this;
  }

  /**
   * Reference of the category
   * @return href
   */
  
  @Schema(name = "href", description = "Reference of the category", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("href")
  public @Nullable String getHref() {
    return href;
  }

  @JsonProperty("href")
  public void setHref(@Nullable String href) {
    this.href = href;
  }

  public Category description(@Nullable String description) {
    this.description = description;
    return this;
  }

  /**
   * Description of the category
   * @return description
   */
  
  @Schema(name = "description", description = "Description of the category", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public @Nullable String getDescription() {
    return description;
  }

  @JsonProperty("description")
  public void setDescription(@Nullable String description) {
    this.description = description;
  }

  public Category isRoot(@Nullable Boolean isRoot) {
    this.isRoot = isRoot;
    return this;
  }

  /**
   * If true, this Boolean indicates that the category is a root of categories
   * @return isRoot
   */
  
  @Schema(name = "isRoot", description = "If true, this Boolean indicates that the category is a root of categories", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isRoot")
  public @Nullable Boolean getIsRoot() {
    return isRoot;
  }

  @JsonProperty("isRoot")
  public void setIsRoot(@Nullable Boolean isRoot) {
    this.isRoot = isRoot;
  }

  public Category lastUpdate(@Nullable OffsetDateTime lastUpdate) {
    this.lastUpdate = lastUpdate;
    return this;
  }

  /**
   * Date and time of the last update
   * @return lastUpdate
   */
  @Valid 
  @Schema(name = "lastUpdate", description = "Date and time of the last update", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lastUpdate")
  public @Nullable OffsetDateTime getLastUpdate() {
    return lastUpdate;
  }

  @JsonProperty("lastUpdate")
  public void setLastUpdate(@Nullable OffsetDateTime lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  public Category lifecycleStatus(@Nullable String lifecycleStatus) {
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

  public Category name(@Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the category
   * @return name
   */
  
  @Schema(name = "name", description = "Name of the category", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public @Nullable String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(@Nullable String name) {
    this.name = name;
  }

  public Category parentId(@Nullable String parentId) {
    this.parentId = parentId;
    return this;
  }

  /**
   * Unique identifier of the parent category
   * @return parentId
   */
  
  @Schema(name = "parentId", description = "Unique identifier of the parent category", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("parentId")
  public @Nullable String getParentId() {
    return parentId;
  }

  @JsonProperty("parentId")
  public void setParentId(@Nullable String parentId) {
    this.parentId = parentId;
  }

  public Category version(@Nullable String version) {
    this.version = version;
    return this;
  }

  /**
   * Category version
   * @return version
   */
  
  @Schema(name = "version", description = "Category version", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("version")
  public @Nullable String getVersion() {
    return version;
  }

  @JsonProperty("version")
  public void setVersion(@Nullable String version) {
    this.version = version;
  }

  public Category productOffering(List<@Valid ProductOfferingRef> productOffering) {
    this.productOffering = productOffering;
    return this;
  }

  public Category addProductOfferingItem(ProductOfferingRef productOfferingItem) {
    if (this.productOffering == null) {
      this.productOffering = new ArrayList<>();
    }
    this.productOffering.add(productOfferingItem);
    return this;
  }

  /**
   * A product offering represents entities that are orderable from the provider of the catalog, this resource includes pricing information.
   * @return productOffering
   */
  @Valid 
  @Schema(name = "productOffering", description = "A product offering represents entities that are orderable from the provider of the catalog, this resource includes pricing information.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("productOffering")
  public List<@Valid ProductOfferingRef> getProductOffering() {
    return productOffering;
  }

  @JsonProperty("productOffering")
  public void setProductOffering(List<@Valid ProductOfferingRef> productOffering) {
    this.productOffering = productOffering;
  }

  public Category subCategory(List<@Valid CategoryRef> subCategory) {
    this.subCategory = subCategory;
    return this;
  }

  public Category addSubCategoryItem(CategoryRef subCategoryItem) {
    if (this.subCategory == null) {
      this.subCategory = new ArrayList<>();
    }
    this.subCategory.add(subCategoryItem);
    return this;
  }

  /**
   * The category resource is used to group product offerings, service and resource candidates in logical containers. Categories can contain other (sub-)categories and/or product offerings.
   * @return subCategory
   */
  @Valid 
  @Schema(name = "subCategory", description = "The category resource is used to group product offerings, service and resource candidates in logical containers. Categories can contain other (sub-)categories and/or product offerings.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("subCategory")
  public List<@Valid CategoryRef> getSubCategory() {
    return subCategory;
  }

  @JsonProperty("subCategory")
  public void setSubCategory(List<@Valid CategoryRef> subCategory) {
    this.subCategory = subCategory;
  }

  public Category validFor(@Nullable TimePeriod validFor) {
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

  public Category atBaseType(@Nullable String atBaseType) {
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

  public Category atSchemaLocation(@Nullable URI atSchemaLocation) {
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

  public Category atType(@Nullable String atType) {
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
    Category category = (Category) o;
    return Objects.equals(this.id, category.id) &&
        Objects.equals(this.href, category.href) &&
        Objects.equals(this.description, category.description) &&
        Objects.equals(this.isRoot, category.isRoot) &&
        Objects.equals(this.lastUpdate, category.lastUpdate) &&
        Objects.equals(this.lifecycleStatus, category.lifecycleStatus) &&
        Objects.equals(this.name, category.name) &&
        Objects.equals(this.parentId, category.parentId) &&
        Objects.equals(this.version, category.version) &&
        Objects.equals(this.productOffering, category.productOffering) &&
        Objects.equals(this.subCategory, category.subCategory) &&
        Objects.equals(this.validFor, category.validFor) &&
        Objects.equals(this.atBaseType, category.atBaseType) &&
        Objects.equals(this.atSchemaLocation, category.atSchemaLocation) &&
        Objects.equals(this.atType, category.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, href, description, isRoot, lastUpdate, lifecycleStatus, name, parentId, version, productOffering, subCategory, validFor, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Category {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    isRoot: ").append(toIndentedString(isRoot)).append("\n");
    sb.append("    lastUpdate: ").append(toIndentedString(lastUpdate)).append("\n");
    sb.append("    lifecycleStatus: ").append(toIndentedString(lifecycleStatus)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    parentId: ").append(toIndentedString(parentId)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    productOffering: ").append(toIndentedString(productOffering)).append("\n");
    sb.append("    subCategory: ").append(toIndentedString(subCategory)).append("\n");
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

