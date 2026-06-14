package com.hichamea.esign.signature_service.infrastructure.persistence.entity;

import com.hichamea.esign.signature_service.domain.model.valueobject.SignatureStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "documents")
public class DocumentEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "signer_name", nullable = false)
    private String signerName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SignatureStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "signed_at")
    private LocalDateTime signedAt;
}
