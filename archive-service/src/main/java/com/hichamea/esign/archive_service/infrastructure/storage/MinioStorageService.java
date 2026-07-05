package com.hichamea.esign.archive_service.infrastructure.storage;

import com.hichamea.esign.archive_service.application.port.out.StoragePort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MinioStorageService implements StoragePort {

    private final S3Client s3Client;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Override
    public String store(String fileName, byte[] fileBytes, String contentType) {

        // Créer le bucket s'il n'existe pas
        createBucketIfNotExists();

        // Générer un nom unique pour éviter les collisions
        String objectKey = UUID.randomUUID() + "_" + fileName;

        s3Client.putObject(
                PutObjectRequest.builder()
                                .bucket(bucketName)
                                .key(objectKey)
                                .contentType(contentType)
                                .contentLength((long) fileBytes.length)
                                .build(),
                RequestBody.fromBytes(fileBytes)
        );

        return objectKey;
    }

    @Override
    public byte[] retrieve(String storagePath) {
        return s3Client.getObjectAsBytes(
                GetObjectRequest.builder()
                                .bucket(bucketName)
                                .key(storagePath)
                                .build()
        ).asByteArray();
    }

    @Override
    public void delete(String storagePath) {
        s3Client.deleteObject(
                DeleteObjectRequest.builder()
                                   .bucket(bucketName)
                                   .key(storagePath)
                                   .build()
        );
    }

    private void createBucketIfNotExists() {
        try {
            s3Client.headBucket(HeadBucketRequest.builder()
                                                 .bucket(bucketName)
                                                 .build());
        } catch (NoSuchBucketException e) {
            s3Client.createBucket(CreateBucketRequest.builder()
                                                     .bucket(bucketName)
                                                     .build());
        }
    }
}
