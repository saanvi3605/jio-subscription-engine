package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.model.BundledProductOfferingPriceRelationship;
import org.openapitools.model.ConstraintRef;
import org.openapitools.model.Money;
import org.openapitools.model.PlaceRef;
import org.openapitools.model.PricingLogicAlgorithm;
import org.openapitools.model.ProductOfferingPriceRelationship;
import org.openapitools.model.ProductOfferingTerm;
import org.openapitools.model.ProductSpecificationCharacteristicValueUse;
import org.openapitools.model.Quantity;
import org.openapitools.model.TaxItem;
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
import org.hibernate.annotations.UuidGenerator;

/**
 * Is based on both the basic cost to develop and produce products and the enterprises policy on revenue targets. This price may be further revised through discounting (productOfferPriceAlteration). The price, applied for a productOffering may also be influenced by the productOfferingTerm, the customer selected, eg: a productOffering can be offered with multiple terms, like commitment periods for the contract. The price may be influenced by this productOfferingTerm. A productOffering may be cheaper with a 24 month commitment than with a 12 month commitment.
 */

@Entity
@Table(name = "product_offering_price")
@Schema(name = "ProductOfferingPrice", description = "Is based on both the basic cost to develop and produce products and the enterprises policy on revenue targets. This price may be further revised through discounting (productOfferPriceAlteration). The price, applied for a productOffering may also be influenced by the productOfferingTerm, the customer selected, eg: a productOffering can be offered with multiple terms, like commitment periods for the contract. The price may be influenced by this productOfferingTerm. A productOffering may be cheaper with a 24 month commitment than with a 12 month commitment.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T16:22:46.010747900+05:30[Asia/Calcutta]", comments = "Generator version: 7.22.0")
public class ProductOfferingPrice {

  @Id
  @UuidGenerator
  private @Nullable String id;

  private @Nullable String href;

  private @Nullable String description;

  private @Nullable Boolean isBundle;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime lastUpdate;

  private @Nullable String lifecycleStatus;

  private @Nullable String name;

  private @Nullable Float percentage;

  private @Nullable String priceType;

  private @Nullable Integer recurringChargePeriodLength;

  private @Nullable String recurringChargePeriodType;

  private @Nullable String version;

  @Valid
  @ElementCollection
  @CollectionTable(name = "pop_bundled_price_rel", joinColumns = @JoinColumn(name = "product_offering_price_id"))
  private List<@Valid BundledProductOfferingPriceRelationship> bundledPopRelationship = new ArrayList<>();

  @Valid
  @ElementCollection
  @CollectionTable(name = "pop_constraint_ref", joinColumns = @JoinColumn(name = "product_offering_price_id"))
  private List<@Valid ConstraintRef> constraint = new ArrayList<>();

  @Valid
  @ElementCollection
  @CollectionTable(name = "pop_place_ref", joinColumns = @JoinColumn(name = "product_offering_price_id"))
  private List<@Valid PlaceRef> place = new ArrayList<>();

  @Valid
  @ElementCollection
  @CollectionTable(name = "pop_price_relationship", joinColumns = @JoinColumn(name = "product_offering_price_id"))
  private List<@Valid ProductOfferingPriceRelationship> popRelationship = new ArrayList<>();

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "unit",  column = @Column(name = "price_unit")),
      @AttributeOverride(name = "value", column = @Column(name = "price_value"))
  })
  private @Nullable Money price;

  @Valid
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "product_offering_price_id")
  private List<@Valid PricingLogicAlgorithm> pricingLogicAlgorithm = new ArrayList<>();

  @Valid
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "product_offering_price_id")
  private List<@Valid ProductSpecificationCharacteristicValueUse> prodSpecCharValueUse = new ArrayList<>();

  @Valid
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "product_offering_price_id")
  private List<@Valid ProductOfferingTerm> productOfferingTerm = new ArrayList<>();

  @Valid
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "product_offering_price_id")
  private List<@Valid TaxItem> tax = new ArrayList<>();

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "amount", column = @Column(name = "unit_of_measure_amount")),
      @AttributeOverride(name = "units",  column = @Column(name = "unit_of_measure_units"))
  })
  private @Nullable Quantity unitOfMeasure;

  @Embedded
  private @Nullable TimePeriod validFor;

  private @Nullable String atBaseType;

  private @Nullable String atSchemaLocation;

  private @Nullable String atType;

  public ProductOfferingPrice id(@Nullable String id) {
    this.id = id;
    return this;
  }

  /**
   * unique id of this resource
   * @return id
   */
  
  @Schema(name = "id", description = "unique id of this resource", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public @Nullable String getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(@Nullable String id) {
    this.id = id;
  }

  public ProductOfferingPrice href(@Nullable String href) {
    this.href = href;
    return this;
  }

  /**
   * Reference of the ProductOfferingPrice
   * @return href
   */
  
  @Schema(name = "href", description = "Reference of the ProductOfferingPrice", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("href")
  public @Nullable String getHref() {
    return href;
  }

  @JsonProperty("href")
  public void setHref(@Nullable String href) {
    this.href = href;
  }

  public ProductOfferingPrice description(@Nullable String description) {
    this.description = description;
    return this;
  }

  /**
   * Description of the productOfferingPrice
   * @return description
   */
  
  @Schema(name = "description", description = "Description of the productOfferingPrice", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public @Nullable String getDescription() {
    return description;
  }

  @JsonProperty("description")
  public void setDescription(@Nullable String description) {
    this.description = description;
  }

  public ProductOfferingPrice isBundle(@Nullable Boolean isBundle) {
    this.isBundle = isBundle;
    return this;
  }

  /**
   * A flag indicating if this ProductOfferingPrice is composite (bundle) or not
   * @return isBundle
   */
  
  @Schema(name = "isBundle", description = "A flag indicating if this ProductOfferingPrice is composite (bundle) or not", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isBundle")
  public @Nullable Boolean getIsBundle() {
    return isBundle;
  }

  @JsonProperty("isBundle")
  public void setIsBundle(@Nullable Boolean isBundle) {
    this.isBundle = isBundle;
  }

  public ProductOfferingPrice lastUpdate(@Nullable OffsetDateTime lastUpdate) {
    this.lastUpdate = lastUpdate;
    return this;
  }

  /**
   * the last update time of this ProductOfferingPrice
   * @return lastUpdate
   */
  @Valid 
  @Schema(name = "lastUpdate", description = "the last update time of this ProductOfferingPrice", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lastUpdate")
  public @Nullable OffsetDateTime getLastUpdate() {
    return lastUpdate;
  }

  @JsonProperty("lastUpdate")
  public void setLastUpdate(@Nullable OffsetDateTime lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  public ProductOfferingPrice lifecycleStatus(@Nullable String lifecycleStatus) {
    this.lifecycleStatus = lifecycleStatus;
    return this;
  }

  /**
   * the lifecycle status of this ProductOfferingPrice
   * @return lifecycleStatus
   */
  
  @Schema(name = "lifecycleStatus", description = "the lifecycle status of this ProductOfferingPrice", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lifecycleStatus")
  public @Nullable String getLifecycleStatus() {
    return lifecycleStatus;
  }

  @JsonProperty("lifecycleStatus")
  public void setLifecycleStatus(@Nullable String lifecycleStatus) {
    this.lifecycleStatus = lifecycleStatus;
  }

  public ProductOfferingPrice name(@Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the productOfferingPrice
   * @return name
   */
  
  @Schema(name = "name", description = "Name of the productOfferingPrice", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public @Nullable String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(@Nullable String name) {
    this.name = name;
  }

  public ProductOfferingPrice percentage(@Nullable Float percentage) {
    this.percentage = percentage;
    return this;
  }

  /**
   * Percentage to apply for ProductOfferPriceAlteration (Discount)
   * @return percentage
   */
  
  @Schema(name = "percentage", description = "Percentage to apply for ProductOfferPriceAlteration (Discount)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("percentage")
  public @Nullable Float getPercentage() {
    return percentage;
  }

  @JsonProperty("percentage")
  public void setPercentage(@Nullable Float percentage) {
    this.percentage = percentage;
  }

  public ProductOfferingPrice priceType(@Nullable String priceType) {
    this.priceType = priceType;
    return this;
  }

  /**
   * A category that describes the price, such as recurring, discount, allowance, penalty, and so forth.
   * @return priceType
   */
  
  @Schema(name = "priceType", description = "A category that describes the price, such as recurring, discount, allowance, penalty, and so forth.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("priceType")
  public @Nullable String getPriceType() {
    return priceType;
  }

  @JsonProperty("priceType")
  public void setPriceType(@Nullable String priceType) {
    this.priceType = priceType;
  }

  public ProductOfferingPrice recurringChargePeriodLength(@Nullable Integer recurringChargePeriodLength) {
    this.recurringChargePeriodLength = recurringChargePeriodLength;
    return this;
  }

  /**
   * the period of the recurring charge:  1, 2, ... .It sets to zero if it is not applicable
   * @return recurringChargePeriodLength
   */
  
  @Schema(name = "recurringChargePeriodLength", description = "the period of the recurring charge:  1, 2, ... .It sets to zero if it is not applicable", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("recurringChargePeriodLength")
  public @Nullable Integer getRecurringChargePeriodLength() {
    return recurringChargePeriodLength;
  }

  @JsonProperty("recurringChargePeriodLength")
  public void setRecurringChargePeriodLength(@Nullable Integer recurringChargePeriodLength) {
    this.recurringChargePeriodLength = recurringChargePeriodLength;
  }

  public ProductOfferingPrice recurringChargePeriodType(@Nullable String recurringChargePeriodType) {
    this.recurringChargePeriodType = recurringChargePeriodType;
    return this;
  }

  /**
   * The period to repeat the application of the price Could be month, week...
   * @return recurringChargePeriodType
   */
  
  @Schema(name = "recurringChargePeriodType", description = "The period to repeat the application of the price Could be month, week...", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("recurringChargePeriodType")
  public @Nullable String getRecurringChargePeriodType() {
    return recurringChargePeriodType;
  }

  @JsonProperty("recurringChargePeriodType")
  public void setRecurringChargePeriodType(@Nullable String recurringChargePeriodType) {
    this.recurringChargePeriodType = recurringChargePeriodType;
  }

  public ProductOfferingPrice version(@Nullable String version) {
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

  public ProductOfferingPrice bundledPopRelationship(List<@Valid BundledProductOfferingPriceRelationship> bundledPopRelationship) {
    this.bundledPopRelationship = bundledPopRelationship;
    return this;
  }

  public ProductOfferingPrice addBundledPopRelationshipItem(BundledProductOfferingPriceRelationship bundledPopRelationshipItem) {
    if (this.bundledPopRelationship == null) {
      this.bundledPopRelationship = new ArrayList<>();
    }
    this.bundledPopRelationship.add(bundledPopRelationshipItem);
    return this;
  }

  /**
   * this object represents a bundle relationship from a bundle product offering price (parent) to a simple product offering price (child). A simple product offering price may participate in more than one bundle relationship.
   * @return bundledPopRelationship
   */
  @Valid 
  @Schema(name = "bundledPopRelationship", description = "this object represents a bundle relationship from a bundle product offering price (parent) to a simple product offering price (child). A simple product offering price may participate in more than one bundle relationship.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bundledPopRelationship")
  public List<@Valid BundledProductOfferingPriceRelationship> getBundledPopRelationship() {
    return bundledPopRelationship;
  }

  @JsonProperty("bundledPopRelationship")
  public void setBundledPopRelationship(List<@Valid BundledProductOfferingPriceRelationship> bundledPopRelationship) {
    this.bundledPopRelationship = bundledPopRelationship;
  }

  public ProductOfferingPrice constraint(List<@Valid ConstraintRef> constraint) {
    this.constraint = constraint;
    return this;
  }

  public ProductOfferingPrice addConstraintItem(ConstraintRef constraintItem) {
    if (this.constraint == null) {
      this.constraint = new ArrayList<>();
    }
    this.constraint.add(constraintItem);
    return this;
  }

  /**
   * The Constraint resource represents a policy/rule applied to ProductOfferingPrice.
   * @return constraint
   */
  @Valid 
  @Schema(name = "constraint", description = "The Constraint resource represents a policy/rule applied to ProductOfferingPrice.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("constraint")
  public List<@Valid ConstraintRef> getConstraint() {
    return constraint;
  }

  @JsonProperty("constraint")
  public void setConstraint(List<@Valid ConstraintRef> constraint) {
    this.constraint = constraint;
  }

  public ProductOfferingPrice place(List<@Valid PlaceRef> place) {
    this.place = place;
    return this;
  }

  public ProductOfferingPrice addPlaceItem(PlaceRef placeItem) {
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

  public ProductOfferingPrice popRelationship(List<@Valid ProductOfferingPriceRelationship> popRelationship) {
    this.popRelationship = popRelationship;
    return this;
  }

  public ProductOfferingPrice addPopRelationshipItem(ProductOfferingPriceRelationship popRelationshipItem) {
    if (this.popRelationship == null) {
      this.popRelationship = new ArrayList<>();
    }
    this.popRelationship.add(popRelationshipItem);
    return this;
  }

  /**
   * Product Offering Prices related to this Product Offering Price, for example a price alteration such as allowance or discount
   * @return popRelationship
   */
  @Valid 
  @Schema(name = "popRelationship", description = "Product Offering Prices related to this Product Offering Price, for example a price alteration such as allowance or discount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("popRelationship")
  public List<@Valid ProductOfferingPriceRelationship> getPopRelationship() {
    return popRelationship;
  }

  @JsonProperty("popRelationship")
  public void setPopRelationship(List<@Valid ProductOfferingPriceRelationship> popRelationship) {
    this.popRelationship = popRelationship;
  }

  public ProductOfferingPrice price(@Nullable Money price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
   */
  @Valid 
  @Schema(name = "price", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("price")
  public @Nullable Money getPrice() {
    return price;
  }

  @JsonProperty("price")
  public void setPrice(@Nullable Money price) {
    this.price = price;
  }

  public ProductOfferingPrice pricingLogicAlgorithm(List<@Valid PricingLogicAlgorithm> pricingLogicAlgorithm) {
    this.pricingLogicAlgorithm = pricingLogicAlgorithm;
    return this;
  }

  public ProductOfferingPrice addPricingLogicAlgorithmItem(PricingLogicAlgorithm pricingLogicAlgorithmItem) {
    if (this.pricingLogicAlgorithm == null) {
      this.pricingLogicAlgorithm = new ArrayList<>();
    }
    this.pricingLogicAlgorithm.add(pricingLogicAlgorithmItem);
    return this;
  }

  /**
   * The PricingLogicAlgorithm entity represents an instantiation of an interface specification to external rating function (without a modeled behavior in SID). Some of the parameters of the interface definition may be already set (such as price per unit) and some may be gathered during the rating process from the event (such as call duration) or from ProductCharacteristicValues (such as assigned bandwidth).
   * @return pricingLogicAlgorithm
   */
  @Valid 
  @Schema(name = "pricingLogicAlgorithm", description = "The PricingLogicAlgorithm entity represents an instantiation of an interface specification to external rating function (without a modeled behavior in SID). Some of the parameters of the interface definition may be already set (such as price per unit) and some may be gathered during the rating process from the event (such as call duration) or from ProductCharacteristicValues (such as assigned bandwidth).", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pricingLogicAlgorithm")
  public List<@Valid PricingLogicAlgorithm> getPricingLogicAlgorithm() {
    return pricingLogicAlgorithm;
  }

  @JsonProperty("pricingLogicAlgorithm")
  public void setPricingLogicAlgorithm(List<@Valid PricingLogicAlgorithm> pricingLogicAlgorithm) {
    this.pricingLogicAlgorithm = pricingLogicAlgorithm;
  }

  public ProductOfferingPrice prodSpecCharValueUse(List<@Valid ProductSpecificationCharacteristicValueUse> prodSpecCharValueUse) {
    this.prodSpecCharValueUse = prodSpecCharValueUse;
    return this;
  }

  public ProductOfferingPrice addProdSpecCharValueUseItem(ProductSpecificationCharacteristicValueUse prodSpecCharValueUseItem) {
    if (this.prodSpecCharValueUse == null) {
      this.prodSpecCharValueUse = new ArrayList<>();
    }
    this.prodSpecCharValueUse.add(prodSpecCharValueUseItem);
    return this;
  }

  /**
   * A use of the ProductSpecificationCharacteristicValue by a ProductOfferingPrice to which additional properties (attributes) apply or override the properties of similar properties contained in ProductSpecificationCharacteristicValue. It should be noted that characteristics which their value(s) addressed by this object must exist in corresponding product specification. The available characteristic values for a ProductSpecificationCharacteristic in a Product specification can be modified at the ProductOffering and ProcuctOfferingPrice level. The list of values in ProductSpecificationCharacteristicValueUse is a strict subset of the list of values as defined in the corresponding product specification characteristics.
   * @return prodSpecCharValueUse
   */
  @Valid 
  @Schema(name = "prodSpecCharValueUse", description = "A use of the ProductSpecificationCharacteristicValue by a ProductOfferingPrice to which additional properties (attributes) apply or override the properties of similar properties contained in ProductSpecificationCharacteristicValue. It should be noted that characteristics which their value(s) addressed by this object must exist in corresponding product specification. The available characteristic values for a ProductSpecificationCharacteristic in a Product specification can be modified at the ProductOffering and ProcuctOfferingPrice level. The list of values in ProductSpecificationCharacteristicValueUse is a strict subset of the list of values as defined in the corresponding product specification characteristics.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prodSpecCharValueUse")
  public List<@Valid ProductSpecificationCharacteristicValueUse> getProdSpecCharValueUse() {
    return prodSpecCharValueUse;
  }

  @JsonProperty("prodSpecCharValueUse")
  public void setProdSpecCharValueUse(List<@Valid ProductSpecificationCharacteristicValueUse> prodSpecCharValueUse) {
    this.prodSpecCharValueUse = prodSpecCharValueUse;
  }

  public ProductOfferingPrice productOfferingTerm(List<@Valid ProductOfferingTerm> productOfferingTerm) {
    this.productOfferingTerm = productOfferingTerm;
    return this;
  }

  public ProductOfferingPrice addProductOfferingTermItem(ProductOfferingTerm productOfferingTermItem) {
    if (this.productOfferingTerm == null) {
      this.productOfferingTerm = new ArrayList<>();
    }
    this.productOfferingTerm.add(productOfferingTermItem);
    return this;
  }

  /**
   * A list of conditions under which a ProductOfferingPrice is made available to Customers. For instance, a Product Offering Price can be offered with multiple commitment periods.
   * @return productOfferingTerm
   */
  @Valid 
  @Schema(name = "productOfferingTerm", description = "A list of conditions under which a ProductOfferingPrice is made available to Customers. For instance, a Product Offering Price can be offered with multiple commitment periods.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("productOfferingTerm")
  public List<@Valid ProductOfferingTerm> getProductOfferingTerm() {
    return productOfferingTerm;
  }

  @JsonProperty("productOfferingTerm")
  public void setProductOfferingTerm(List<@Valid ProductOfferingTerm> productOfferingTerm) {
    this.productOfferingTerm = productOfferingTerm;
  }

  public ProductOfferingPrice tax(List<@Valid TaxItem> tax) {
    this.tax = tax;
    return this;
  }

  public ProductOfferingPrice addTaxItem(TaxItem taxItem) {
    if (this.tax == null) {
      this.tax = new ArrayList<>();
    }
    this.tax.add(taxItem);
    return this;
  }

  /**
   * An amount of money levied on the price of a Product by a legislative body.
   * @return tax
   */
  @Valid 
  @Schema(name = "tax", description = "An amount of money levied on the price of a Product by a legislative body.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tax")
  public List<@Valid TaxItem> getTax() {
    return tax;
  }

  @JsonProperty("tax")
  public void setTax(List<@Valid TaxItem> tax) {
    this.tax = tax;
  }

  public ProductOfferingPrice unitOfMeasure(@Nullable Quantity unitOfMeasure) {
    this.unitOfMeasure = unitOfMeasure;
    return this;
  }

  /**
   * Get unitOfMeasure
   * @return unitOfMeasure
   */
  @Valid 
  @Schema(name = "unitOfMeasure", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("unitOfMeasure")
  public @Nullable Quantity getUnitOfMeasure() {
    return unitOfMeasure;
  }

  @JsonProperty("unitOfMeasure")
  public void setUnitOfMeasure(@Nullable Quantity unitOfMeasure) {
    this.unitOfMeasure = unitOfMeasure;
  }

  public ProductOfferingPrice validFor(@Nullable TimePeriod validFor) {
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

  public ProductOfferingPrice atBaseType(@Nullable String atBaseType) {
    this.atBaseType = atBaseType;
    return this;
  }

  /**
   * the immediate base class type of this product offering
   * @return atBaseType
   */
  
  @Schema(name = "@baseType", description = "the immediate base class type of this product offering", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("@baseType")
  public @Nullable String getAtBaseType() {
    return atBaseType;
  }

  @JsonProperty("@baseType")
  public void setAtBaseType(@Nullable String atBaseType) {
    this.atBaseType = atBaseType;
  }

  public ProductOfferingPrice atSchemaLocation(@Nullable String atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
    return this;
  }

  /**
   * hyperlink reference to the schema describing this resource
   * @return atSchemaLocation
   */
  
  @Schema(name = "@schemaLocation", description = "hyperlink reference to the schema describing this resource", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("@schemaLocation")
  public @Nullable String getAtSchemaLocation() {
    return atSchemaLocation;
  }

  @JsonProperty("@schemaLocation")
  public void setAtSchemaLocation(@Nullable String atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
  }

  public ProductOfferingPrice atType(@Nullable String atType) {
    this.atType = atType;
    return this;
  }

  /**
   * The class type of this Product offering
   * @return atType
   */
  
  @Schema(name = "@type", description = "The class type of this Product offering", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
    ProductOfferingPrice productOfferingPrice = (ProductOfferingPrice) o;
    return Objects.equals(this.id, productOfferingPrice.id) &&
        Objects.equals(this.href, productOfferingPrice.href) &&
        Objects.equals(this.description, productOfferingPrice.description) &&
        Objects.equals(this.isBundle, productOfferingPrice.isBundle) &&
        Objects.equals(this.lastUpdate, productOfferingPrice.lastUpdate) &&
        Objects.equals(this.lifecycleStatus, productOfferingPrice.lifecycleStatus) &&
        Objects.equals(this.name, productOfferingPrice.name) &&
        Objects.equals(this.percentage, productOfferingPrice.percentage) &&
        Objects.equals(this.priceType, productOfferingPrice.priceType) &&
        Objects.equals(this.recurringChargePeriodLength, productOfferingPrice.recurringChargePeriodLength) &&
        Objects.equals(this.recurringChargePeriodType, productOfferingPrice.recurringChargePeriodType) &&
        Objects.equals(this.version, productOfferingPrice.version) &&
        Objects.equals(this.bundledPopRelationship, productOfferingPrice.bundledPopRelationship) &&
        Objects.equals(this.constraint, productOfferingPrice.constraint) &&
        Objects.equals(this.place, productOfferingPrice.place) &&
        Objects.equals(this.popRelationship, productOfferingPrice.popRelationship) &&
        Objects.equals(this.price, productOfferingPrice.price) &&
        Objects.equals(this.pricingLogicAlgorithm, productOfferingPrice.pricingLogicAlgorithm) &&
        Objects.equals(this.prodSpecCharValueUse, productOfferingPrice.prodSpecCharValueUse) &&
        Objects.equals(this.productOfferingTerm, productOfferingPrice.productOfferingTerm) &&
        Objects.equals(this.tax, productOfferingPrice.tax) &&
        Objects.equals(this.unitOfMeasure, productOfferingPrice.unitOfMeasure) &&
        Objects.equals(this.validFor, productOfferingPrice.validFor) &&
        Objects.equals(this.atBaseType, productOfferingPrice.atBaseType) &&
        Objects.equals(this.atSchemaLocation, productOfferingPrice.atSchemaLocation) &&
        Objects.equals(this.atType, productOfferingPrice.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, href, description, isBundle, lastUpdate, lifecycleStatus, name, percentage, priceType, recurringChargePeriodLength, recurringChargePeriodType, version, bundledPopRelationship, constraint, place, popRelationship, price, pricingLogicAlgorithm, prodSpecCharValueUse, productOfferingTerm, tax, unitOfMeasure, validFor, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductOfferingPrice {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    isBundle: ").append(toIndentedString(isBundle)).append("\n");
    sb.append("    lastUpdate: ").append(toIndentedString(lastUpdate)).append("\n");
    sb.append("    lifecycleStatus: ").append(toIndentedString(lifecycleStatus)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    percentage: ").append(toIndentedString(percentage)).append("\n");
    sb.append("    priceType: ").append(toIndentedString(priceType)).append("\n");
    sb.append("    recurringChargePeriodLength: ").append(toIndentedString(recurringChargePeriodLength)).append("\n");
    sb.append("    recurringChargePeriodType: ").append(toIndentedString(recurringChargePeriodType)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    bundledPopRelationship: ").append(toIndentedString(bundledPopRelationship)).append("\n");
    sb.append("    constraint: ").append(toIndentedString(constraint)).append("\n");
    sb.append("    place: ").append(toIndentedString(place)).append("\n");
    sb.append("    popRelationship: ").append(toIndentedString(popRelationship)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    pricingLogicAlgorithm: ").append(toIndentedString(pricingLogicAlgorithm)).append("\n");
    sb.append("    prodSpecCharValueUse: ").append(toIndentedString(prodSpecCharValueUse)).append("\n");
    sb.append("    productOfferingTerm: ").append(toIndentedString(productOfferingTerm)).append("\n");
    sb.append("    tax: ").append(toIndentedString(tax)).append("\n");
    sb.append("    unitOfMeasure: ").append(toIndentedString(unitOfMeasure)).append("\n");
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

