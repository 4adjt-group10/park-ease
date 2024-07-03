package com.parkease.parkingmeter.application;

import com.parkease.parkingmeter.domain.ParkingSpaceStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ParkingSpaceFormDTO(
        @NotBlank String code,
        @NotNull ParkingSpaceStatus status
) {
}
