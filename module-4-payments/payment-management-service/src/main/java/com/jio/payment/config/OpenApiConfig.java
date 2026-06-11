package com.jio.payment.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI paymentManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TMF676 Payment Management API")
                        .version("4.0.0")
                        .description("Manages payment transactions for customer subscriptions. " +
                                     "References Customer Accounts (TMF629) and Product Inventory (TMF637). " +
                                     "Payment methods are managed separately in TMF671.")
                        .contact(new Contact()
                                .name("Jio Subscription Engine")
                                .email("saanvi3605@gmail.com")));
    }
}
