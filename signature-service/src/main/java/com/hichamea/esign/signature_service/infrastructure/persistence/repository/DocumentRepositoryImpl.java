package com.hichamea.esign.signature_service.infrastructure.persistence.repository;

import com.hichamea.esign.signature_service.application.port.out.DocumentRepositoryPort;
import com.hichamea.esign.signature_service.domain.model.Document;
import com.hichamea.esign.signature_service.domain.model.valueobject.DocumentId;
import com.hichamea.esign.signature_service.domain.repository.DocumentRepository;
import com.hichamea.esign.signature_service.infrastructure.persistence.entity.DocumentEntity;
import com.hichamea.esign.signature_service.infrastructure.persistence.mapper.DocumentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DocumentRepositoryImpl implements DocumentRepository, DocumentRepositoryPort {

    private final DocumentJpaRepository jpaRepository;
    private final DocumentMapper documentMapper;


    @Override
    public Document save(Document document) {
        DocumentEntity entity = documentMapper.toEntity(document);
        DocumentEntity saved = jpaRepository.save(entity);
        return documentMapper.toDomain(saved);
    }

    @Override
    public Optional<Document> findById(DocumentId id) {
        return jpaRepository.findById(id.getValue())
                            .map(documentMapper::toDomain);
    }

    @Override
    public List<Document> findAll() {
        return jpaRepository.findAll()
                            .stream()
                            .map(documentMapper::toDomain)
                            .toList();
    }

    @Override
    public void deleteById(DocumentId id) {
        jpaRepository.deleteById(id.getValue());
    }
}
