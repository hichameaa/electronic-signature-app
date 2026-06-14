package com.hichamea.esign.signature_service.application.usecase;

import com.hichamea.esign.signature_service.application.port.in.SignDocumentPort;
import com.hichamea.esign.signature_service.application.port.out.DocumentRepositoryPort;
import com.hichamea.esign.signature_service.application.port.out.PdfSignerPort;
import com.hichamea.esign.signature_service.domain.model.Document;
import com.hichamea.esign.signature_service.domain.service.SignatureDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SignDocumentUseCase implements SignDocumentPort {

    private final SignatureDomainService domainService;
    private final DocumentRepositoryPort repositoryPort;
    private final PdfSignerPort pdfSigner;

    @Override
    public Document sign(String fileName, String signerName, byte[] pdfBytes) {

        // 1. Créer l'entité métier
        Document document = new Document(fileName, signerName);

        // 2. Valider via le domain service
        domainService.validateDocument(document);
        domainService.validateCanBeSigned(document);

        // 3. Signer le PDF
        byte[] signedPdf;
        try {
            signedPdf = pdfSigner.sign(pdfBytes, signerName);
        } catch (IOException e) {
            document.markAsFailed();
            repositoryPort.save(document);
            throw new RuntimeException("PDF signing failed: " + e.getMessage(), e);
        }

        // 4. Marquer comme signé
        document.markAsSigned();

        // 5. Sauvegarder
        return repositoryPort.save(document);
    }
}
