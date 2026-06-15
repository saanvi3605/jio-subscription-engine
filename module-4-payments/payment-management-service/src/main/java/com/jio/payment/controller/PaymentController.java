package com.jio.payment.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jio.payment.model.Payment;
import com.jio.payment.model.PaymentInitiateRequest;
import com.jio.payment.model.PaymentInitiateResponse;
import com.jio.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

/**
 * TMF676 Payment Management API
 * Base path: /tmf-api/paymentManagement/v4/payment
 */
@RestController
@RequestMapping("/tmf-api/paymentManagement/v4/payment")
@Tag(name = "Payment", description = "TMF676 — Manages payment transactions against customer accounts")
public class PaymentController {

    private final PaymentService service;
    private final ObjectMapper objectMapper;

    public PaymentController(PaymentService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    @Operation(summary = "Creates a Payment",
               description = "Records a payment transaction. Requires customerId and customerAccountId " +
                             "(from TMF629) and paymentMethodType. Optionally links productId (TMF637).")
    @ApiResponse(responseCode = "201", description = "Payment created")
    @ApiResponse(responseCode = "400", description = "Validation error")
    public ResponseEntity<Payment> createPayment(@Valid @RequestBody Payment payment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(payment));
    }

    @GetMapping
    @Operation(summary = "List or find Payment records")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<List<Payment>> listPayments(
            @Parameter(description = "Filter by customerId (TMF629)")
            @RequestParam(required = false) String customerId,
            @Parameter(description = "Filter by customerAccountId (TMF629)")
            @RequestParam(required = false) String customerAccountId,
            @Parameter(description = "Filter by productId (TMF637)")
            @RequestParam(required = false) String productId,
            @Parameter(description = "Filter by status: received, settled, rejected, held")
            @RequestParam(required = false) String status) {

        if (customerId != null && status != null) {
            return ResponseEntity.ok(service.findByCustomerIdAndStatus(customerId, status));
        }
        if (customerId != null) {
            return ResponseEntity.ok(service.findByCustomerId(customerId));
        }
        if (customerAccountId != null) {
            return ResponseEntity.ok(service.findByCustomerAccountId(customerAccountId));
        }
        if (productId != null) {
            return ResponseEntity.ok(service.findByProductId(productId));
        }
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieves a Payment by ID")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<Payment> retrievePayment(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Updates partially a Payment",
               description = "Typically used to update status (e.g. received → settled or rejected) " +
                             "and set authorizationCode after gateway confirmation.")
    @ApiResponse(responseCode = "200", description = "Updated")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<Payment> patchPayment(
            @PathVariable String id,
            @RequestBody Payment patch) {
        return service.patch(id, patch)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a Payment record")
    @ApiResponse(responseCode = "204", description = "Deleted")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<Void> deletePayment(@PathVariable String id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // ── Razorpay gateway endpoints ────────────────────────────────────────────

    @PostMapping("/initiate")
    @Operation(
        summary = "Initiates a Razorpay payment session",
        description = "Creates a Razorpay order and a pending Payment record. " +
                      "Pass the returned razorpayOrderId, amountInPaise, currency, and razorpayKeyId " +
                      "to Razorpay Checkout JS to show the payment popup.")
    @ApiResponse(responseCode = "201", description = "Payment session created — use the response with Razorpay Checkout JS")
    @ApiResponse(responseCode = "422", description = "Invalid customerId, customerAccountId, or productId")
    @ApiResponse(responseCode = "502", description = "Razorpay order creation failed")
    public ResponseEntity<PaymentInitiateResponse> initiatePayment(
            @Valid @RequestBody PaymentInitiateRequest request) {
        PaymentInitiateResponse response = service.initiatePayment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/webhook")
    @Operation(
        summary = "Razorpay webhook callback",
        description = "Receives payment events from Razorpay (payment.captured / payment.failed). " +
                      "Configure this URL in your Razorpay dashboard under Webhooks. " +
                      "The endpoint verifies the X-Razorpay-Signature header before processing.")
    @ApiResponse(responseCode = "200", description = "Webhook processed")
    @ApiResponse(responseCode = "401", description = "Invalid signature")
    public ResponseEntity<Void> handleWebhook(
            @RequestHeader("X-Razorpay-Signature") String signature,
            @RequestBody String rawBody) {
        try {
            Map<String, Object> payload = objectMapper.readValue(
                rawBody, new TypeReference<Map<String, Object>>() {});
            service.handleWebhook(rawBody, signature, payload);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid webhook payload JSON");
        }
        return ResponseEntity.ok().build();
    }
}
