package com.parkease.parkingmeter.application;

import com.parkease.parkingmeter.domain.ParkingZoneStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ParkingZoneFormDTO(
        @NotBlank String name,
        @NotBlank String location,
        @NotNull @Min(0) BigDecimal price,
        @NotNull ParkingZoneStatus status) {
}
