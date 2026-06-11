package com.jio.party.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI partyManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TMF632 Party Management API")
                        .description(
                            "Manages Individual and PartyRole resources for the Jio Subscription Engine. " +
                            "A Party is any person or organization in the system. " +
                            "A PartyRole defines what role that party plays — " +
                            "Subscriber, ContentProvider, BankingPartner, or Retailer.")
                        .version("v4.0.0")
                        .contact(new Contact()
                                .name("Jio Subscription Engine")
                                .email("saanvi3605@gmail.com")));
    }
}
