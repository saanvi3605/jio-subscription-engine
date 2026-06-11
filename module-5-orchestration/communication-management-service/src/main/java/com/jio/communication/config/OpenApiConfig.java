package com.jio.communication.config;

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
                        .title("TMF681 Communication Management API")
                        .version("4.0")
                        .description("Manages notifications (SMS, Email, Push) triggered by lifecycle events " +
                                     "across the Jio Subscription Engine. Messages are created by TMF629, " +
                                     "TMF622, TMF676, and TMF701 on key events such as customer onboarding, " +
                                     "order completion, and payment settlement."));
    }
}
