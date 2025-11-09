package com.blandev.lottery.backend.infrastructure.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Lottery Management System API")
                        .version("1.0")
                        .description("API documentation for the Lottery Management System")
                        .contact(new Contact()
                                .name("Jacobo Blandón Castro")
                                .email("jacoboblandon94@gmail.com")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Local server")));
    }

}
