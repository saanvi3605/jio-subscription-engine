package com.jio.inventory.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI productInventoryOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TMF637 Product Inventory Management API")
                        .version("4.0.0")
                        .description("Manages product inventory items — the products a customer currently holds. " +
                                     "Bridges TMF629 (Customer), TMF622 (Product Order), and TMF620 (Product Catalog).")
                        .contact(new Contact()
                                .name("Jio Subscription Engine")
                                .email("saanvi3605@gmail.com")));
    }
}
