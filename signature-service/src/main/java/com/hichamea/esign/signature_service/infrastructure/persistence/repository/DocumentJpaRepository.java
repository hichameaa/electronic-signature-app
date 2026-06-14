package com.hichamea.esign.signature_service.infrastructure.persistence.repository;

import com.hichamea.esign.signature_service.infrastructure.persistence.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentJpaRepository extends JpaRepository<DocumentEntity, String> {
}
