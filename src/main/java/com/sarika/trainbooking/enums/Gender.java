package com.sarika.trainbooking.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Gender {
    M("Male"),
    F("Female");

    private String value;

    @Override
    public String toString() {
        return value;
    }
}
