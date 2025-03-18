package com.example.labonebackend.model;

import com.fasterxml.jackson.annotation.JsonCreator;


public enum TypeColor {
    ENEMAL,
    ACRYLIC,
    OIL;

    @JsonCreator
    public static TypeColor fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return TypeColor.valueOf(value.toUpperCase());
    }

}
