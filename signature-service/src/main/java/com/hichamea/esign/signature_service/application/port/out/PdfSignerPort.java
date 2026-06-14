package com.hichamea.esign.signature_service.application.port.out;

import java.io.IOException;

public interface PdfSignerPort {
    byte[] sign(byte[] pdfBytes, String signerName) throws IOException;
}
