package com.jio.customer.service;

import com.jio.customer.client.CommunicationClient;
import com.jio.customer.client.PartyClient;
import com.jio.customer.model.Customer;
import com.jio.customer.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;

@Service
@Transactional
public class AuthService {

    private final CustomerRepository repository;
    private final PartyClient partyClient;
    private final CommunicationClient communicationClient;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(CustomerRepository repository,
                       PartyClient partyClient,
                       CommunicationClient communicationClient,
                       BCryptPasswordEncoder passwordEncoder) {
        this.repository          = repository;
        this.partyClient         = partyClient;
        this.communicationClient = communicationClient;
        this.passwordEncoder     = passwordEncoder;
    }

    /**
     * Signup: creates an Individual in TMF632, then a Customer record with
     * status="prospect". Welcome notification fires automatically.
     */
    public Customer signup(String givenName, String familyName,
                           String email, String rawPassword) {

        if (repository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "An account with email '" + email + "' already exists.");
        }

        String individualId = partyClient.createIndividual(givenName, familyName);

        Customer customer = new Customer();
        customer.setName(givenName + " " + familyName);
        customer.setEmail(email);
        customer.setPasswordHash(passwordEncoder.encode(rawPassword));
        customer.setEngagedPartyId(individualId);
        customer.setEngagedPartyHref(
            "/tmf-api/partyManagement/v4/individual/" + individualId);
        customer.setEngagedPartyName(givenName + " " + familyName);
        customer.setStatus("prospect");
        customer.setCreatedAt(OffsetDateTime.now());

        Customer saved = repository.save(customer);

        // Wire 15: welcome SMS (fire-and-forget)
        communicationClient.sendWelcome(saved.getId(), saved.getName());

        return saved;
    }

    /**
     * Login: verifies credentials, and promotes a prospect → active on first login.
     * Fires an activation notification if the status changed.
     */
    @Transactional
    public Customer login(String email, String rawPassword) {
        Customer customer = repository.findByEmail(email)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, "Invalid email or password."));

        if (!passwordEncoder.matches(rawPassword, customer.getPasswordHash())) {
            throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, "Invalid email or password.");
        }

        if ("prospect".equalsIgnoreCase(customer.getStatus())) {
            customer.setStatus("active");
            customer.setStatusReason("Activated on first login");
            customer.setUpdatedAt(OffsetDateTime.now());
            repository.save(customer);

            // Fire activation notification (fire-and-forget)
            communicationClient.sendActivation(customer.getId(), customer.getName());
        }

        return customer;
    }
}
