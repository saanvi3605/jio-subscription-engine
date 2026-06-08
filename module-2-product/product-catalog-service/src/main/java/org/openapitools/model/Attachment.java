package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.net.URI;
import org.openapitools.model.Quantity;
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
 * Complements the description of an element (for instance a product) through video, pictures...
 */

@Schema(name = "Attachment", description = "Complements the description of an element (for instance a product) through video, pictures...")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T16:22:46.010747900+05:30[Asia/Calcutta]", comments = "Generator version: 7.22.0")
public class Attachment {

  private @Nullable String id;

  private @Nullable String href;

  private @Nullable String attachmentType;

  private @Nullable String content;

  private @Nullable String description;

  private @Nullable String mimeType;

  private @Nullable String name;

  private @Nullable String url;

  private @Nullable Quantity size;

  private @Nullable TimePeriod validFor;

  private @Nullable String atBaseType;

  private @Nullable URI atSchemaLocation;

  private @Nullable String atType;

  public Attachment id(@Nullable String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier for this particular attachment
   * @return id
   */
  
  @Schema(name = "id", description = "Unique identifier for this particular attachment", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public @Nullable String getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(@Nullable String id) {
    this.id = id;
  }

  public Attachment href(@Nullable String href) {
    this.href = href;
    return this;
  }

  /**
   * URI for this Attachment
   * @return href
   */
  
  @Schema(name = "href", description = "URI for this Attachment", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("href")
  public @Nullable String getHref() {
    return href;
  }

  @JsonProperty("href")
  public void setHref(@Nullable String href) {
    this.href = href;
  }

  public Attachment attachmentType(@Nullable String attachmentType) {
    this.attachmentType = attachmentType;
    return this;
  }

  /**
   * Attachment type such as video, picture
   * @return attachmentType
   */
  
  @Schema(name = "attachmentType", description = "Attachment type such as video, picture", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("attachmentType")
  public @Nullable String getAttachmentType() {
    return attachmentType;
  }

  @JsonProperty("attachmentType")
  public void setAttachmentType(@Nullable String attachmentType) {
    this.attachmentType = attachmentType;
  }

  public Attachment content(@Nullable String content) {
    this.content = content;
    return this;
  }

  /**
   * The actual contents of the attachment object, if embedded, encoded as base64
   * @return content
   */
  
  @Schema(name = "content", description = "The actual contents of the attachment object, if embedded, encoded as base64", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("content")
  public @Nullable String getContent() {
    return content;
  }

  @JsonProperty("content")
  public void setContent(@Nullable String content) {
    this.content = content;
  }

  public Attachment description(@Nullable String description) {
    this.description = description;
    return this;
  }

  /**
   * A narrative text describing the content of the attachment
   * @return description
   */
  
  @Schema(name = "description", description = "A narrative text describing the content of the attachment", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public @Nullable String getDescription() {
    return description;
  }

  @JsonProperty("description")
  public void setDescription(@Nullable String description) {
    this.description = description;
  }

  public Attachment mimeType(@Nullable String mimeType) {
    this.mimeType = mimeType;
    return this;
  }

  /**
   * Attachment mime type such as extension file for video, picture and document
   * @return mimeType
   */
  
  @Schema(name = "mimeType", description = "Attachment mime type such as extension file for video, picture and document", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("mimeType")
  public @Nullable String getMimeType() {
    return mimeType;
  }

  @JsonProperty("mimeType")
  public void setMimeType(@Nullable String mimeType) {
    this.mimeType = mimeType;
  }

  public Attachment name(@Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the attachment
   * @return name
   */
  
  @Schema(name = "name", description = "The name of the attachment", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public @Nullable String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(@Nullable String name) {
    this.name = name;
  }

  public Attachment url(@Nullable String url) {
    this.url = url;
    return this;
  }

  /**
   * Uniform Resource Locator, is a web page address (a subset of URI)
   * @return url
   */
  
  @Schema(name = "url", description = "Uniform Resource Locator, is a web page address (a subset of URI)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("url")
  public @Nullable String getUrl() {
    return url;
  }

  @JsonProperty("url")
  public void setUrl(@Nullable String url) {
    this.url = url;
  }

  public Attachment size(@Nullable Quantity size) {
    this.size = size;
    return this;
  }

  /**
   * Get size
   * @return size
   */
  @Valid 
  @Schema(name = "size", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("size")
  public @Nullable Quantity getSize() {
    return size;
  }

  @JsonProperty("size")
  public void setSize(@Nullable Quantity size) {
    this.size = size;
  }

  public Attachment validFor(@Nullable TimePeriod validFor) {
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

  public Attachment atBaseType(@Nullable String atBaseType) {
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

  public Attachment atSchemaLocation(@Nullable URI atSchemaLocation) {
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

  public Attachment atType(@Nullable String atType) {
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
    Attachment attachment = (Attachment) o;
    return Objects.equals(this.id, attachment.id) &&
        Objects.equals(this.href, attachment.href) &&
        Objects.equals(this.attachmentType, attachment.attachmentType) &&
        Objects.equals(this.content, attachment.content) &&
        Objects.equals(this.description, attachment.description) &&
        Objects.equals(this.mimeType, attachment.mimeType) &&
        Objects.equals(this.name, attachment.name) &&
        Objects.equals(this.url, attachment.url) &&
        Objects.equals(this.size, attachment.size) &&
        Objects.equals(this.validFor, attachment.validFor) &&
        Objects.equals(this.atBaseType, attachment.atBaseType) &&
        Objects.equals(this.atSchemaLocation, attachment.atSchemaLocation) &&
        Objects.equals(this.atType, attachment.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, href, attachmentType, content, description, mimeType, name, url, size, validFor, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Attachment {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    attachmentType: ").append(toIndentedString(attachmentType)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    mimeType: ").append(toIndentedString(mimeType)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
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

