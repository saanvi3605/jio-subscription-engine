package com.jio.processflow.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TMF701 Process Flow Management API")
                        .version("4.0")
                        .description("Orchestrates end-to-end customer lifecycle flows across all Jio microservices. " +
                                     "Supports three built-in definitions: CustomerOnboarding, " +
                                     "ExistingCustomerOrder, and FastCheckout."));
    }
}
