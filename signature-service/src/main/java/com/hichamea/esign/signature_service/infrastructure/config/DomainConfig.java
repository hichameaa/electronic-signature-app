package com.hichamea.esign.signature_service.infrastructure.config;

import com.hichamea.esign.signature_service.domain.service.SignatureDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public SignatureDomainService signatureDomainService() {
        return new SignatureDomainService();
    }
}
