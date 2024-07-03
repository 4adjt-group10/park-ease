package com.parkease.vehicle.domain;

public enum VehicleType {
    SEDAN("Sedan"),
    SUV("SUV"),
    TRUCK("Caminhão"),
    MOTORCYCLE("Moto"),
    VAN("Vã"),
    BUS("Ônibus"),
    OTHER("Outro");

    private final String description;

    VehicleType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
