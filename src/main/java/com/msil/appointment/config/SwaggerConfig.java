package com.msil.appointment.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    OpenAPI appointmentOpenApi() {
        return new OpenAPI().info(new Info()
                .title("MSIL Appointment Service API")
                .version("1.0.0")
                .description("Pickup appointment management API"));
    }
}
