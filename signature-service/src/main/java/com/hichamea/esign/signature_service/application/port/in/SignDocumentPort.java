package com.hichamea.esign.signature_service.application.port.in;

import com.hichamea.esign.signature_service.domain.model.Document;

public interface SignDocumentPort {
    Document sign(String fileName, String signerName, byte[] pdfBytes);
}
