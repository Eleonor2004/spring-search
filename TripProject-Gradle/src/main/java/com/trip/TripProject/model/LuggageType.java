package com.trip.TripProject.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.trip.TripProject.helper.TripType;

public enum LuggageType {
    ANIMAL("animal"),
    BED("bed");

    private final String value;

    LuggageType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static TripType fromValue(String value) {
        for (TripType type : TripType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown enum type " + value);
    }
}
