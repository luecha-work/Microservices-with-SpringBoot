package com.example.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Workshop Account Service API",
                description = "EazyBank Account microservice Rest API Document",
                version = "v1",
                contact = @Contact(
                        name = "Workshop EazyBank",
                        email = "admin@test.com",
                        url = "www.google.com"
                ),
                license = @License(
						name = "Apache 2.0",
						url = "https://www.eazybytes.com"
				)
        ),
        externalDocs = @ExternalDocumentation(
                description = "EazyBank Account microservice Rest API Document",
                url = "http://localhost:8080/swagger-ui/index.html"
        )
)
public class Session1BuildMicroservicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(Session1BuildMicroservicesApplication.class, args);
    }

}
