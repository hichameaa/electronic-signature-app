package com.hichamea.esign.signature_service.infrastructure.pdf;

import com.hichamea.esign.signature_service.application.port.out.PdfSignerPort;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class PdfSimpleSigner implements PdfSignerPort {

    public byte[] sign(byte[] pdfBytes, String signerName) throws IOException {

        // Validation
        if (pdfBytes == null || pdfBytes.length == 0) {
            throw new IllegalArgumentException("PDF content cannot be empty");
        }

        try (PDDocument document = Loader.loadPDF(pdfBytes)) {

            if (document.getNumberOfPages() == 0) {
                throw new IllegalArgumentException("PDF has no pages");
            }

            PDPage page = document.getPage(0);

            try (PDPageContentStream contentStream = new PDPageContentStream(
                    document, page, PDPageContentStream.AppendMode.APPEND, true)) {

                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
                contentStream.newLineAtOffset(50, 50);
                contentStream.showText("Signé par : " + signerName);
                contentStream.newLineAtOffset(0, -20);
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("Date : " + LocalDateTime.now()
                                                                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                contentStream.endText();
            }

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            document.save(output);
            return output.toByteArray();
        }
    }
}
