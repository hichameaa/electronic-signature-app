package com.hichamea.esign.archive_service.application.usecase;


import com.hichamea.esign.archive_service.application.port.in.StoreDocumentPort;
import com.hichamea.esign.archive_service.application.port.out.ArchiveRepositoryPort;
import com.hichamea.esign.archive_service.application.port.out.StoragePort;
import com.hichamea.esign.archive_service.domain.model.Archive;
import com.hichamea.esign.archive_service.domain.service.ArchiveDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreDocumentUseCase implements StoreDocumentPort {

    private final ArchiveDomainService domainService;
    private final ArchiveRepositoryPort repositoryPort;
    private final StoragePort storagePort;

    @Override
    public Archive store(String fileName, String documentId, byte[] pdfBytes) {

        // 1. Créer l'entité métier
        Archive archive = new Archive(fileName, documentId);

        // 2. Valider via le domain service
        domainService.validateArchive(archive);

        // 3. Stocker dans MinIO
        String storagePath = storagePort.store(fileName, pdfBytes, "application/pdf");

        // 4. Assigner le chemin de stockage
        archive.assignStoragePath(storagePath);

        // 5. Sauvegarder les métadonnées
        return repositoryPort.save(archive);
    }
}
