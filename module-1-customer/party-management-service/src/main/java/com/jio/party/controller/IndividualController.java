package com.jio.party.controller;

import com.jio.party.model.Individual;
import com.jio.party.service.IndividualService;
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
 * TMF632 Party Management API — Individual resource
 * Base path: /tmf-api/partyManagement/v4/individual
 */
@RestController
@RequestMapping("/tmf-api/partyManagement/v4/individual")
@Tag(name = "Individual", description = "TMF632 — Manages individual party resources")
public class IndividualController {

    private final IndividualService service;

    public IndividualController(IndividualService service) {
        this.service = service;
    }

    // ── POST /individual ────────────────────────────────────────────────────
    @PostMapping
    @Operation(summary = "Creates an Individual",
               description = "Creates a new Individual party (customer, agent, etc.)")
    @ApiResponse(responseCode = "201", description = "Individual created")
    @ApiResponse(responseCode = "400", description = "Validation error")
    public ResponseEntity<Individual> createIndividual(
            @Valid @RequestBody Individual individual) {
        Individual created = service.create(individual);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ── GET /individual ─────────────────────────────────────────────────────
    @GetMapping
    @Operation(summary = "List or find Individual objects",
               description = "Returns all individuals, optionally filtered by status")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<List<Individual>> listIndividual(
            @Parameter(description = "Filter by lifecycle status (initialized, validated, bankrupt, deceased)")
            @RequestParam(required = false) String status) {

        List<Individual> result = (status != null)
                ? service.findAll().stream()
                    .filter(i -> status.equalsIgnoreCase(i.getStatus()))
                    .toList()
                : service.findAll();
        return ResponseEntity.ok(result);
    }

    // ── GET /individual/{id} ────────────────────────────────────────────────
    @GetMapping("/{id}")
    @Operation(summary = "Retrieves an Individual by ID")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "404", description = "Individual not found")
    public ResponseEntity<Individual> retrieveIndividual(
            @Parameter(description = "Unique id of the Individual") @PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ── PATCH /individual/{id} ──────────────────────────────────────────────
    @PatchMapping("/{id}")
    @Operation(summary = "Updates partially an Individual",
               description = "Only the fields present in the request body are updated")
    @ApiResponse(responseCode = "200", description = "Updated successfully")
    @ApiResponse(responseCode = "404", description = "Individual not found")
    public ResponseEntity<Individual> patchIndividual(
            @Parameter(description = "Unique id of the Individual") @PathVariable String id,
            @RequestBody Individual patch) {
        return service.patch(id, patch)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ── DELETE /individual/{id} ─────────────────────────────────────────────
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes an Individual")
    @ApiResponse(responseCode = "204", description = "Deleted successfully")
    @ApiResponse(responseCode = "404", description = "Individual not found")
    public ResponseEntity<Void> deleteIndividual(
            @Parameter(description = "Unique id of the Individual") @PathVariable String id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
