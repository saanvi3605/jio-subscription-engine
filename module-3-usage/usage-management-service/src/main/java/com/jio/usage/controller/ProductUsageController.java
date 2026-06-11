package com.jio.usage.controller;

import com.jio.usage.model.ProductUsage;
import com.jio.usage.service.ProductUsageService;
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
 * TMF635 Product Usage resource
 * Base path: /tmf-api/usageManagement/v4/productUsage
 *
 * Complements the /usage endpoint — while /usage logs individual events,
 * /productUsage tracks the aggregate consumption against a plan's allowance.
 */
@RestController
@RequestMapping("/tmf-api/usageManagement/v4/productUsage")
@Tag(name = "ProductUsage", description = "TMF635 — Tracks aggregate usage per product (allowance vs consumed vs remaining)")
public class ProductUsageController {

    private final ProductUsageService service;

    public ProductUsageController(ProductUsageService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Creates a ProductUsage summary",
               description = "Initialises a usage tracker for a product's allowance. " +
                             "Set allowance to null for unlimited plans. " +
                             "Requires productId (TMF637) and customerId (TMF629).")
    @ApiResponse(responseCode = "201", description = "ProductUsage created")
    @ApiResponse(responseCode = "400", description = "Validation error")
    public ResponseEntity<ProductUsage> createProductUsage(
            @Valid @RequestBody ProductUsage productUsage) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(productUsage));
    }

    @GetMapping
    @Operation(summary = "List or find ProductUsage records")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<List<ProductUsage>> listProductUsage(
            @Parameter(description = "Filter by customerId (TMF629)")
            @RequestParam(required = false) String customerId,
            @Parameter(description = "Filter by productId (TMF637)")
            @RequestParam(required = false) String productId) {

        if (customerId != null) {
            return ResponseEntity.ok(service.findByCustomerId(customerId));
        }
        if (productId != null) {
            return ResponseEntity.ok(service.findByProductId(productId));
        }
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieves a ProductUsage record by ID")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<ProductUsage> retrieveProductUsage(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Updates a ProductUsage record",
               description = "Update consumed to reflect new usage. " +
                             "remaining and status are auto-recalculated — " +
                             "status becomes 'exhausted' when consumed >= allowance.")
    @ApiResponse(responseCode = "200", description = "Updated")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<ProductUsage> patchProductUsage(
            @PathVariable String id,
            @RequestBody ProductUsage patch) {
        return service.patch(id, patch)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a ProductUsage record")
    @ApiResponse(responseCode = "204", description = "Deleted")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<Void> deleteProductUsage(@PathVariable String id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
