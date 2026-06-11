package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.net.URI;
import org.openapitools.model.BundledProductOfferingOption;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Transient;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import org.hibernate.annotations.UuidGenerator;

/**
 * A type of ProductOffering that belongs to a grouping of ProductOfferings made available to the market. It inherits of all attributes of ProductOffering.
 */

@Entity
@Table(name = "bundled_product_offering")
@Schema(name = "BundledProductOffering", description = "A type of ProductOffering that belongs to a grouping of ProductOfferings made available to the market. It inherits of all attributes of ProductOffering.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T16:22:46.010747900+05:30[Asia/Calcutta]", comments = "Generator version: 7.22.0")
public class BundledProductOffering {

  @Id
  @UuidGenerator
  private @Nullable String id;

  private @Nullable String href;

  private @Nullable String lifecycleStatus;

  private @Nullable String name;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "atBaseType", column = @Column(name = "option_at_base_type")),
      @AttributeOverride(name = "atType",     column = @Column(name = "option_at_type"))
  })
  private @Nullable BundledProductOfferingOption bundledProductOfferingOption;

  private @Nullable String atBaseType;

  @Transient
  private @Nullable URI atSchemaLocation;

  private @Nullable String atType;

  public BundledProductOffering id(@Nullable String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier of the BundledProductOffering
   * @return id
   */
  
  @Schema(name = "id", description = "Unique identifier of the BundledProductOffering", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public @Nullable String getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(@Nullable String id) {
    this.id = id;
  }

  public BundledProductOffering href(@Nullable String href) {
    this.href = href;
    return this;
  }

  /**
   * Unique reference of the BundledProductOffering
   * @return href
   */
  
  @Schema(name = "href", description = "Unique reference of the BundledProductOffering", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("href")
  public @Nullable String getHref() {
    return href;
  }

  @JsonProperty("href")
  public void setHref(@Nullable String href) {
    this.href = href;
  }

  public BundledProductOffering lifecycleStatus(@Nullable String lifecycleStatus) {
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

  public BundledProductOffering name(@Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the BundledProductOffering
   * @return name
   */
  
  @Schema(name = "name", description = "Name of the BundledProductOffering", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public @Nullable String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(@Nullable String name) {
    this.name = name;
  }

  public BundledProductOffering bundledProductOfferingOption(@Nullable BundledProductOfferingOption bundledProductOfferingOption) {
    this.bundledProductOfferingOption = bundledProductOfferingOption;
    return this;
  }

  /**
   * Get bundledProductOfferingOption
   * @return bundledProductOfferingOption
   */
  @Valid 
  @Schema(name = "bundledProductOfferingOption", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bundledProductOfferingOption")
  public @Nullable BundledProductOfferingOption getBundledProductOfferingOption() {
    return bundledProductOfferingOption;
  }

  @JsonProperty("bundledProductOfferingOption")
  public void setBundledProductOfferingOption(@Nullable BundledProductOfferingOption bundledProductOfferingOption) {
    this.bundledProductOfferingOption = bundledProductOfferingOption;
  }

  public BundledProductOffering atBaseType(@Nullable String atBaseType) {
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

  public BundledProductOffering atSchemaLocation(@Nullable URI atSchemaLocation) {
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

  public BundledProductOffering atType(@Nullable String atType) {
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
    BundledProductOffering bundledProductOffering = (BundledProductOffering) o;
    return Objects.equals(this.id, bundledProductOffering.id) &&
        Objects.equals(this.href, bundledProductOffering.href) &&
        Objects.equals(this.lifecycleStatus, bundledProductOffering.lifecycleStatus) &&
        Objects.equals(this.name, bundledProductOffering.name) &&
        Objects.equals(this.bundledProductOfferingOption, bundledProductOffering.bundledProductOfferingOption) &&
        Objects.equals(this.atBaseType, bundledProductOffering.atBaseType) &&
        Objects.equals(this.atSchemaLocation, bundledProductOffering.atSchemaLocation) &&
        Objects.equals(this.atType, bundledProductOffering.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, href, lifecycleStatus, name, bundledProductOfferingOption, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BundledProductOffering {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    lifecycleStatus: ").append(toIndentedString(lifecycleStatus)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    bundledProductOfferingOption: ").append(toIndentedString(bundledProductOfferingOption)).append("\n");
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

