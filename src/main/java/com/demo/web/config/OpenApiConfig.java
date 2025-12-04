package com.demo.web.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Java Demo API",
                version = "1.0",
                description = "Spring Boot + SQLite 示例接口"
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Local")
        }
)
@Configuration
public class OpenApiConfig {
}
