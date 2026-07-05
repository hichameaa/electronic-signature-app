package com.hichamea.esign.archive_service.infrastructure.persistence.repository;

import com.hichamea.esign.archive_service.application.port.out.ArchiveRepositoryPort;
import com.hichamea.esign.archive_service.domain.model.Archive;
import com.hichamea.esign.archive_service.domain.model.valueobject.ArchiveId;
import com.hichamea.esign.archive_service.domain.repository.ArchiveRepository;
import com.hichamea.esign.archive_service.infrastructure.persistence.entity.ArchiveEntity;
import com.hichamea.esign.archive_service.infrastructure.persistence.mapper.ArchiveMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArchiveRepositoryImpl implements ArchiveRepository, ArchiveRepositoryPort {

    private final ArchiveJpaRepository jpaRepository;
    private final ArchiveMapper archiveMapper;

    @Override
    public Archive save(Archive archive) {
        ArchiveEntity entity = archiveMapper.toEntity(archive);
        ArchiveEntity saved = jpaRepository.save(entity);
        return archiveMapper.toDomain(saved);
    }

    @Override
    public Optional<Archive> findById(ArchiveId id) {
        return jpaRepository.findById(id.getValue())
                            .map(archiveMapper::toDomain);
    }

    @Override
    public List<Archive> findAll() {
        return jpaRepository.findAll()
                            .stream()
                            .map(archiveMapper::toDomain)
                            .toList();
    }
}
