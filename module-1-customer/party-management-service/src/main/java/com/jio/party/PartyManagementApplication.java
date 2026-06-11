package com.jio.party;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TMF632 Party Management Service
 * Port: 8082 | DB: jio_party
 *
 * Resources:
 *   GET/POST/PATCH/DELETE /tmf-api/partyManagement/v4/individual
 *   GET/POST/PATCH/DELETE /tmf-api/partyManagement/v4/partyRole
 *
 * Swagger UI: http://localhost:8082/swagger-ui.html
 */
@SpringBootApplication
public class PartyManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(PartyManagementApplication.class, args);
    }
}
