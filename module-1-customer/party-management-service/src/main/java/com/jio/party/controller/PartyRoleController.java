package com.jio.party.controller;

import com.jio.party.model.PartyRole;
import com.jio.party.service.PartyRoleService;
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
 * TMF632 Party Management API — PartyRole resource
 * Base path: /tmf-api/partyManagement/v4/partyRole
 *
 * Examples of roles used in the Jio subscription engine:
 *   Subscriber, ContentProvider, BankingPartner, Retailer
 */
@RestController
@RequestMapping("/tmf-api/partyManagement/v4/partyRole")
@Tag(name = "PartyRole", description = "TMF632 — Manages party role resources (Subscriber, ContentProvider, BankingPartner, etc.)")
public class PartyRoleController {

    private final PartyRoleService service;

    public PartyRoleController(PartyRoleService service) {
        this.service = service;
    }

    // ── POST /partyRole ─────────────────────────────────────────────────────
    @PostMapping
    @Operation(summary = "Creates a PartyRole",
               description = "Assigns a role to an Individual or Organization party")
    @ApiResponse(responseCode = "201", description = "PartyRole created")
    @ApiResponse(responseCode = "400", description = "Validation error")
    public ResponseEntity<PartyRole> createPartyRole(
            @Valid @RequestBody PartyRole partyRole) {
        PartyRole created = service.create(partyRole);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ── GET /partyRole ──────────────────────────────────────────────────────
    @GetMapping
    @Operation(summary = "List or find PartyRole objects")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<List<PartyRole>> listPartyRole(
            @Parameter(description = "Filter by engagedPartyId (individual or org id)")
            @RequestParam(required = false) String engagedPartyId,
            @Parameter(description = "Filter by role name (e.g. Subscriber, ContentProvider)")
            @RequestParam(required = false) String name) {

        List<PartyRole> result;
        if (engagedPartyId != null) {
            result = service.findByEngagedPartyId(engagedPartyId);
        } else if (name != null) {
            result = service.findAll().stream()
                    .filter(r -> name.equalsIgnoreCase(r.getName()))
                    .toList();
        } else {
            result = service.findAll();
        }
        return ResponseEntity.ok(result);
    }

    // ── GET /partyRole/{id} ─────────────────────────────────────────────────
    @GetMapping("/{id}")
    @Operation(summary = "Retrieves a PartyRole by ID")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "404", description = "PartyRole not found")
    public ResponseEntity<PartyRole> retrievePartyRole(
            @Parameter(description = "Unique id of the PartyRole") @PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ── PATCH /partyRole/{id} ───────────────────────────────────────────────
    @PatchMapping("/{id}")
    @Operation(summary = "Updates partially a PartyRole",
               description = "Only the fields present in the request body are updated")
    @ApiResponse(responseCode = "200", description = "Updated successfully")
    @ApiResponse(responseCode = "404", description = "PartyRole not found")
    public ResponseEntity<PartyRole> patchPartyRole(
            @Parameter(description = "Unique id of the PartyRole") @PathVariable String id,
            @RequestBody PartyRole patch) {
        return service.patch(id, patch)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ── DELETE /partyRole/{id} ──────────────────────────────────────────────
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a PartyRole")
    @ApiResponse(responseCode = "204", description = "Deleted successfully")
    @ApiResponse(responseCode = "404", description = "PartyRole not found")
    public ResponseEntity<Void> deletePartyRole(
            @Parameter(description = "Unique id of the PartyRole") @PathVariable String id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
