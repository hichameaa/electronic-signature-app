package com.hichamea.esign.archive_service.domain.model;

import com.hichamea.esign.archive_service.domain.model.valueobject.ArchiveId;
import com.hichamea.esign.archive_service.domain.model.valueobject.ArchiveStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Archive {

    private final ArchiveId id;
    private String fileName;
    private String storagePath;
    private String documentId;
    private ArchiveStatus status;
    private final LocalDateTime createdAt;

    // Nouveau document à archiver
    public Archive(String fileName, String documentId) {
        this.id = ArchiveId.generate();
        this.fileName = fileName;
        this.documentId = documentId;
        this.status = ArchiveStatus.STORED;
        this.createdAt = LocalDateTime.now();
    }

    // Reconstruction depuis la base
    public Archive(ArchiveId id, String fileName, String storagePath,
                   String documentId, ArchiveStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.fileName = fileName;
        this.storagePath = storagePath;
        this.documentId = documentId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public void assignStoragePath(String path) {
        this.storagePath = path;
    }

    public void markAsRetrieved() {
        this.status = ArchiveStatus.RETRIEVED;
    }

    public void markAsExpired() {
        this.status = ArchiveStatus.EXPIRED;
    }

    public boolean isStored() {
        return ArchiveStatus.STORED.equals(this.status);
    }
}