package com.hichamea.esign.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                      // signature-service
                      .route("signature-service", r -> r
                              .path("/api/v1/signatures/**")
                              .uri("http://localhost:8081"))
                      // archive-service
                      .route("archive-service", r -> r
                              .path("/api/v1/archives/**")
                              .uri("http://localhost:8082"))
                      .build();
    }
}