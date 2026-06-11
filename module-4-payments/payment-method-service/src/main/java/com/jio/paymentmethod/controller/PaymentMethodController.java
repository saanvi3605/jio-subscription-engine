package com.jio.paymentmethod.controller;

import com.jio.paymentmethod.model.PaymentMethod;
import com.jio.paymentmethod.service.PaymentMethodService;
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
 * TMF671 Payment Method Management API
 * Base path: /tmf-api/paymentMethodManagement/v4/paymentMethod
 */
@RestController
@RequestMapping("/tmf-api/paymentMethodManagement/v4/paymentMethod")
@Tag(name = "PaymentMethod", description = "TMF671 — Manages payment instruments registered by customers (UPI, cards, net banking, wallets)")
public class PaymentMethodController {

    private final PaymentMethodService service;

    public PaymentMethodController(PaymentMethodService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Creates a PaymentMethod",
               description = "Registers a payment instrument for a customer. " +
                             "Fill only the fields relevant to the type: " +
                             "UPI → upiId, CREDIT/DEBIT_CARD → cardLast4/cardBrand/expiry, " +
                             "NET_BANKING → bankName/accountLast4/ifscCode, WALLET → walletProvider/walletId.")
    @ApiResponse(responseCode = "201", description = "PaymentMethod created")
    @ApiResponse(responseCode = "400", description = "Validation error")
    public ResponseEntity<PaymentMethod> createPaymentMethod(
            @Valid @RequestBody PaymentMethod method) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(method));
    }

    @GetMapping
    @Operation(summary = "List or find PaymentMethod records")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<List<PaymentMethod>> listPaymentMethods(
            @Parameter(description = "Filter by customerId (TMF629)")
            @RequestParam(required = false) String customerId,
            @Parameter(description = "Return only the default method for a customer")
            @RequestParam(required = false, defaultValue = "false") boolean defaultOnly) {

        if (customerId != null && defaultOnly) {
            return service.findDefaultByCustomerId(customerId)
                    .map(m -> ResponseEntity.ok(List.of(m)))
                    .orElse(ResponseEntity.ok(List.of()));
        }
        if (customerId != null) {
            return ResponseEntity.ok(service.findByCustomerId(customerId));
        }
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieves a PaymentMethod by ID")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<PaymentMethod> retrievePaymentMethod(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Updates partially a PaymentMethod",
               description = "Use to update status (e.g. active → expired) or set as default.")
    @ApiResponse(responseCode = "200", description = "Updated")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<PaymentMethod> patchPaymentMethod(
            @PathVariable String id,
            @RequestBody PaymentMethod patch) {
        return service.patch(id, patch)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a PaymentMethod")
    @ApiResponse(responseCode = "204", description = "Deleted")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable String id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
