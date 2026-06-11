package com.jio.usage.controller;

import com.jio.usage.model.Usage;
import com.jio.usage.service.UsageService;
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
 * TMF635 Usage Management API
 * Base path: /tmf-api/usageManagement/v4/usage
 */
@RestController
@RequestMapping("/tmf-api/usageManagement/v4/usage")
@Tag(name = "Usage", description = "TMF635 — Manages usage records (data, voice, SMS consumption events)")
public class UsageController {

    private final UsageService service;

    public UsageController(UsageService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Creates a Usage record",
               description = "Records a consumption event for a customer. " +
                             "Requires customerId (TMF629) and usageDate. " +
                             "Optionally links productId (TMF637).")
    @ApiResponse(responseCode = "201", description = "Usage record created")
    @ApiResponse(responseCode = "400", description = "Validation error")
    public ResponseEntity<Usage> createUsage(@Valid @RequestBody Usage usage) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(usage));
    }

    @GetMapping
    @Operation(summary = "List or find Usage records")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<List<Usage>> listUsage(
            @Parameter(description = "Filter by customerId (TMF629)")
            @RequestParam(required = false) String customerId,
            @Parameter(description = "Filter by productId (TMF637)")
            @RequestParam(required = false) String productId,
            @Parameter(description = "Filter by usageType: DATA, VOICE, SMS, ROAMING_DATA, ROAMING_VOICE")
            @RequestParam(required = false) String usageType,
            @Parameter(description = "Filter by status: received, guided, rated, billed")
            @RequestParam(required = false) String status) {

        if (customerId != null && usageType != null) {
            return ResponseEntity.ok(service.findByCustomerIdAndUsageType(customerId, usageType));
        }
        if (customerId != null && status != null) {
            return ResponseEntity.ok(service.findByCustomerIdAndStatus(customerId, status));
        }
        if (customerId != null) {
            return ResponseEntity.ok(service.findByCustomerId(customerId));
        }
        if (productId != null) {
            return ResponseEntity.ok(service.findByProductId(productId));
        }
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieves a Usage record by ID")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<Usage> retrieveUsage(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Updates partially a Usage record",
               description = "Typically used to update status (e.g. received → rated → billed) " +
                             "or set ratedAmount after pricing.")
    @ApiResponse(responseCode = "200", description = "Updated")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<Usage> patchUsage(
            @PathVariable String id,
            @RequestBody Usage patch) {
        return service.patch(id, patch)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a Usage record")
    @ApiResponse(responseCode = "204", description = "Deleted")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<Void> deleteUsage(@PathVariable String id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
