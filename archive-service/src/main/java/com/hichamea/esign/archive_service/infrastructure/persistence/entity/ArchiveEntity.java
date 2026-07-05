package com.hichamea.esign.archive_service.infrastructure.persistence.entity;

import com.hichamea.esign.archive_service.domain.model.valueobject.ArchiveStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "archives")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArchiveEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "storage_path")
    private String storagePath;

    @Column(name = "document_id", nullable = false)
    private String documentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ArchiveStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}