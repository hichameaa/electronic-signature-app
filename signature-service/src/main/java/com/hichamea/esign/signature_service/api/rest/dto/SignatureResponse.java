package com.hichamea.esign.signature_service.api.rest.dto;

import com.hichamea.esign.signature_service.domain.model.Document;
import com.hichamea.esign.signature_service.domain.model.valueobject.SignatureStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SignatureResponse {

    private String id;
    private String fileName;
    private String signerName;
    private SignatureStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime signedAt;

    public static SignatureResponse from(Document document) {
        return SignatureResponse.builder()
                                .id(document.getId().getValue())
                                .fileName(document.getFileName())
                                .signerName(document.getSignerName())
                                .status(document.getStatus())
                                .createdAt(document.getCreatedAt())
                                .signedAt(document.getSignedAt())
                                .build();
    }
}
