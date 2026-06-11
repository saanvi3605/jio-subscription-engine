package com.jio.inventory.controller;

import com.jio.inventory.model.Product;
import com.jio.inventory.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TMF637 Product Inventory Management API
 * Base path: /tmf-api/productInventoryManagement/v4/product
 */
@RestController
@RequestMapping("/tmf-api/productInventoryManagement/v4/product")
@Tag(name = "Product", description = "TMF637 — Manages product inventory items (active products held by customers)")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Creates a Product inventory item",
               description = "Records a product that a customer now holds after order fulfilment. " +
                             "Requires customerId (from TMF629). Optionally links productOrderId (TMF622) " +
                             "and productOfferingId (TMF620).")
    @ApiResponse(responseCode = "201", description = "Product created")
    @ApiResponse(responseCode = "400", description = "Validation error")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(product));
    }

    @GetMapping
    @Operation(summary = "List or find Product inventory items")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<List<Product>> listProducts(
            @Parameter(description = "Filter by customerId (TMF629)")
            @RequestParam(required = false) String customerId,
            @Parameter(description = "Filter by status: active, inactive, suspended, terminated")
            @RequestParam(required = false) String status,
            @Parameter(description = "Filter by productOrderId (TMF622)")
            @RequestParam(required = false) String productOrderId) {

        if (customerId != null && status != null) {
            return ResponseEntity.ok(service.findByCustomerIdAndStatus(customerId, status));
        }
        if (customerId != null) {
            return ResponseEntity.ok(service.findByCustomerId(customerId));
        }
        if (status != null) {
            return ResponseEntity.ok(service.findByStatus(status));
        }
        if (productOrderId != null) {
            return ResponseEntity.ok(service.findByProductOrderId(productOrderId));
        }
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieves a Product inventory item by ID")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<Product> retrieveProduct(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Updates partially a Product inventory item",
               description = "Only fields present in the body are updated. " +
                             "Use this to update status (e.g. active → terminated) or terminationDate.")
    @ApiResponse(responseCode = "200", description = "Updated")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<Product> patchProduct(
            @PathVariable String id,
            @RequestBody Product patch) {
        return service.patch(id, patch)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a Product inventory item")
    @ApiResponse(responseCode = "204", description = "Deleted")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
