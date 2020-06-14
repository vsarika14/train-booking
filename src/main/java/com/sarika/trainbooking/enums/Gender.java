package com.sarika.trainbooking.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Gender {
    Male("Male"),
    Female("Female");

    private String value;

    @Override
    public String toString() {
        return value;
    }
}
