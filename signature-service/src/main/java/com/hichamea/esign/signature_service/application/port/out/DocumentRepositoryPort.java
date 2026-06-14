package com.hichamea.esign.signature_service.application.port.out;

import com.hichamea.esign.signature_service.domain.model.Document;
import com.hichamea.esign.signature_service.domain.model.valueobject.DocumentId;

import java.util.List;
import java.util.Optional;

public interface DocumentRepositoryPort {
    Document save(Document document);
    Optional<Document> findById(DocumentId id);
    List<Document> findAll();
}
