package com.hichamea.esign.archive_service.api.rest.dto;

import com.hichamea.esign.archive_service.domain.model.Archive;
import com.hichamea.esign.archive_service.domain.model.valueobject.ArchiveStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ArchiveResponse {

    private String id;
    private String fileName;
    private String storagePath;
    private String documentId;
    private ArchiveStatus status;
    private LocalDateTime createdAt;

    public static ArchiveResponse from(Archive archive) {
        return ArchiveResponse.builder()
                              .id(archive.getId().getValue())
                              .fileName(archive.getFileName())
                              .storagePath(archive.getStoragePath())
                              .documentId(archive.getDocumentId())
                              .status(archive.getStatus())
                              .createdAt(archive.getCreatedAt())
                              .build();
    }
}
