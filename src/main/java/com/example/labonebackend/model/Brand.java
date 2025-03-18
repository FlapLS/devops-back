package com.example.labonebackend.model;

import com.fasterxml.jackson.annotation.JsonCreator;


public enum Brand {
    TAMIYA,
    MR_HOBBY,
    AMMO_MIG,
    AK_INTERACTIVE,
    ABTEILING_502;

    @JsonCreator
    public static Brand fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return Brand.valueOf(value.toUpperCase());
    }

}
