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
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;

/**
 * ServiceLevelAgreement reference: A service level agreement (SLA) is a type of agreement that represents a formal negotiated agreement between two parties designed to create a common understanding about products, services, priorities, responsibilities, and so forth. The SLA is a set of appropriate procedures and targets formally or informally agreed between parties in order to achieve and maintain specified Quality of Service.
 */

@Embeddable
@Schema(name = "SLARef", description = "ServiceLevelAgreement reference: A service level agreement (SLA) is a type of agreement that represents a formal negotiated agreement between two parties designed to create a common understanding about products, services, priorities, responsibilities, and so forth. The SLA is a set of appropriate procedures and targets formally or informally agreed between parties in order to achieve and maintain specified Quality of Service.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T16:22:46.010747900+05:30[Asia/Calcutta]", comments = "Generator version: 7.22.0")
public class SLARef {

  private String id;

  private @Nullable String href;

  private @Nullable String name;

  private @Nullable String atBaseType;

  @Transient
  private @Nullable URI atSchemaLocation;

  private @Nullable String atType;

  private @Nullable String atReferredType;

  public SLARef() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SLARef(String id) {
    this.id = id;
  }

  public SLARef id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier of service level agreement
   * @return id
   */
  @NotNull 
  @Schema(name = "id", description = "Unique identifier of service level agreement", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(String id) {
    this.id = id;
  }

  public SLARef href(@Nullable String href) {
    this.href = href;
    return this;
  }

  /**
   * Reference of the service level agreement
   * @return href
   */
  
  @Schema(name = "href", description = "Reference of the service level agreement", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("href")
  public @Nullable String getHref() {
    return href;
  }

  @JsonProperty("href")
  public void setHref(@Nullable String href) {
    this.href = href;
  }

  public SLARef name(@Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the service level agreement
   * @return name
   */
  
  @Schema(name = "name", description = "Name of the service level agreement", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public @Nullable String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(@Nullable String name) {
    this.name = name;
  }

  public SLARef atBaseType(@Nullable String atBaseType) {
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

  public SLARef atSchemaLocation(@Nullable URI atSchemaLocation) {
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

  public SLARef atType(@Nullable String atType) {
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

  public SLARef atReferredType(@Nullable String atReferredType) {
    this.atReferredType = atReferredType;
    return this;
  }

  /**
   * The actual type of the target instance when needed for disambiguation.
   * @return atReferredType
   */
  
  @Schema(name = "@referredType", description = "The actual type of the target instance when needed for disambiguation.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("@referredType")
  public @Nullable String getAtReferredType() {
    return atReferredType;
  }

  @JsonProperty("@referredType")
  public void setAtReferredType(@Nullable String atReferredType) {
    this.atReferredType = atReferredType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SLARef slARef = (SLARef) o;
    return Objects.equals(this.id, slARef.id) &&
        Objects.equals(this.href, slARef.href) &&
        Objects.equals(this.name, slARef.name) &&
        Objects.equals(this.atBaseType, slARef.atBaseType) &&
        Objects.equals(this.atSchemaLocation, slARef.atSchemaLocation) &&
        Objects.equals(this.atType, slARef.atType) &&
        Objects.equals(this.atReferredType, slARef.atReferredType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, href, name, atBaseType, atSchemaLocation, atType, atReferredType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SLARef {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    atBaseType: ").append(toIndentedString(atBaseType)).append("\n");
    sb.append("    atSchemaLocation: ").append(toIndentedString(atSchemaLocation)).append("\n");
    sb.append("    atType: ").append(toIndentedString(atType)).append("\n");
    sb.append("    atReferredType: ").append(toIndentedString(atReferredType)).append("\n");
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

