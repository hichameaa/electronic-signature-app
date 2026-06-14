package com.hichamea.esign.signature_service.domain.service;

import com.hichamea.esign.signature_service.domain.model.Document;

public class SignatureDomainService {

    public void validateDocument(Document document) {
        if (document.getFileName() == null || document.getFileName().isBlank()) {
            throw new IllegalArgumentException("File name cannot be empty");
        }
        if (document.getSignerName() == null || document.getSignerName().isBlank()) {
            throw new IllegalArgumentException("Signer name cannot be empty");
        }
        if (!document.getFileName().toLowerCase().endsWith(".pdf")) {
            throw new IllegalArgumentException("Only PDF files are supported");
        }
    }

    public void validateCanBeSigned(Document document) {
        if (document.isSigned()) {
            throw new IllegalStateException(
                    "Document already signed: " + document.getId()
            );
        }
    }
}
