package com.hichamea.esign.archive_service.application.port.in;

import com.hichamea.esign.archive_service.domain.model.Archive;

public interface RetrieveDocumentPort {
    Archive retrieve(String archiveId);
    java.util.List<Archive> retrieveAll();
}
