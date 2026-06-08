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
 * This represents a bundling pricing relationship, allowing a price to be composed of multiple other prices (e.g. a recurring charge and a onetime charge).
 */

@Schema(name = "BundledProductOfferingPriceRelationship", description = "This represents a bundling pricing relationship, allowing a price to be composed of multiple other prices (e.g. a recurring charge and a onetime charge).")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T16:22:46.010747900+05:30[Asia/Calcutta]", comments = "Generator version: 7.22.0")
public class BundledProductOfferingPriceRelationship {

  private @Nullable String id;

  private @Nullable String href;

  private @Nullable String name;

  private @Nullable String atBaseType;

  private @Nullable URI atSchemaLocation;

  private @Nullable String atType;

  public BundledProductOfferingPriceRelationship id(@Nullable String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier of the bundled product offering price
   * @return id
   */
  
  @Schema(name = "id", description = "Unique identifier of the bundled product offering price", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public @Nullable String getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(@Nullable String id) {
    this.id = id;
  }

  public BundledProductOfferingPriceRelationship href(@Nullable String href) {
    this.href = href;
    return this;
  }

  /**
   * hyperlink reference of the bundled product offering price
   * @return href
   */
  
  @Schema(name = "href", description = "hyperlink reference of the bundled product offering price", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("href")
  public @Nullable String getHref() {
    return href;
  }

  @JsonProperty("href")
  public void setHref(@Nullable String href) {
    this.href = href;
  }

  public BundledProductOfferingPriceRelationship name(@Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the bundled product offering price
   * @return name
   */
  
  @Schema(name = "name", description = "Name of the bundled product offering price", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public @Nullable String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(@Nullable String name) {
    this.name = name;
  }

  public BundledProductOfferingPriceRelationship atBaseType(@Nullable String atBaseType) {
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

  public BundledProductOfferingPriceRelationship atSchemaLocation(@Nullable URI atSchemaLocation) {
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

  public BundledProductOfferingPriceRelationship atType(@Nullable String atType) {
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
    BundledProductOfferingPriceRelationship bundledProductOfferingPriceRelationship = (BundledProductOfferingPriceRelationship) o;
    return Objects.equals(this.id, bundledProductOfferingPriceRelationship.id) &&
        Objects.equals(this.href, bundledProductOfferingPriceRelationship.href) &&
        Objects.equals(this.name, bundledProductOfferingPriceRelationship.name) &&
        Objects.equals(this.atBaseType, bundledProductOfferingPriceRelationship.atBaseType) &&
        Objects.equals(this.atSchemaLocation, bundledProductOfferingPriceRelationship.atSchemaLocation) &&
        Objects.equals(this.atType, bundledProductOfferingPriceRelationship.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, href, name, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BundledProductOfferingPriceRelationship {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

