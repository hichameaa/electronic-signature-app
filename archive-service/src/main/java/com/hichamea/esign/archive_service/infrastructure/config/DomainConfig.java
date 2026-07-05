package com.hichamea.esign.archive_service.infrastructure.config;

import com.hichamea.esign.archive_service.domain.service.ArchiveDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public ArchiveDomainService archiveDomainService() {
        return new ArchiveDomainService();
    }
}
