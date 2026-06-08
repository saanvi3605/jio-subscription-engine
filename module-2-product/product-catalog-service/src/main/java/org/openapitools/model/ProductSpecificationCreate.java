package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.model.AttachmentRefOrValue;
import org.openapitools.model.BundledProductSpecification;
import org.openapitools.model.ProductSpecificationCharacteristic;
import org.openapitools.model.ProductSpecificationRelationship;
import org.openapitools.model.RelatedParty;
import org.openapitools.model.ResourceSpecificationRef;
import org.openapitools.model.ServiceSpecificationRef;
import org.openapitools.model.TargetProductSchema;
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
 * Is a detailed description of a tangible or intangible object made available externally in the form of a ProductOffering to customers or other parties playing a party role. Skipped properties: id,href
 */

@Schema(name = "ProductSpecification_Create", description = "Is a detailed description of a tangible or intangible object made available externally in the form of a ProductOffering to customers or other parties playing a party role. Skipped properties: id,href")
@JsonTypeName("ProductSpecification_Create")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T16:22:46.010747900+05:30[Asia/Calcutta]", comments = "Generator version: 7.22.0")
public class ProductSpecificationCreate {

  private @Nullable String brand;

  private @Nullable String description;

  private @Nullable Boolean isBundle;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime lastUpdate;

  private @Nullable String lifecycleStatus;

  private String name;

  private @Nullable String productNumber;

  private @Nullable String version;

  @Valid
  private List<@Valid AttachmentRefOrValue> attachment = new ArrayList<>();

  @Valid
  private List<@Valid BundledProductSpecification> bundledProductSpecification = new ArrayList<>();

  @Valid
  private List<@Valid ProductSpecificationCharacteristic> productSpecCharacteristic = new ArrayList<>();

  @Valid
  private List<@Valid ProductSpecificationRelationship> productSpecificationRelationship = new ArrayList<>();

  @Valid
  private List<@Valid RelatedParty> relatedParty = new ArrayList<>();

  @Valid
  private List<@Valid ResourceSpecificationRef> resourceSpecification = new ArrayList<>();

  @Valid
  private List<@Valid ServiceSpecificationRef> serviceSpecification = new ArrayList<>();

  private @Nullable TargetProductSchema targetProductSchema;

  private @Nullable TimePeriod validFor;

  private @Nullable String atBaseType;

  private @Nullable URI atSchemaLocation;

  private @Nullable String atType;

  public ProductSpecificationCreate() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProductSpecificationCreate(String name) {
    this.name = name;
  }

  public ProductSpecificationCreate brand(@Nullable String brand) {
    this.brand = brand;
    return this;
  }

  /**
   * The manufacturer or trademark of the specification
   * @return brand
   */
  
  @Schema(name = "brand", description = "The manufacturer or trademark of the specification", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("brand")
  public @Nullable String getBrand() {
    return brand;
  }

  @JsonProperty("brand")
  public void setBrand(@Nullable String brand) {
    this.brand = brand;
  }

  public ProductSpecificationCreate description(@Nullable String description) {
    this.description = description;
    return this;
  }

  /**
   * A narrative that explains in detail what the product specification is
   * @return description
   */
  
  @Schema(name = "description", description = "A narrative that explains in detail what the product specification is", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public @Nullable String getDescription() {
    return description;
  }

  @JsonProperty("description")
  public void setDescription(@Nullable String description) {
    this.description = description;
  }

  public ProductSpecificationCreate isBundle(@Nullable Boolean isBundle) {
    this.isBundle = isBundle;
    return this;
  }

  /**
   * isBundle determines whether a productSpecification represents a single productSpecification (false), or a bundle of productSpecification (true).
   * @return isBundle
   */
  
  @Schema(name = "isBundle", description = "isBundle determines whether a productSpecification represents a single productSpecification (false), or a bundle of productSpecification (true).", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isBundle")
  public @Nullable Boolean getIsBundle() {
    return isBundle;
  }

  @JsonProperty("isBundle")
  public void setIsBundle(@Nullable Boolean isBundle) {
    this.isBundle = isBundle;
  }

  public ProductSpecificationCreate lastUpdate(@Nullable OffsetDateTime lastUpdate) {
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

  public ProductSpecificationCreate lifecycleStatus(@Nullable String lifecycleStatus) {
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

  public ProductSpecificationCreate name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the product specification
   * @return name
   */
  @NotNull 
  @Schema(name = "name", description = "Name of the product specification", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  public ProductSpecificationCreate productNumber(@Nullable String productNumber) {
    this.productNumber = productNumber;
    return this;
  }

  /**
   * An identification number assigned to uniquely identity the specification
   * @return productNumber
   */
  
  @Schema(name = "productNumber", description = "An identification number assigned to uniquely identity the specification", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("productNumber")
  public @Nullable String getProductNumber() {
    return productNumber;
  }

  @JsonProperty("productNumber")
  public void setProductNumber(@Nullable String productNumber) {
    this.productNumber = productNumber;
  }

  public ProductSpecificationCreate version(@Nullable String version) {
    this.version = version;
    return this;
  }

  /**
   * Product specification version
   * @return version
   */
  
  @Schema(name = "version", description = "Product specification version", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("version")
  public @Nullable String getVersion() {
    return version;
  }

  @JsonProperty("version")
  public void setVersion(@Nullable String version) {
    this.version = version;
  }

  public ProductSpecificationCreate attachment(List<@Valid AttachmentRefOrValue> attachment) {
    this.attachment = attachment;
    return this;
  }

  public ProductSpecificationCreate addAttachmentItem(AttachmentRefOrValue attachmentItem) {
    if (this.attachment == null) {
      this.attachment = new ArrayList<>();
    }
    this.attachment.add(attachmentItem);
    return this;
  }

  /**
   * Complements the description of an element (for instance a product) through video, pictures...
   * @return attachment
   */
  @Valid 
  @Schema(name = "attachment", description = "Complements the description of an element (for instance a product) through video, pictures...", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("attachment")
  public List<@Valid AttachmentRefOrValue> getAttachment() {
    return attachment;
  }

  @JsonProperty("attachment")
  public void setAttachment(List<@Valid AttachmentRefOrValue> attachment) {
    this.attachment = attachment;
  }

  public ProductSpecificationCreate bundledProductSpecification(List<@Valid BundledProductSpecification> bundledProductSpecification) {
    this.bundledProductSpecification = bundledProductSpecification;
    return this;
  }

  public ProductSpecificationCreate addBundledProductSpecificationItem(BundledProductSpecification bundledProductSpecificationItem) {
    if (this.bundledProductSpecification == null) {
      this.bundledProductSpecification = new ArrayList<>();
    }
    this.bundledProductSpecification.add(bundledProductSpecificationItem);
    return this;
  }

  /**
   * A type of ProductSpecification that belongs to a grouping of ProductSpecifications made available to the market. It inherits of all attributes of ProductSpecification.
   * @return bundledProductSpecification
   */
  @Valid 
  @Schema(name = "bundledProductSpecification", description = "A type of ProductSpecification that belongs to a grouping of ProductSpecifications made available to the market. It inherits of all attributes of ProductSpecification.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bundledProductSpecification")
  public List<@Valid BundledProductSpecification> getBundledProductSpecification() {
    return bundledProductSpecification;
  }

  @JsonProperty("bundledProductSpecification")
  public void setBundledProductSpecification(List<@Valid BundledProductSpecification> bundledProductSpecification) {
    this.bundledProductSpecification = bundledProductSpecification;
  }

  public ProductSpecificationCreate productSpecCharacteristic(List<@Valid ProductSpecificationCharacteristic> productSpecCharacteristic) {
    this.productSpecCharacteristic = productSpecCharacteristic;
    return this;
  }

  public ProductSpecificationCreate addProductSpecCharacteristicItem(ProductSpecificationCharacteristic productSpecCharacteristicItem) {
    if (this.productSpecCharacteristic == null) {
      this.productSpecCharacteristic = new ArrayList<>();
    }
    this.productSpecCharacteristic.add(productSpecCharacteristicItem);
    return this;
  }

  /**
   * A characteristic quality or distinctive feature of a ProductSpecification.  The characteristic can be take on a discrete value, such as color, can take on a range of values, (for example, sensitivity of 100-240 mV), or can be derived from a formula (for example, usage time (hrs) = 30 - talk time *3). Certain characteristics, such as color, may be configured during the ordering or some other process.
   * @return productSpecCharacteristic
   */
  @Valid 
  @Schema(name = "productSpecCharacteristic", description = "A characteristic quality or distinctive feature of a ProductSpecification.  The characteristic can be take on a discrete value, such as color, can take on a range of values, (for example, sensitivity of 100-240 mV), or can be derived from a formula (for example, usage time (hrs) = 30 - talk time *3). Certain characteristics, such as color, may be configured during the ordering or some other process.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("productSpecCharacteristic")
  public List<@Valid ProductSpecificationCharacteristic> getProductSpecCharacteristic() {
    return productSpecCharacteristic;
  }

  @JsonProperty("productSpecCharacteristic")
  public void setProductSpecCharacteristic(List<@Valid ProductSpecificationCharacteristic> productSpecCharacteristic) {
    this.productSpecCharacteristic = productSpecCharacteristic;
  }

  public ProductSpecificationCreate productSpecificationRelationship(List<@Valid ProductSpecificationRelationship> productSpecificationRelationship) {
    this.productSpecificationRelationship = productSpecificationRelationship;
    return this;
  }

  public ProductSpecificationCreate addProductSpecificationRelationshipItem(ProductSpecificationRelationship productSpecificationRelationshipItem) {
    if (this.productSpecificationRelationship == null) {
      this.productSpecificationRelationship = new ArrayList<>();
    }
    this.productSpecificationRelationship.add(productSpecificationRelationshipItem);
    return this;
  }

  /**
   * A migration, substitution, dependency or exclusivity relationship between/among product specifications.
   * @return productSpecificationRelationship
   */
  @Valid 
  @Schema(name = "productSpecificationRelationship", description = "A migration, substitution, dependency or exclusivity relationship between/among product specifications.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("productSpecificationRelationship")
  public List<@Valid ProductSpecificationRelationship> getProductSpecificationRelationship() {
    return productSpecificationRelationship;
  }

  @JsonProperty("productSpecificationRelationship")
  public void setProductSpecificationRelationship(List<@Valid ProductSpecificationRelationship> productSpecificationRelationship) {
    this.productSpecificationRelationship = productSpecificationRelationship;
  }

  public ProductSpecificationCreate relatedParty(List<@Valid RelatedParty> relatedParty) {
    this.relatedParty = relatedParty;
    return this;
  }

  public ProductSpecificationCreate addRelatedPartyItem(RelatedParty relatedPartyItem) {
    if (this.relatedParty == null) {
      this.relatedParty = new ArrayList<>();
    }
    this.relatedParty.add(relatedPartyItem);
    return this;
  }

  /**
   * A related party defines party or party role linked to a specific entity.
   * @return relatedParty
   */
  @Valid 
  @Schema(name = "relatedParty", description = "A related party defines party or party role linked to a specific entity.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("relatedParty")
  public List<@Valid RelatedParty> getRelatedParty() {
    return relatedParty;
  }

  @JsonProperty("relatedParty")
  public void setRelatedParty(List<@Valid RelatedParty> relatedParty) {
    this.relatedParty = relatedParty;
  }

  public ProductSpecificationCreate resourceSpecification(List<@Valid ResourceSpecificationRef> resourceSpecification) {
    this.resourceSpecification = resourceSpecification;
    return this;
  }

  public ProductSpecificationCreate addResourceSpecificationItem(ResourceSpecificationRef resourceSpecificationItem) {
    if (this.resourceSpecification == null) {
      this.resourceSpecification = new ArrayList<>();
    }
    this.resourceSpecification.add(resourceSpecificationItem);
    return this;
  }

  /**
   * The ResourceSpecification is required to realize a ProductSpecification.
   * @return resourceSpecification
   */
  @Valid 
  @Schema(name = "resourceSpecification", description = "The ResourceSpecification is required to realize a ProductSpecification.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("resourceSpecification")
  public List<@Valid ResourceSpecificationRef> getResourceSpecification() {
    return resourceSpecification;
  }

  @JsonProperty("resourceSpecification")
  public void setResourceSpecification(List<@Valid ResourceSpecificationRef> resourceSpecification) {
    this.resourceSpecification = resourceSpecification;
  }

  public ProductSpecificationCreate serviceSpecification(List<@Valid ServiceSpecificationRef> serviceSpecification) {
    this.serviceSpecification = serviceSpecification;
    return this;
  }

  public ProductSpecificationCreate addServiceSpecificationItem(ServiceSpecificationRef serviceSpecificationItem) {
    if (this.serviceSpecification == null) {
      this.serviceSpecification = new ArrayList<>();
    }
    this.serviceSpecification.add(serviceSpecificationItem);
    return this;
  }

  /**
   * ServiceSpecification(s) required to realize a ProductSpecification.
   * @return serviceSpecification
   */
  @Valid 
  @Schema(name = "serviceSpecification", description = "ServiceSpecification(s) required to realize a ProductSpecification.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("serviceSpecification")
  public List<@Valid ServiceSpecificationRef> getServiceSpecification() {
    return serviceSpecification;
  }

  @JsonProperty("serviceSpecification")
  public void setServiceSpecification(List<@Valid ServiceSpecificationRef> serviceSpecification) {
    this.serviceSpecification = serviceSpecification;
  }

  public ProductSpecificationCreate targetProductSchema(@Nullable TargetProductSchema targetProductSchema) {
    this.targetProductSchema = targetProductSchema;
    return this;
  }

  /**
   * Get targetProductSchema
   * @return targetProductSchema
   */
  @Valid 
  @Schema(name = "targetProductSchema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("targetProductSchema")
  public @Nullable TargetProductSchema getTargetProductSchema() {
    return targetProductSchema;
  }

  @JsonProperty("targetProductSchema")
  public void setTargetProductSchema(@Nullable TargetProductSchema targetProductSchema) {
    this.targetProductSchema = targetProductSchema;
  }

  public ProductSpecificationCreate validFor(@Nullable TimePeriod validFor) {
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

  public ProductSpecificationCreate atBaseType(@Nullable String atBaseType) {
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

  public ProductSpecificationCreate atSchemaLocation(@Nullable URI atSchemaLocation) {
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

  public ProductSpecificationCreate atType(@Nullable String atType) {
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
    ProductSpecificationCreate productSpecificationCreate = (ProductSpecificationCreate) o;
    return Objects.equals(this.brand, productSpecificationCreate.brand) &&
        Objects.equals(this.description, productSpecificationCreate.description) &&
        Objects.equals(this.isBundle, productSpecificationCreate.isBundle) &&
        Objects.equals(this.lastUpdate, productSpecificationCreate.lastUpdate) &&
        Objects.equals(this.lifecycleStatus, productSpecificationCreate.lifecycleStatus) &&
        Objects.equals(this.name, productSpecificationCreate.name) &&
        Objects.equals(this.productNumber, productSpecificationCreate.productNumber) &&
        Objects.equals(this.version, productSpecificationCreate.version) &&
        Objects.equals(this.attachment, productSpecificationCreate.attachment) &&
        Objects.equals(this.bundledProductSpecification, productSpecificationCreate.bundledProductSpecification) &&
        Objects.equals(this.productSpecCharacteristic, productSpecificationCreate.productSpecCharacteristic) &&
        Objects.equals(this.productSpecificationRelationship, productSpecificationCreate.productSpecificationRelationship) &&
        Objects.equals(this.relatedParty, productSpecificationCreate.relatedParty) &&
        Objects.equals(this.resourceSpecification, productSpecificationCreate.resourceSpecification) &&
        Objects.equals(this.serviceSpecification, productSpecificationCreate.serviceSpecification) &&
        Objects.equals(this.targetProductSchema, productSpecificationCreate.targetProductSchema) &&
        Objects.equals(this.validFor, productSpecificationCreate.validFor) &&
        Objects.equals(this.atBaseType, productSpecificationCreate.atBaseType) &&
        Objects.equals(this.atSchemaLocation, productSpecificationCreate.atSchemaLocation) &&
        Objects.equals(this.atType, productSpecificationCreate.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(brand, description, isBundle, lastUpdate, lifecycleStatus, name, productNumber, version, attachment, bundledProductSpecification, productSpecCharacteristic, productSpecificationRelationship, relatedParty, resourceSpecification, serviceSpecification, targetProductSchema, validFor, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductSpecificationCreate {\n");
    sb.append("    brand: ").append(toIndentedString(brand)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    isBundle: ").append(toIndentedString(isBundle)).append("\n");
    sb.append("    lastUpdate: ").append(toIndentedString(lastUpdate)).append("\n");
    sb.append("    lifecycleStatus: ").append(toIndentedString(lifecycleStatus)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    productNumber: ").append(toIndentedString(productNumber)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    attachment: ").append(toIndentedString(attachment)).append("\n");
    sb.append("    bundledProductSpecification: ").append(toIndentedString(bundledProductSpecification)).append("\n");
    sb.append("    productSpecCharacteristic: ").append(toIndentedString(productSpecCharacteristic)).append("\n");
    sb.append("    productSpecificationRelationship: ").append(toIndentedString(productSpecificationRelationship)).append("\n");
    sb.append("    relatedParty: ").append(toIndentedString(relatedParty)).append("\n");
    sb.append("    resourceSpecification: ").append(toIndentedString(resourceSpecification)).append("\n");
    sb.append("    serviceSpecification: ").append(toIndentedString(serviceSpecification)).append("\n");
    sb.append("    targetProductSchema: ").append(toIndentedString(targetProductSchema)).append("\n");
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

