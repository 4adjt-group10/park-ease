package com.parkease.parkingmeter.domain;

public enum ParkingZoneStatus {
    ACTIVE("Ativa"),
    INACTIVE("Inativa"),
    FULL("Lotada"),;

    private String description;

    ParkingZoneStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
