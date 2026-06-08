package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.model.ProductSpecification;
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

@Schema(name = "ProductSpecificationCreateEventPayload", description = "The event data structure")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T16:22:46.010747900+05:30[Asia/Calcutta]", comments = "Generator version: 7.22.0")
public class ProductSpecificationCreateEventPayload {

  private @Nullable ProductSpecification productSpecification;

  public ProductSpecificationCreateEventPayload productSpecification(@Nullable ProductSpecification productSpecification) {
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
  public @Nullable ProductSpecification getProductSpecification() {
    return productSpecification;
  }

  @JsonProperty("productSpecification")
  public void setProductSpecification(@Nullable ProductSpecification productSpecification) {
    this.productSpecification = productSpecification;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductSpecificationCreateEventPayload productSpecificationCreateEventPayload = (ProductSpecificationCreateEventPayload) o;
    return Objects.equals(this.productSpecification, productSpecificationCreateEventPayload.productSpecification);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productSpecification);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductSpecificationCreateEventPayload {\n");
    sb.append("    productSpecification: ").append(toIndentedString(productSpecification)).append("\n");
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

