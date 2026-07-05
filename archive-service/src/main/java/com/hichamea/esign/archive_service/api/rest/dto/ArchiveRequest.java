package com.hichamea.esign.archive_service.api.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArchiveRequest {

    @NotBlank(message = "Document ID is required")
    private String documentId;
}
