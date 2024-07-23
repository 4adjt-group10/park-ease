package com.parkease.driver.application;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DriverUpdateDTO(@NotBlank String firstName,
                              @NotBlank String lastName,
                              @Nullable String email,
                              @NotBlank String phone,
                              @NotNull AddressFormDTO address) {
}
