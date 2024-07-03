package com.parkease.vehicle.application;

import com.parkease.vehicle.domain.VehicleType;

public record VehicleFormDTO(String brand,
                             String model,
                             String color,
                             Integer year,
                             String licensePlate,
                             String driverId,
                             VehicleType type) {
}
