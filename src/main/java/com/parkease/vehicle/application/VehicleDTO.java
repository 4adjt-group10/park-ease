package com.parkease.vehicle.application;

import com.parkease.vehicle.domain.Vehicle;

public record VehicleDTO(String id,
                         String brand,
                         String model,
                         String color,
                         Integer year,
                         String licensePlate,
                         String driverId,
                         String type) {

    public VehicleDTO(Vehicle vehicle) {
        this(vehicle.getId(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getColor(),
                vehicle.getYear(),
                vehicle.getLicensePlate(),
                vehicle.getDriverId(),
                vehicle.getType().name());
    }
}
