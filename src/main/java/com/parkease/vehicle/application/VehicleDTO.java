package com.parkease.vehicle.application;

import com.parkease.vehicle.domain.Vehicle;
import com.parkease.vehicle.domain.VehicleType;

public record VehicleDTO(String id,
                         String brand,
                         String model,
                         String color,
                         Integer year,
                         String licensePlate,
                         String driverId,
                         VehicleType type) {

    public VehicleDTO(Vehicle vehicle) {
        this(vehicle.getId(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getColor(),
                vehicle.getYear(),
                vehicle.getLicensePlate(),
                vehicle.getDriverId(),
                vehicle.getType());
    }
}
