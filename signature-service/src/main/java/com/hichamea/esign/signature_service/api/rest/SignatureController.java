package com.hichamea.esign.signature_service.api.rest;

import com.hichamea.esign.signature_service.api.rest.dto.SignatureRequest;
import com.hichamea.esign.signature_service.api.rest.dto.SignatureResponse;
import com.hichamea.esign.signature_service.application.port.in.SignDocumentPort;
import com.hichamea.esign.signature_service.domain.model.Document;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/signatures")
@RequiredArgsConstructor
public class SignatureController {

    private final SignDocumentPort signDocumentPort;

    @PostMapping("/sign")
    public ResponseEntity<SignatureResponse> sign(
            @RequestPart("file") MultipartFile file,
            @RequestPart("request") @Valid SignatureRequest request
    ) throws IOException {
        Document document = signDocumentPort.sign(
                file.getOriginalFilename(),
                request.getSignerName(),
                file.getBytes()
        );

        return ResponseEntity.ok(SignatureResponse.from(document));
    }
}
