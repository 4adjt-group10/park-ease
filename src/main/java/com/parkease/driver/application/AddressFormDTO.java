package com.parkease.driver.application;

import jakarta.validation.constraints.NotBlank;

public record AddressFormDTO(
        @NotBlank String address,
        @NotBlank String city,
        @NotBlank String state,
        @NotBlank String zipCode,
        @NotBlank String country
) {
}
