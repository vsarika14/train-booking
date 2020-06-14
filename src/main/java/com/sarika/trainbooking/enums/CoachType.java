package com.sarika.trainbooking.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CoachType {
    FIRST("FIRST"),
    SECOND("SECOND"),
    THIRD("THIRD"),
    GENERAL("GENERAL");

    private String value;

    @Override
    public String toString() {
        return value;
    }
}
