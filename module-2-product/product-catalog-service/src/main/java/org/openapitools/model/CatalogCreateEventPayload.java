package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.model.Catalog;
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

@Schema(name = "CatalogCreateEventPayload", description = "The event data structure")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T16:22:46.010747900+05:30[Asia/Calcutta]", comments = "Generator version: 7.22.0")
public class CatalogCreateEventPayload {

  private @Nullable Catalog catalog;

  public CatalogCreateEventPayload catalog(@Nullable Catalog catalog) {
    this.catalog = catalog;
    return this;
  }

  /**
   * Get catalog
   * @return catalog
   */
  @Valid 
  @Schema(name = "catalog", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("catalog")
  public @Nullable Catalog getCatalog() {
    return catalog;
  }

  @JsonProperty("catalog")
  public void setCatalog(@Nullable Catalog catalog) {
    this.catalog = catalog;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CatalogCreateEventPayload catalogCreateEventPayload = (CatalogCreateEventPayload) o;
    return Objects.equals(this.catalog, catalogCreateEventPayload.catalog);
  }

  @Override
  public int hashCode() {
    return Objects.hash(catalog);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CatalogCreateEventPayload {\n");
    sb.append("    catalog: ").append(toIndentedString(catalog)).append("\n");
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

