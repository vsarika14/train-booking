package com.sarika.trainbooking.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CustomerPreference {
    ALL_TRAVELLERS_SAME_COACH("ALL_TRAVELLERS_SAME_COACH");

    private String value;

    @Override
    public String toString() {
        return value;
    }
}
