package com.hichamea.esign.archive_service.domain.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public final class ArchiveId {

    private final String value;

    private ArchiveId(String value) {
        this.value = value;
    }

    public static ArchiveId generate() {
        return new ArchiveId(UUID.randomUUID().toString());
    }

    public static ArchiveId of(String value) {
        Objects.requireNonNull(value, "ArchiveId cannot be null");
        return new ArchiveId(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArchiveId that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}

