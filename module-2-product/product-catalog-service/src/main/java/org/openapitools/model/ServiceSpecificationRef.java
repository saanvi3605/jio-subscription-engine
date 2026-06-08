package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.net.URI;
import org.openapitools.model.TargetServiceSchema;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Service specification reference: ServiceSpecification(s) required to realize a ProductSpecification.
 */

@Schema(name = "ServiceSpecificationRef", description = "Service specification reference: ServiceSpecification(s) required to realize a ProductSpecification.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T16:22:46.010747900+05:30[Asia/Calcutta]", comments = "Generator version: 7.22.0")
public class ServiceSpecificationRef {

  private String id;

  private @Nullable String href;

  private @Nullable String name;

  private @Nullable String version;

  private @Nullable TargetServiceSchema targetServiceSchema;

  private @Nullable String atBaseType;

  private @Nullable URI atSchemaLocation;

  private @Nullable String atType;

  private @Nullable String atReferredType;

  public ServiceSpecificationRef() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ServiceSpecificationRef(String id) {
    this.id = id;
  }

  public ServiceSpecificationRef id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier of the service specification
   * @return id
   */
  @NotNull 
  @Schema(name = "id", description = "Unique identifier of the service specification", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(String id) {
    this.id = id;
  }

  public ServiceSpecificationRef href(@Nullable String href) {
    this.href = href;
    return this;
  }

  /**
   * Reference of the serviceSpecification
   * @return href
   */
  
  @Schema(name = "href", description = "Reference of the serviceSpecification", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("href")
  public @Nullable String getHref() {
    return href;
  }

  @JsonProperty("href")
  public void setHref(@Nullable String href) {
    this.href = href;
  }

  public ServiceSpecificationRef name(@Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the requiredServiceSpecification
   * @return name
   */
  
  @Schema(name = "name", description = "Name of the requiredServiceSpecification", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public @Nullable String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(@Nullable String name) {
    this.name = name;
  }

  public ServiceSpecificationRef version(@Nullable String version) {
    this.version = version;
    return this;
  }

  /**
   * Service specification version
   * @return version
   */
  
  @Schema(name = "version", description = "Service specification version", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("version")
  public @Nullable String getVersion() {
    return version;
  }

  @JsonProperty("version")
  public void setVersion(@Nullable String version) {
    this.version = version;
  }

  public ServiceSpecificationRef targetServiceSchema(@Nullable TargetServiceSchema targetServiceSchema) {
    this.targetServiceSchema = targetServiceSchema;
    return this;
  }

  /**
   * Get targetServiceSchema
   * @return targetServiceSchema
   */
  @Valid 
  @Schema(name = "targetServiceSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("targetServiceSchema")
  public @Nullable TargetServiceSchema getTargetServiceSchema() {
    return targetServiceSchema;
  }

  @JsonProperty("targetServiceSchema")
  public void setTargetServiceSchema(@Nullable TargetServiceSchema targetServiceSchema) {
    this.targetServiceSchema = targetServiceSchema;
  }

  public ServiceSpecificationRef atBaseType(@Nullable String atBaseType) {
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

  public ServiceSpecificationRef atSchemaLocation(@Nullable URI atSchemaLocation) {
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

  public ServiceSpecificationRef atType(@Nullable String atType) {
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

  public ServiceSpecificationRef atReferredType(@Nullable String atReferredType) {
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
    ServiceSpecificationRef serviceSpecificationRef = (ServiceSpecificationRef) o;
    return Objects.equals(this.id, serviceSpecificationRef.id) &&
        Objects.equals(this.href, serviceSpecificationRef.href) &&
        Objects.equals(this.name, serviceSpecificationRef.name) &&
        Objects.equals(this.version, serviceSpecificationRef.version) &&
        Objects.equals(this.targetServiceSchema, serviceSpecificationRef.targetServiceSchema) &&
        Objects.equals(this.atBaseType, serviceSpecificationRef.atBaseType) &&
        Objects.equals(this.atSchemaLocation, serviceSpecificationRef.atSchemaLocation) &&
        Objects.equals(this.atType, serviceSpecificationRef.atType) &&
        Objects.equals(this.atReferredType, serviceSpecificationRef.atReferredType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, href, name, version, targetServiceSchema, atBaseType, atSchemaLocation, atType, atReferredType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceSpecificationRef {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    targetServiceSchema: ").append(toIndentedString(targetServiceSchema)).append("\n");
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

