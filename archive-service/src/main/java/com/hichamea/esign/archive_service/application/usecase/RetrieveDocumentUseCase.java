package com.hichamea.esign.archive_service.application.usecase;

import com.hichamea.esign.archive_service.application.port.in.RetrieveDocumentPort;
import com.hichamea.esign.archive_service.application.port.out.ArchiveRepositoryPort;
import com.hichamea.esign.archive_service.domain.model.Archive;
import com.hichamea.esign.archive_service.domain.model.valueobject.ArchiveId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RetrieveDocumentUseCase implements RetrieveDocumentPort {

    private final ArchiveRepositoryPort repositoryPort;

    @Override
    public Archive retrieve(String archiveId) {
        return repositoryPort.findById(ArchiveId.of(archiveId))
                             .orElseThrow(() -> new IllegalArgumentException(
                                     "Archive not found: " + archiveId
                             ));
    }

    @Override
    public List<Archive> retrieveAll() {
        return repositoryPort.findAll();
    }
}
