package com.hichamea.esign.archive_service.infrastructure.persistence.mapper;

import com.hichamea.esign.archive_service.domain.model.Archive;
import com.hichamea.esign.archive_service.domain.model.valueobject.ArchiveId;
import com.hichamea.esign.archive_service.infrastructure.persistence.entity.ArchiveEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArchiveMapper {

    @Mapping(target = "id", expression = "java(archive.getId().getValue())")
    ArchiveEntity toEntity(Archive archive);

    default Archive toDomain(ArchiveEntity entity) {
        return new Archive(
                ArchiveId.of(entity.getId()),
                entity.getFileName(),
                entity.getStoragePath(),
                entity.getDocumentId(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }
}
