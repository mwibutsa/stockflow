package com.mwibutsa.stockflow.po;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Locale;

public enum PoStatus {
    PENDING,
    APPROVED,
    RECEIVED,
    CANCELLED;

    @JsonCreator
    public static PoStatus fromString(String value) {
        if (value == null) return null;
        return PoStatus.valueOf(value.toUpperCase(Locale.ROOT));
    }


}
