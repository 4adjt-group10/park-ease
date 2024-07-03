package com.parkease.parkingmeter.domain;

public enum ParkingSpaceStatus {
    OCCUPIED("Ocupada"),
    FREE("Livre");

    private final String description;

    ParkingSpaceStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
