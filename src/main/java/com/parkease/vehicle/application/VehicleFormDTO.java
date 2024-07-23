package com.parkease.vehicle.application;

import com.parkease.vehicle.domain.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VehicleFormDTO(@NotBlank String brand,
                             @NotBlank String model,
                             @NotBlank String color,
                             @NotNull Integer year,
                             @NotBlank String licensePlate,
                             @NotBlank String driverId,
                             @NotNull VehicleType type) {
}
