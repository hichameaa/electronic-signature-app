package com.hichamea.esign.archive_service.api.rest;

import com.hichamea.esign.archive_service.api.rest.dto.ArchiveRequest;
import com.hichamea.esign.archive_service.api.rest.dto.ArchiveResponse;
import com.hichamea.esign.archive_service.application.port.in.RetrieveDocumentPort;
import com.hichamea.esign.archive_service.application.port.in.StoreDocumentPort;
import com.hichamea.esign.archive_service.domain.model.Archive;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/archives")
@RequiredArgsConstructor
public class ArchiveController {

    private final StoreDocumentPort storeDocumentPort;
    private final RetrieveDocumentPort retrieveDocumentPort;

    @PostMapping("/store")
    public ResponseEntity<ArchiveResponse> store(
            @RequestPart("file") MultipartFile file,
            @RequestPart("request") @Valid ArchiveRequest request
    ) throws IOException {

        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        Archive archive = storeDocumentPort.store(
                file.getOriginalFilename(),
                request.getDocumentId(),
                file.getBytes()
        );

        return ResponseEntity.ok(ArchiveResponse.from(archive));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArchiveResponse> retrieve(@PathVariable String id) {
        Archive archive = retrieveDocumentPort.retrieve(id);
        return ResponseEntity.ok(ArchiveResponse.from(archive));
    }

    @GetMapping
    public ResponseEntity<List<ArchiveResponse>> retrieveAll() {
        List<ArchiveResponse> responses = retrieveDocumentPort.retrieveAll()
                                                              .stream()
                                                              .map(ArchiveResponse::from)
                                                              .toList();
        return ResponseEntity.ok(responses);
    }
}

