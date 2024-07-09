package com.parkease.parkingmeter.application;

import com.parkease.payment.domain.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public record ParkingMeterFormDTO(@NotBlank String driverId,
                                  @NotBlank String vehicleId,
                                  @NotNull ParkingMeterType parkingMeterType,
                                  Optional<Long> timeParking,
                                  @NotNull PaymentMethod paymentMethod
) {

    public boolean isFixedTime() {
        return parkingMeterType.equals(ParkingMeterType.FIXED_TIME);
    }

    public boolean hasValidTimeParking() {
        return timeParking.isPresent() && timeParking.get() > 0;
    }
}
