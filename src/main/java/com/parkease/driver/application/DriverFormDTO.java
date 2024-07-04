package com.parkease.driver.application;

import com.parkease.vehicle.application.VehicleFormDTO;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public record DriverFormDTO(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @Nullable String email,
        @NotBlank String phone,
        @NotNull AddressFormDTO address,
        Optional<VehicleFormDTO> vehicle
) {
}
