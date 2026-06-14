package com.hichamea.esign.signature_service.infrastructure.persistence.mapper;

import com.hichamea.esign.signature_service.domain.model.Document;
import com.hichamea.esign.signature_service.domain.model.valueobject.DocumentId;
import com.hichamea.esign.signature_service.infrastructure.persistence.entity.DocumentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    // Document (domain) → DocumentEntity (JPA)
    @Mapping(target = "id", expression = "java(document.getId().getValue())")
    DocumentEntity toEntity(Document document);

    // DocumentEntity (JPA) → Document (domain)
    default Document toDomain(DocumentEntity entity) {
        return new Document(
                DocumentId.of(entity.getId()),
                entity.getFileName(),
                entity.getSignerName(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getSignedAt()
        );
    }
}
