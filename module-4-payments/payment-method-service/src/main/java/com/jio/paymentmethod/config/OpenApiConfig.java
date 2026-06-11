package com.jio.paymentmethod.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI paymentMethodOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TMF671 Payment Method Management API")
                        .version("4.0.0")
                        .description("Manages payment instruments registered by customers — " +
                                     "UPI IDs, credit/debit cards, net banking accounts, and digital wallets. " +
                                     "Referenced by TMF676 Payment Management via paymentMethodId.")
                        .contact(new Contact()
                                .name("Jio Subscription Engine")
                                .email("saanvi3605@gmail.com")));
    }
}
