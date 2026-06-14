package com.hichamea.esign.signature_service.api.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignatureRequest {

    @NotBlank(message = "Signer name is required")
    private String signerName;
}
