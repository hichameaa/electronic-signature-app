package com.hichamea.esign.archive_service.application.port.in;

import com.hichamea.esign.archive_service.domain.model.Archive;

public interface StoreDocumentPort {
    Archive store(String fileName, String documentId, byte[] pdfBytes);
}