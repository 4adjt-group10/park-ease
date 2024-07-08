package com.parkease.parkingmeter.application;

import java.util.Optional;

public record ParkingLotFormDTO(String driverId,
                                String vehicleId,
                                ParkingMeterType parkingMeterType,
                                Optional<Long> timeParking
) {

    public boolean isFixedTime() {
        return parkingMeterType.equals(ParkingMeterType.FIXED_TIME);
    }

    public boolean hasValidTimeParking() {
        return timeParking.isPresent() && timeParking.get() > 0;
    }
}
