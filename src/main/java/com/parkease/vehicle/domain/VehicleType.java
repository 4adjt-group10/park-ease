package com.parkease.vehicle.domain;

public enum VehicleType {
    SEDAN("Sedan"),
    SUV("SUV"),
    TRUCK("Truck"),
    MOTORCYCLE("Motorcycle"),
    VAN("Van"),
    BUS("Bus"),
    OTHER("Other");

    private String description;

    VehicleType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
