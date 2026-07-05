package com.hichamea.esign.archive_service.domain.repository;

import com.hichamea.esign.archive_service.domain.model.Archive;
import com.hichamea.esign.archive_service.domain.model.valueobject.ArchiveId;

import java.util.List;
import java.util.Optional;

public interface ArchiveRepository {
    Archive save(Archive archive);
    Optional<Archive> findById(ArchiveId id);
    List<Archive> findAll();
}