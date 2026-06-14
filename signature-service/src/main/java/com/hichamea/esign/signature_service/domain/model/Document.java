package com.hichamea.esign.signature_service.domain.model;

import com.hichamea.esign.signature_service.domain.model.valueobject.DocumentId;
import com.hichamea.esign.signature_service.domain.model.valueobject.SignatureStatus;
import lombok.Getter;

import javax.print.Doc;
import java.time.LocalDateTime;

@Getter
public class Document {

    private final DocumentId id;
    private String fileName;
    private String signerName;
    private SignatureStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime signedAt;

    // Création d'un nouveau document
    public Document(String fileName, String signerName) {
        this.id = DocumentId.generate();
        this.fileName = fileName;
        this.signerName = signerName;
        this.status = SignatureStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    // Reconstruction depuis la base de données
    public Document(DocumentId id, String fileName, String signerName, SignatureStatus status, LocalDateTime createdAt, LocalDateTime signedAt) {
        this.id = id;
        this.fileName = fileName;
        this.signerName = signerName;
        this.status = status;
        this.createdAt = createdAt;
        this.signedAt = signedAt;
    }

    public void markAsSigned() {
        this.status = SignatureStatus.SIGNED;
        this.signedAt = LocalDateTime.now();
    }

    public void markAsFailed() {
        this.status = SignatureStatus.FAILED;
    }

    public void markAsArchived() {
        this.status = SignatureStatus.ARCHIVED;
    }

    public boolean isSigned() {
        return SignatureStatus.SIGNED.equals(this.status);
    }
}
