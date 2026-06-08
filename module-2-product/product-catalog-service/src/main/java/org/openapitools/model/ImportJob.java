package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.net.URI;
import java.time.OffsetDateTime;
import org.openapitools.model.JobStateType;
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
 * Represents a task used to import resources from a file
 */

@Schema(name = "ImportJob", description = "Represents a task used to import resources from a file")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T16:22:46.010747900+05:30[Asia/Calcutta]", comments = "Generator version: 7.22.0")
public class ImportJob {

  private @Nullable String id;

  private @Nullable String href;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime completionDate;

  private @Nullable String contentType;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime creationDate;

  private @Nullable String errorLog;

  private @Nullable String path;

  private @Nullable String url;

  private @Nullable JobStateType status;

  private @Nullable String atBaseType;

  private @Nullable URI atSchemaLocation;

  private @Nullable String atType;

  public ImportJob id(@Nullable String id) {
    this.id = id;
    return this;
  }

  /**
   * Identifier of the import job
   * @return id
   */
  
  @Schema(name = "id", description = "Identifier of the import job", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public @Nullable String getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(@Nullable String id) {
    this.id = id;
  }

  public ImportJob href(@Nullable String href) {
    this.href = href;
    return this;
  }

  /**
   * Reference of the import job
   * @return href
   */
  
  @Schema(name = "href", description = "Reference of the import job", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("href")
  public @Nullable String getHref() {
    return href;
  }

  @JsonProperty("href")
  public void setHref(@Nullable String href) {
    this.href = href;
  }

  public ImportJob completionDate(@Nullable OffsetDateTime completionDate) {
    this.completionDate = completionDate;
    return this;
  }

  /**
   * Date at which the job was completed
   * @return completionDate
   */
  @Valid 
  @Schema(name = "completionDate", description = "Date at which the job was completed", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("completionDate")
  public @Nullable OffsetDateTime getCompletionDate() {
    return completionDate;
  }

  @JsonProperty("completionDate")
  public void setCompletionDate(@Nullable OffsetDateTime completionDate) {
    this.completionDate = completionDate;
  }

  public ImportJob contentType(@Nullable String contentType) {
    this.contentType = contentType;
    return this;
  }

  /**
   * Indicates the format of the imported data
   * @return contentType
   */
  
  @Schema(name = "contentType", description = "Indicates the format of the imported data", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("contentType")
  public @Nullable String getContentType() {
    return contentType;
  }

  @JsonProperty("contentType")
  public void setContentType(@Nullable String contentType) {
    this.contentType = contentType;
  }

  public ImportJob creationDate(@Nullable OffsetDateTime creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Date at which the job was created
   * @return creationDate
   */
  @Valid 
  @Schema(name = "creationDate", description = "Date at which the job was created", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("creationDate")
  public @Nullable OffsetDateTime getCreationDate() {
    return creationDate;
  }

  @JsonProperty("creationDate")
  public void setCreationDate(@Nullable OffsetDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public ImportJob errorLog(@Nullable String errorLog) {
    this.errorLog = errorLog;
    return this;
  }

  /**
   * Reason for failure if status is failed
   * @return errorLog
   */
  
  @Schema(name = "errorLog", description = "Reason for failure if status is failed", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("errorLog")
  public @Nullable String getErrorLog() {
    return errorLog;
  }

  @JsonProperty("errorLog")
  public void setErrorLog(@Nullable String errorLog) {
    this.errorLog = errorLog;
  }

  public ImportJob path(@Nullable String path) {
    this.path = path;
    return this;
  }

  /**
   * URL of the root resource where the content of the file specified by the import job must be applied
   * @return path
   */
  
  @Schema(name = "path", description = "URL of the root resource where the content of the file specified by the import job must be applied", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("path")
  public @Nullable String getPath() {
    return path;
  }

  @JsonProperty("path")
  public void setPath(@Nullable String path) {
    this.path = path;
  }

  public ImportJob url(@Nullable String url) {
    this.url = url;
    return this;
  }

  /**
   * URL of the file containing the data to be imported
   * @return url
   */
  
  @Schema(name = "url", description = "URL of the file containing the data to be imported", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("url")
  public @Nullable String getUrl() {
    return url;
  }

  @JsonProperty("url")
  public void setUrl(@Nullable String url) {
    this.url = url;
  }

  public ImportJob status(@Nullable JobStateType status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   */
  @Valid 
  @Schema(name = "status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status")
  public @Nullable JobStateType getStatus() {
    return status;
  }

  @JsonProperty("status")
  public void setStatus(@Nullable JobStateType status) {
    this.status = status;
  }

  public ImportJob atBaseType(@Nullable String atBaseType) {
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

  public ImportJob atSchemaLocation(@Nullable URI atSchemaLocation) {
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

  public ImportJob atType(@Nullable String atType) {
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
    ImportJob importJob = (ImportJob) o;
    return Objects.equals(this.id, importJob.id) &&
        Objects.equals(this.href, importJob.href) &&
        Objects.equals(this.completionDate, importJob.completionDate) &&
        Objects.equals(this.contentType, importJob.contentType) &&
        Objects.equals(this.creationDate, importJob.creationDate) &&
        Objects.equals(this.errorLog, importJob.errorLog) &&
        Objects.equals(this.path, importJob.path) &&
        Objects.equals(this.url, importJob.url) &&
        Objects.equals(this.status, importJob.status) &&
        Objects.equals(this.atBaseType, importJob.atBaseType) &&
        Objects.equals(this.atSchemaLocation, importJob.atSchemaLocation) &&
        Objects.equals(this.atType, importJob.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, href, completionDate, contentType, creationDate, errorLog, path, url, status, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ImportJob {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    completionDate: ").append(toIndentedString(completionDate)).append("\n");
    sb.append("    contentType: ").append(toIndentedString(contentType)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    errorLog: ").append(toIndentedString(errorLog)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

