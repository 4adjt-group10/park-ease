package com.parkease.vehicle.domain;

public enum VehicleType {
    SEDAN("Sedan"),
    HATCHBACK("Hatchback"),
    SUV("SUV"),
    TRUCK("Truck"),
    MOTORCYCLE("Motorcycle"),
    VAN("Van"),
    BUS("Bus"),
    OTHER("Other");

    private final String description;

    VehicleType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
