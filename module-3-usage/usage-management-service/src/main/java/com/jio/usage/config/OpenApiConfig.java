package com.jio.usage.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI usageManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TMF635 Usage Management API")
                        .version("4.0.0")
                        .description("Manages usage records — data sessions, voice calls, and SMS events. " +
                                     "Links customers (TMF629) to their product consumption (TMF637).")
                        .contact(new Contact()
                                .name("Jio Subscription Engine")
                                .email("saanvi3605@gmail.com")));
    }
}
