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
import org.openapitools.model.AgreementRef;
import org.openapitools.model.AttachmentRefOrValue;
import org.openapitools.model.BundledProductOffering;
import org.openapitools.model.CategoryRef;
import org.openapitools.model.ChannelRef;
import org.openapitools.model.MarketSegmentRef;
import org.openapitools.model.PlaceRef;
import org.openapitools.model.ProductOfferingPriceRef;
import org.openapitools.model.ProductOfferingTerm;
import org.openapitools.model.ProductSpecificationCharacteristicValueUse;
import org.openapitools.model.ProductSpecificationRef;
import org.openapitools.model.ResourceCandidateRef;
import org.openapitools.model.SLARef;
import org.openapitools.model.ServiceCandidateRef;
import org.openapitools.model.TimePeriod;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import org.hibernate.annotations.UuidGenerator;

import java.util.*;
import jakarta.annotation.Generated;

/**
 * Represents entities that are orderable from the provider of the catalog, this resource includes pricing information.
 */

@Entity
@Table(name = "product_offering")
@Schema(name = "ProductOffering", description = "Represents entities that are orderable from the provider of the catalog, this resource includes pricing information.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T16:22:46.010747900+05:30[Asia/Calcutta]", comments = "Generator version: 7.22.0")
public class ProductOffering {

  @Id
  @UuidGenerator
  private @Nullable String id;

  private @Nullable String href;

  private @Nullable String description;

  private @Nullable Boolean isBundle;

  private @Nullable Boolean isSellable;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime lastUpdate;

  private @Nullable String lifecycleStatus;

  private @Nullable String name;

  private @Nullable String statusReason;

  private @Nullable String version;

  @Valid
  @ElementCollection
  @CollectionTable(name = "po_agreement_ref", joinColumns = @JoinColumn(name = "product_offering_id"))
  private List<@Valid AgreementRef> agreement = new ArrayList<>();

  @Valid
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "product_offering_id")
  private List<@Valid AttachmentRefOrValue> attachment = new ArrayList<>();

  @Valid
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "product_offering_id")
  private List<@Valid BundledProductOffering> bundledProductOffering = new ArrayList<>();

  @Valid
  @ElementCollection
  @CollectionTable(name = "po_category_ref", joinColumns = @JoinColumn(name = "product_offering_id"))
  private List<@Valid CategoryRef> category = new ArrayList<>();

  @Valid
  @ElementCollection
  @CollectionTable(name = "po_channel_ref", joinColumns = @JoinColumn(name = "product_offering_id"))
  private List<@Valid ChannelRef> channel = new ArrayList<>();

  @Valid
  @ElementCollection
  @CollectionTable(name = "po_market_segment_ref", joinColumns = @JoinColumn(name = "product_offering_id"))
  private List<@Valid MarketSegmentRef> marketSegment = new ArrayList<>();

  @Valid
  @ElementCollection
  @CollectionTable(name = "po_place_ref", joinColumns = @JoinColumn(name = "product_offering_id"))
  private List<@Valid PlaceRef> place = new ArrayList<>();

  @Valid
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "product_offering_id")
  private List<@Valid ProductSpecificationCharacteristicValueUse> prodSpecCharValueUse = new ArrayList<>();

  @Valid
  @ElementCollection
  @CollectionTable(name = "po_price_ref", joinColumns = @JoinColumn(name = "product_offering_id"))
  private List<@Valid ProductOfferingPriceRef> productOfferingPrice = new ArrayList<>();

  @Valid
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "product_offering_id")
  private List<@Valid ProductOfferingTerm> productOfferingTerm = new ArrayList<>();

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "id",                                    column = @Column(name = "prod_spec_ref_id")),
      @AttributeOverride(name = "href",                                  column = @Column(name = "prod_spec_ref_href")),
      @AttributeOverride(name = "name",                                  column = @Column(name = "prod_spec_ref_name")),
      @AttributeOverride(name = "version",                               column = @Column(name = "prod_spec_ref_version")),
      @AttributeOverride(name = "atBaseType",                            column = @Column(name = "prod_spec_ref_base_type")),
      @AttributeOverride(name = "atType",                                column = @Column(name = "prod_spec_ref_type")),
      @AttributeOverride(name = "atReferredType",                        column = @Column(name = "prod_spec_ref_referred_type")),
      @AttributeOverride(name = "targetProductSchema.atBaseType",        column = @Column(name = "prod_spec_schema_base_type")),
      @AttributeOverride(name = "targetProductSchema.atSchemaLocation",  column = @Column(name = "prod_spec_schema_location")),
      @AttributeOverride(name = "targetProductSchema.atType",            column = @Column(name = "prod_spec_schema_type"))
  })
  private @Nullable ProductSpecificationRef productSpecification;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "id",             column = @Column(name = "res_cand_ref_id")),
      @AttributeOverride(name = "href",           column = @Column(name = "res_cand_ref_href")),
      @AttributeOverride(name = "name",           column = @Column(name = "res_cand_ref_name")),
      @AttributeOverride(name = "version",        column = @Column(name = "res_cand_ref_version")),
      @AttributeOverride(name = "atBaseType",     column = @Column(name = "res_cand_ref_base_type")),
      @AttributeOverride(name = "atType",         column = @Column(name = "res_cand_ref_type")),
      @AttributeOverride(name = "atReferredType", column = @Column(name = "res_cand_ref_referred_type"))
  })
  private @Nullable ResourceCandidateRef resourceCandidate;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "id",             column = @Column(name = "svc_cand_ref_id")),
      @AttributeOverride(name = "href",           column = @Column(name = "svc_cand_ref_href")),
      @AttributeOverride(name = "name",           column = @Column(name = "svc_cand_ref_name")),
      @AttributeOverride(name = "version",        column = @Column(name = "svc_cand_ref_version")),
      @AttributeOverride(name = "atBaseType",     column = @Column(name = "svc_cand_ref_base_type")),
      @AttributeOverride(name = "atType",         column = @Column(name = "svc_cand_ref_type")),
      @AttributeOverride(name = "atReferredType", column = @Column(name = "svc_cand_ref_referred_type"))
  })
  private @Nullable ServiceCandidateRef serviceCandidate;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "id",             column = @Column(name = "sla_ref_id")),
      @AttributeOverride(name = "href",           column = @Column(name = "sla_ref_href")),
      @AttributeOverride(name = "name",           column = @Column(name = "sla_ref_name")),
      @AttributeOverride(name = "atBaseType",     column = @Column(name = "sla_ref_base_type")),
      @AttributeOverride(name = "atType",         column = @Column(name = "sla_ref_type")),
      @AttributeOverride(name = "atReferredType", column = @Column(name = "sla_ref_referred_type"))
  })
  private @Nullable SLARef serviceLevelAgreement;

  @Embedded
  private @Nullable TimePeriod validFor;

  private @Nullable String atBaseType;

  @Transient
  private @Nullable URI atSchemaLocation;

  private @Nullable String atType;

  public ProductOffering id(@Nullable String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier of the productOffering
   * @return id
   */
  
  @Schema(name = "id", description = "Unique identifier of the productOffering", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public @Nullable String getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(@Nullable String id) {
    this.id = id;
  }

  public ProductOffering href(@Nullable String href) {
    this.href = href;
    return this;
  }

  /**
   * Reference of the ProductOffering
   * @return href
   */
  
  @Schema(name = "href", description = "Reference of the ProductOffering", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("href")
  public @Nullable String getHref() {
    return href;
  }

  @JsonProperty("href")
  public void setHref(@Nullable String href) {
    this.href = href;
  }

  public ProductOffering description(@Nullable String description) {
    this.description = description;
    return this;
  }

  /**
   * Description of the productOffering
   * @return description
   */
  
  @Schema(name = "description", description = "Description of the productOffering", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public @Nullable String getDescription() {
    return description;
  }

  @JsonProperty("description")
  public void setDescription(@Nullable String description) {
    this.description = description;
  }

  public ProductOffering isBundle(@Nullable Boolean isBundle) {
    this.isBundle = isBundle;
    return this;
  }

  /**
   * isBundle determines whether a productOffering represents a single productOffering (false), or a bundle of productOfferings (true).
   * @return isBundle
   */
  
  @Schema(name = "isBundle", description = "isBundle determines whether a productOffering represents a single productOffering (false), or a bundle of productOfferings (true).", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isBundle")
  public @Nullable Boolean getIsBundle() {
    return isBundle;
  }

  @JsonProperty("isBundle")
  public void setIsBundle(@Nullable Boolean isBundle) {
    this.isBundle = isBundle;
  }

  public ProductOffering isSellable(@Nullable Boolean isSellable) {
    this.isSellable = isSellable;
    return this;
  }

  /**
   * A flag indicating if this product offer can be sold stand-alone for sale or not. If this flag is false it indicates that the offer can only be sold within a bundle.
   * @return isSellable
   */
  
  @Schema(name = "isSellable", description = "A flag indicating if this product offer can be sold stand-alone for sale or not. If this flag is false it indicates that the offer can only be sold within a bundle.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isSellable")
  public @Nullable Boolean getIsSellable() {
    return isSellable;
  }

  @JsonProperty("isSellable")
  public void setIsSellable(@Nullable Boolean isSellable) {
    this.isSellable = isSellable;
  }

  public ProductOffering lastUpdate(@Nullable OffsetDateTime lastUpdate) {
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

  public ProductOffering lifecycleStatus(@Nullable String lifecycleStatus) {
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

  public ProductOffering name(@Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the productOffering
   * @return name
   */
  
  @Schema(name = "name", description = "Name of the productOffering", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public @Nullable String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(@Nullable String name) {
    this.name = name;
  }

  public ProductOffering statusReason(@Nullable String statusReason) {
    this.statusReason = statusReason;
    return this;
  }

  /**
   * A string providing a complementary information on the value of the lifecycle status attribute.
   * @return statusReason
   */
  
  @Schema(name = "statusReason", description = "A string providing a complementary information on the value of the lifecycle status attribute.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("statusReason")
  public @Nullable String getStatusReason() {
    return statusReason;
  }

  @JsonProperty("statusReason")
  public void setStatusReason(@Nullable String statusReason) {
    this.statusReason = statusReason;
  }

  public ProductOffering version(@Nullable String version) {
    this.version = version;
    return this;
  }

  /**
   * ProductOffering version
   * @return version
   */
  
  @Schema(name = "version", description = "ProductOffering version", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("version")
  public @Nullable String getVersion() {
    return version;
  }

  @JsonProperty("version")
  public void setVersion(@Nullable String version) {
    this.version = version;
  }

  public ProductOffering agreement(List<@Valid AgreementRef> agreement) {
    this.agreement = agreement;
    return this;
  }

  public ProductOffering addAgreementItem(AgreementRef agreementItem) {
    if (this.agreement == null) {
      this.agreement = new ArrayList<>();
    }
    this.agreement.add(agreementItem);
    return this;
  }

  /**
   * An agreement represents a contract or arrangement, either written or verbal and sometimes enforceable by law, such as a service level agreement or a customer price agreement. An agreement involves a number of other business entities, such as products, services, and resources and/or their specifications.
   * @return agreement
   */
  @Valid 
  @Schema(name = "agreement", description = "An agreement represents a contract or arrangement, either written or verbal and sometimes enforceable by law, such as a service level agreement or a customer price agreement. An agreement involves a number of other business entities, such as products, services, and resources and/or their specifications.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("agreement")
  public List<@Valid AgreementRef> getAgreement() {
    return agreement;
  }

  @JsonProperty("agreement")
  public void setAgreement(List<@Valid AgreementRef> agreement) {
    this.agreement = agreement;
  }

  public ProductOffering attachment(List<@Valid AttachmentRefOrValue> attachment) {
    this.attachment = attachment;
    return this;
  }

  public ProductOffering addAttachmentItem(AttachmentRefOrValue attachmentItem) {
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

  public ProductOffering bundledProductOffering(List<@Valid BundledProductOffering> bundledProductOffering) {
    this.bundledProductOffering = bundledProductOffering;
    return this;
  }

  public ProductOffering addBundledProductOfferingItem(BundledProductOffering bundledProductOfferingItem) {
    if (this.bundledProductOffering == null) {
      this.bundledProductOffering = new ArrayList<>();
    }
    this.bundledProductOffering.add(bundledProductOfferingItem);
    return this;
  }

  /**
   * A type of ProductOffering that belongs to a grouping of ProductOfferings made available to the market. It inherits of all attributes of ProductOffering.
   * @return bundledProductOffering
   */
  @Valid 
  @Schema(name = "bundledProductOffering", description = "A type of ProductOffering that belongs to a grouping of ProductOfferings made available to the market. It inherits of all attributes of ProductOffering.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bundledProductOffering")
  public List<@Valid BundledProductOffering> getBundledProductOffering() {
    return bundledProductOffering;
  }

  @JsonProperty("bundledProductOffering")
  public void setBundledProductOffering(List<@Valid BundledProductOffering> bundledProductOffering) {
    this.bundledProductOffering = bundledProductOffering;
  }

  public ProductOffering category(List<@Valid CategoryRef> category) {
    this.category = category;
    return this;
  }

  public ProductOffering addCategoryItem(CategoryRef categoryItem) {
    if (this.category == null) {
      this.category = new ArrayList<>();
    }
    this.category.add(categoryItem);
    return this;
  }

  /**
   * The category resource is used to group product offerings, service and resource candidates in logical containers. Categories can contain other categories and/or product offerings, resource or service candidates.
   * @return category
   */
  @Valid 
  @Schema(name = "category", description = "The category resource is used to group product offerings, service and resource candidates in logical containers. Categories can contain other categories and/or product offerings, resource or service candidates.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("category")
  public List<@Valid CategoryRef> getCategory() {
    return category;
  }

  @JsonProperty("category")
  public void setCategory(List<@Valid CategoryRef> category) {
    this.category = category;
  }

  public ProductOffering channel(List<@Valid ChannelRef> channel) {
    this.channel = channel;
    return this;
  }

  public ProductOffering addChannelItem(ChannelRef channelItem) {
    if (this.channel == null) {
      this.channel = new ArrayList<>();
    }
    this.channel.add(channelItem);
    return this;
  }

  /**
   * The channel defines the channel for selling product offerings.
   * @return channel
   */
  @Valid 
  @Schema(name = "channel", description = "The channel defines the channel for selling product offerings.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("channel")
  public List<@Valid ChannelRef> getChannel() {
    return channel;
  }

  @JsonProperty("channel")
  public void setChannel(List<@Valid ChannelRef> channel) {
    this.channel = channel;
  }

  public ProductOffering marketSegment(List<@Valid MarketSegmentRef> marketSegment) {
    this.marketSegment = marketSegment;
    return this;
  }

  public ProductOffering addMarketSegmentItem(MarketSegmentRef marketSegmentItem) {
    if (this.marketSegment == null) {
      this.marketSegment = new ArrayList<>();
    }
    this.marketSegment.add(marketSegmentItem);
    return this;
  }

  /**
   * provides references to the corresponding market segment as target of product offerings. A market segment is grouping of Parties, GeographicAreas, SalesChannels, and so forth.
   * @return marketSegment
   */
  @Valid 
  @Schema(name = "marketSegment", description = "provides references to the corresponding market segment as target of product offerings. A market segment is grouping of Parties, GeographicAreas, SalesChannels, and so forth.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("marketSegment")
  public List<@Valid MarketSegmentRef> getMarketSegment() {
    return marketSegment;
  }

  @JsonProperty("marketSegment")
  public void setMarketSegment(List<@Valid MarketSegmentRef> marketSegment) {
    this.marketSegment = marketSegment;
  }

  public ProductOffering place(List<@Valid PlaceRef> place) {
    this.place = place;
    return this;
  }

  public ProductOffering addPlaceItem(PlaceRef placeItem) {
    if (this.place == null) {
      this.place = new ArrayList<>();
    }
    this.place.add(placeItem);
    return this;
  }

  /**
   * Place defines the places where the products are sold or delivered.
   * @return place
   */
  @Valid 
  @Schema(name = "place", description = "Place defines the places where the products are sold or delivered.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("place")
  public List<@Valid PlaceRef> getPlace() {
    return place;
  }

  @JsonProperty("place")
  public void setPlace(List<@Valid PlaceRef> place) {
    this.place = place;
  }

  public ProductOffering prodSpecCharValueUse(List<@Valid ProductSpecificationCharacteristicValueUse> prodSpecCharValueUse) {
    this.prodSpecCharValueUse = prodSpecCharValueUse;
    return this;
  }

  public ProductOffering addProdSpecCharValueUseItem(ProductSpecificationCharacteristicValueUse prodSpecCharValueUseItem) {
    if (this.prodSpecCharValueUse == null) {
      this.prodSpecCharValueUse = new ArrayList<>();
    }
    this.prodSpecCharValueUse.add(prodSpecCharValueUseItem);
    return this;
  }

  /**
   * A use of the ProductSpecificationCharacteristicValue by a ProductOffering to which additional properties (attributes) apply or override the properties of similar properties contained in ProductSpecificationCharacteristicValue. It should be noted that characteristics which their value(s) addressed by this object must exist in corresponding product specification. The available characteristic values for a ProductSpecificationCharacteristic in a Product specification can be modified at the ProductOffering level. For example, a characteristic 'Color' might have values White, Blue, Green, and Red. But, the list of values can be restricted to e.g. White and Blue in an associated product offering. It should be noted that the list of values in 'ProductSpecificationCharacteristicValueUse' is a strict subset of the list of values as defined in the corresponding product specification characteristics.
   * @return prodSpecCharValueUse
   */
  @Valid 
  @Schema(name = "prodSpecCharValueUse", description = "A use of the ProductSpecificationCharacteristicValue by a ProductOffering to which additional properties (attributes) apply or override the properties of similar properties contained in ProductSpecificationCharacteristicValue. It should be noted that characteristics which their value(s) addressed by this object must exist in corresponding product specification. The available characteristic values for a ProductSpecificationCharacteristic in a Product specification can be modified at the ProductOffering level. For example, a characteristic 'Color' might have values White, Blue, Green, and Red. But, the list of values can be restricted to e.g. White and Blue in an associated product offering. It should be noted that the list of values in 'ProductSpecificationCharacteristicValueUse' is a strict subset of the list of values as defined in the corresponding product specification characteristics.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prodSpecCharValueUse")
  public List<@Valid ProductSpecificationCharacteristicValueUse> getProdSpecCharValueUse() {
    return prodSpecCharValueUse;
  }

  @JsonProperty("prodSpecCharValueUse")
  public void setProdSpecCharValueUse(List<@Valid ProductSpecificationCharacteristicValueUse> prodSpecCharValueUse) {
    this.prodSpecCharValueUse = prodSpecCharValueUse;
  }

  public ProductOffering productOfferingPrice(List<@Valid ProductOfferingPriceRef> productOfferingPrice) {
    this.productOfferingPrice = productOfferingPrice;
    return this;
  }

  public ProductOffering addProductOfferingPriceItem(ProductOfferingPriceRef productOfferingPriceItem) {
    if (this.productOfferingPrice == null) {
      this.productOfferingPrice = new ArrayList<>();
    }
    this.productOfferingPrice.add(productOfferingPriceItem);
    return this;
  }

  /**
   * An amount, usually of money, that is asked for or allowed when a ProductOffering is bought, rented, or leased. The price is valid for a defined period of time and may not represent the actual price paid by a customer.
   * @return productOfferingPrice
   */
  @Valid 
  @Schema(name = "productOfferingPrice", description = "An amount, usually of money, that is asked for or allowed when a ProductOffering is bought, rented, or leased. The price is valid for a defined period of time and may not represent the actual price paid by a customer.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("productOfferingPrice")
  public List<@Valid ProductOfferingPriceRef> getProductOfferingPrice() {
    return productOfferingPrice;
  }

  @JsonProperty("productOfferingPrice")
  public void setProductOfferingPrice(List<@Valid ProductOfferingPriceRef> productOfferingPrice) {
    this.productOfferingPrice = productOfferingPrice;
  }

  public ProductOffering productOfferingTerm(List<@Valid ProductOfferingTerm> productOfferingTerm) {
    this.productOfferingTerm = productOfferingTerm;
    return this;
  }

  public ProductOffering addProductOfferingTermItem(ProductOfferingTerm productOfferingTermItem) {
    if (this.productOfferingTerm == null) {
      this.productOfferingTerm = new ArrayList<>();
    }
    this.productOfferingTerm.add(productOfferingTermItem);
    return this;
  }

  /**
   * A condition under which a ProductOffering is made available to Customers. For instance, a productOffering can be offered with multiple commitment periods.
   * @return productOfferingTerm
   */
  @Valid 
  @Schema(name = "productOfferingTerm", description = "A condition under which a ProductOffering is made available to Customers. For instance, a productOffering can be offered with multiple commitment periods.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("productOfferingTerm")
  public List<@Valid ProductOfferingTerm> getProductOfferingTerm() {
    return productOfferingTerm;
  }

  @JsonProperty("productOfferingTerm")
  public void setProductOfferingTerm(List<@Valid ProductOfferingTerm> productOfferingTerm) {
    this.productOfferingTerm = productOfferingTerm;
  }

  public ProductOffering productSpecification(@Nullable ProductSpecificationRef productSpecification) {
    this.productSpecification = productSpecification;
    return this;
  }

  /**
   * Get productSpecification
   * @return productSpecification
   */
  @Valid 
  @Schema(name = "productSpecification", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("productSpecification")
  public @Nullable ProductSpecificationRef getProductSpecification() {
    return productSpecification;
  }

  @JsonProperty("productSpecification")
  public void setProductSpecification(@Nullable ProductSpecificationRef productSpecification) {
    this.productSpecification = productSpecification;
  }

  public ProductOffering resourceCandidate(@Nullable ResourceCandidateRef resourceCandidate) {
    this.resourceCandidate = resourceCandidate;
    return this;
  }

  /**
   * Get resourceCandidate
   * @return resourceCandidate
   */
  @Valid 
  @Schema(name = "resourceCandidate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("resourceCandidate")
  public @Nullable ResourceCandidateRef getResourceCandidate() {
    return resourceCandidate;
  }

  @JsonProperty("resourceCandidate")
  public void setResourceCandidate(@Nullable ResourceCandidateRef resourceCandidate) {
    this.resourceCandidate = resourceCandidate;
  }

  public ProductOffering serviceCandidate(@Nullable ServiceCandidateRef serviceCandidate) {
    this.serviceCandidate = serviceCandidate;
    return this;
  }

  /**
   * Get serviceCandidate
   * @return serviceCandidate
   */
  @Valid 
  @Schema(name = "serviceCandidate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("serviceCandidate")
  public @Nullable ServiceCandidateRef getServiceCandidate() {
    return serviceCandidate;
  }

  @JsonProperty("serviceCandidate")
  public void setServiceCandidate(@Nullable ServiceCandidateRef serviceCandidate) {
    this.serviceCandidate = serviceCandidate;
  }

  public ProductOffering serviceLevelAgreement(@Nullable SLARef serviceLevelAgreement) {
    this.serviceLevelAgreement = serviceLevelAgreement;
    return this;
  }

  /**
   * Get serviceLevelAgreement
   * @return serviceLevelAgreement
   */
  @Valid 
  @Schema(name = "serviceLevelAgreement", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("serviceLevelAgreement")
  public @Nullable SLARef getServiceLevelAgreement() {
    return serviceLevelAgreement;
  }

  @JsonProperty("serviceLevelAgreement")
  public void setServiceLevelAgreement(@Nullable SLARef serviceLevelAgreement) {
    this.serviceLevelAgreement = serviceLevelAgreement;
  }

  public ProductOffering validFor(@Nullable TimePeriod validFor) {
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

  public ProductOffering atBaseType(@Nullable String atBaseType) {
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

  public ProductOffering atSchemaLocation(@Nullable URI atSchemaLocation) {
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

  public ProductOffering atType(@Nullable String atType) {
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
    ProductOffering productOffering = (ProductOffering) o;
    return Objects.equals(this.id, productOffering.id) &&
        Objects.equals(this.href, productOffering.href) &&
        Objects.equals(this.description, productOffering.description) &&
        Objects.equals(this.isBundle, productOffering.isBundle) &&
        Objects.equals(this.isSellable, productOffering.isSellable) &&
        Objects.equals(this.lastUpdate, productOffering.lastUpdate) &&
        Objects.equals(this.lifecycleStatus, productOffering.lifecycleStatus) &&
        Objects.equals(this.name, productOffering.name) &&
        Objects.equals(this.statusReason, productOffering.statusReason) &&
        Objects.equals(this.version, productOffering.version) &&
        Objects.equals(this.agreement, productOffering.agreement) &&
        Objects.equals(this.attachment, productOffering.attachment) &&
        Objects.equals(this.bundledProductOffering, productOffering.bundledProductOffering) &&
        Objects.equals(this.category, productOffering.category) &&
        Objects.equals(this.channel, productOffering.channel) &&
        Objects.equals(this.marketSegment, productOffering.marketSegment) &&
        Objects.equals(this.place, productOffering.place) &&
        Objects.equals(this.prodSpecCharValueUse, productOffering.prodSpecCharValueUse) &&
        Objects.equals(this.productOfferingPrice, productOffering.productOfferingPrice) &&
        Objects.equals(this.productOfferingTerm, productOffering.productOfferingTerm) &&
        Objects.equals(this.productSpecification, productOffering.productSpecification) &&
        Objects.equals(this.resourceCandidate, productOffering.resourceCandidate) &&
        Objects.equals(this.serviceCandidate, productOffering.serviceCandidate) &&
        Objects.equals(this.serviceLevelAgreement, productOffering.serviceLevelAgreement) &&
        Objects.equals(this.validFor, productOffering.validFor) &&
        Objects.equals(this.atBaseType, productOffering.atBaseType) &&
        Objects.equals(this.atSchemaLocation, productOffering.atSchemaLocation) &&
        Objects.equals(this.atType, productOffering.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, href, description, isBundle, isSellable, lastUpdate, lifecycleStatus, name, statusReason, version, agreement, attachment, bundledProductOffering, category, channel, marketSegment, place, prodSpecCharValueUse, productOfferingPrice, productOfferingTerm, productSpecification, resourceCandidate, serviceCandidate, serviceLevelAgreement, validFor, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductOffering {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    isBundle: ").append(toIndentedString(isBundle)).append("\n");
    sb.append("    isSellable: ").append(toIndentedString(isSellable)).append("\n");
    sb.append("    lastUpdate: ").append(toIndentedString(lastUpdate)).append("\n");
    sb.append("    lifecycleStatus: ").append(toIndentedString(lifecycleStatus)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    statusReason: ").append(toIndentedString(statusReason)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    agreement: ").append(toIndentedString(agreement)).append("\n");
    sb.append("    attachment: ").append(toIndentedString(attachment)).append("\n");
    sb.append("    bundledProductOffering: ").append(toIndentedString(bundledProductOffering)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    channel: ").append(toIndentedString(channel)).append("\n");
    sb.append("    marketSegment: ").append(toIndentedString(marketSegment)).append("\n");
    sb.append("    place: ").append(toIndentedString(place)).append("\n");
    sb.append("    prodSpecCharValueUse: ").append(toIndentedString(prodSpecCharValueUse)).append("\n");
    sb.append("    productOfferingPrice: ").append(toIndentedString(productOfferingPrice)).append("\n");
    sb.append("    productOfferingTerm: ").append(toIndentedString(productOfferingTerm)).append("\n");
    sb.append("    productSpecification: ").append(toIndentedString(productSpecification)).append("\n");
    sb.append("    resourceCandidate: ").append(toIndentedString(resourceCandidate)).append("\n");
    sb.append("    serviceCandidate: ").append(toIndentedString(serviceCandidate)).append("\n");
    sb.append("    serviceLevelAgreement: ").append(toIndentedString(serviceLevelAgreement)).append("\n");
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

