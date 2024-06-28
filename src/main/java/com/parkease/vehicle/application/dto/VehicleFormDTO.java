package com.parkease.vehicle.application.dto;

import com.parkease.vehicle.domain.VehicleType;

import java.time.LocalDate;

public record VehicleFormDTO(String brand,
                             String model,
                             String color,
                             LocalDate year,
                             String licensePlate,
                             String driverId,
                             VehicleType type) {
}
