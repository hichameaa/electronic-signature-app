package com.hichamea.esign.archive_service.domain.service;

import com.hichamea.esign.archive_service.domain.model.Archive;
import com.hichamea.esign.archive_service.domain.model.valueobject.ArchiveStatus;

public class ArchiveDomainService {

    public void validateArchive(Archive archive) {
        if (archive.getFileName() == null || archive.getFileName().isBlank()) {
            throw new IllegalArgumentException("File name cannot be empty");
        }
        if (archive.getDocumentId() == null || archive.getDocumentId().isBlank()) {
            throw new IllegalArgumentException("Document ID cannot be empty");
        }
        if (!archive.getFileName().toLowerCase().endsWith(".pdf")) {
            throw new IllegalArgumentException("Only PDF files are supported");
        }
    }

    public void validateCanBeRetrieved(Archive archive) {
        if (ArchiveStatus.EXPIRED.equals(archive.getStatus())) {
            throw new IllegalStateException("Archive has expired: " + archive.getId());
        }
    }
}
