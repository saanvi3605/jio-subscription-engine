package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.model.ProductOffering;
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

@Schema(name = "ProductOfferingDeleteEventPayload", description = "The event data structure")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T16:22:46.010747900+05:30[Asia/Calcutta]", comments = "Generator version: 7.22.0")
public class ProductOfferingDeleteEventPayload {

  private @Nullable ProductOffering productOffering;

  public ProductOfferingDeleteEventPayload productOffering(@Nullable ProductOffering productOffering) {
    this.productOffering = productOffering;
    return this;
  }

  /**
   * Get productOffering
   * @return productOffering
   */
  @Valid 
  @Schema(name = "productOffering", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("productOffering")
  public @Nullable ProductOffering getProductOffering() {
    return productOffering;
  }

  @JsonProperty("productOffering")
  public void setProductOffering(@Nullable ProductOffering productOffering) {
    this.productOffering = productOffering;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductOfferingDeleteEventPayload productOfferingDeleteEventPayload = (ProductOfferingDeleteEventPayload) o;
    return Objects.equals(this.productOffering, productOfferingDeleteEventPayload.productOffering);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productOffering);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductOfferingDeleteEventPayload {\n");
    sb.append("    productOffering: ").append(toIndentedString(productOffering)).append("\n");
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

