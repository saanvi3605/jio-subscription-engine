package com.jio.customer.controller;

import com.jio.customer.model.Customer;
import com.jio.customer.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Auth endpoints — signup and login.
 * Base path: /tmf-api/customerManagement/v4/auth
 */
@RestController
@RequestMapping("/tmf-api/customerManagement/v4/auth")
@Tag(name = "Auth", description = "Customer signup and login")
@Validated
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // ── Request bodies ────────────────────────────────────────────────────────

    record SignupRequest(
        @NotBlank String givenName,
        @NotBlank String familyName,
        @NotBlank @Email String email,
        @NotBlank String password
    ) {}

    record LoginRequest(
        @NotBlank @Email String email,
        @NotBlank String password
    ) {}

    // ── Endpoints ─────────────────────────────────────────────────────────────

    @PostMapping("/signup")
    @Operation(
        summary = "Sign up as a new customer",
        description = "Creates an Individual (TMF632) + Customer record with status=prospect. " +
                      "A welcome SMS is dispatched automatically."
    )
    @ApiResponse(responseCode = "201", description = "Customer created with status=prospect")
    @ApiResponse(responseCode = "409", description = "Email already registered")
    public ResponseEntity<Customer> signup(@RequestBody SignupRequest req) {
        Customer created = authService.signup(
            req.givenName(), req.familyName(), req.email(), req.password());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/login")
    @Operation(
        summary = "Log in as an existing customer",
        description = "Verifies credentials. If the customer's status is prospect, " +
                      "it is promoted to active and an activation SMS is sent."
    )
    @ApiResponse(responseCode = "200", description = "Login successful — customer returned")
    @ApiResponse(responseCode = "401", description = "Invalid email or password")
    public ResponseEntity<Customer> login(@RequestBody LoginRequest req) {
        Customer customer = authService.login(req.email(), req.password());
        return ResponseEntity.ok(customer);
    }
}
