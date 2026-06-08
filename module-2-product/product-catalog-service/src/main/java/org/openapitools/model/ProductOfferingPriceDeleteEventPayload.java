package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.model.ProductOfferingPrice;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * The event data structure
 */

@Schema(name = "ProductOfferingPriceDeleteEventPayload", description = "The event data structure")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T16:22:46.010747900+05:30[Asia/Calcutta]", comments = "Generator version: 7.22.0")
public class ProductOfferingPriceDeleteEventPayload {

  private @Nullable ProductOfferingPrice productOfferingPrice;

  public ProductOfferingPriceDeleteEventPayload productOfferingPrice(@Nullable ProductOfferingPrice productOfferingPrice) {
    this.productOfferingPrice = productOfferingPrice;
    return this;
  }

  /**
   * Get productOfferingPrice
   * @return productOfferingPrice
   */
  @Valid 
  @Schema(name = "productOfferingPrice", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("productOfferingPrice")
  public @Nullable ProductOfferingPrice getProductOfferingPrice() {
    return productOfferingPrice;
  }

  @JsonProperty("productOfferingPrice")
  public void setProductOfferingPrice(@Nullable ProductOfferingPrice productOfferingPrice) {
    this.productOfferingPrice = productOfferingPrice;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductOfferingPriceDeleteEventPayload productOfferingPriceDeleteEventPayload = (ProductOfferingPriceDeleteEventPayload) o;
    return Objects.equals(this.productOfferingPrice, productOfferingPriceDeleteEventPayload.productOfferingPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productOfferingPrice);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductOfferingPriceDeleteEventPayload {\n");
    sb.append("    productOfferingPrice: ").append(toIndentedString(productOfferingPrice)).append("\n");
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

