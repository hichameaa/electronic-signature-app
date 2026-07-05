package com.hichamea.esign.archive_service.application.port.out;

public interface StoragePort {
    String store(String fileName, byte[] fileBytes, String contentType);
    byte[] retrieve(String storagePath);
    void delete(String storagePath);
}
