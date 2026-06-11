package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;
import jakarta.persistence.Embeddable;

/**
 * The reference object to the schema and type of target service which is described by service specification
 */

@Embeddable
@Schema(name = "TargetServiceSchema", description = "The reference object to the schema and type of target service which is described by service specification")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T16:22:46.010747900+05:30[Asia/Calcutta]", comments = "Generator version: 7.22.0")
public class TargetServiceSchema {

  private @Nullable String atBaseType;

  private String atSchemaLocation;

  private String atType;

  public TargetServiceSchema() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TargetServiceSchema(String atSchemaLocation, String atType) {
    this.atSchemaLocation = atSchemaLocation;
    this.atType = atType;
  }

  public TargetServiceSchema atBaseType(@Nullable String atBaseType) {
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

  public TargetServiceSchema atSchemaLocation(String atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
    return this;
  }

  /**
   * This field provides a link to the schema describing the target service
   * @return atSchemaLocation
   */
  @NotNull 
  @Schema(name = "@schemaLocation", description = "This field provides a link to the schema describing the target service", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("@schemaLocation")
  public String getAtSchemaLocation() {
    return atSchemaLocation;
  }

  @JsonProperty("@schemaLocation")
  public void setAtSchemaLocation(String atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
  }

  public TargetServiceSchema atType(String atType) {
    this.atType = atType;
    return this;
  }

  /**
   * Class type of the target service
   * @return atType
   */
  @NotNull 
  @Schema(name = "@type", description = "Class type of the target service", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("@type")
  public String getAtType() {
    return atType;
  }

  @JsonProperty("@type")
  public void setAtType(String atType) {
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
    TargetServiceSchema targetServiceSchema = (TargetServiceSchema) o;
    return Objects.equals(this.atBaseType, targetServiceSchema.atBaseType) &&
        Objects.equals(this.atSchemaLocation, targetServiceSchema.atSchemaLocation) &&
        Objects.equals(this.atType, targetServiceSchema.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TargetServiceSchema {\n");
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

