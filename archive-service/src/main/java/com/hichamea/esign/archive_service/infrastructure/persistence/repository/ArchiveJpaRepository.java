package com.hichamea.esign.archive_service.infrastructure.persistence.repository;

import com.hichamea.esign.archive_service.infrastructure.persistence.entity.ArchiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchiveJpaRepository extends JpaRepository<ArchiveEntity, String> {
}