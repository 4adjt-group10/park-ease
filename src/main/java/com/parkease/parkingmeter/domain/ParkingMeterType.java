package com.parkease.parkingmeter.domain;

public enum ParkingMeterType {
    FIXED_TIME("Tempo fixo"),
    PER_HOUR("Por hora");

    private final String description;

    ParkingMeterType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
