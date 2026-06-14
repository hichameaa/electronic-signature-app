package com.hichamea.esign.signature_service.domain.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public final class DocumentId {

    private final String value;

    private DocumentId(String value) {
        this.value = value;
    }

    public static DocumentId generate() {
        return new DocumentId(UUID.randomUUID().toString());
    }

    public static DocumentId of(String value) {
        Objects.requireNonNull(value, "DocumentId cannot be null");
        return new DocumentId(value);
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DocumentId that)) return false;
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
