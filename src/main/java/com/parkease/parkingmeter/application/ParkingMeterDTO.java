package com.parkease.parkingmeter.application;

import com.parkease.parkingmeter.domain.ParkingMeter;
import com.parkease.parkingmeter.domain.ParkingMeterType;

import java.time.LocalDateTime;

public record ParkingMeterDTO(
        String id,
        ParkingSpaceDTO parkingSpace,
        ParkingMeterType type,
        LocalDateTime startsAt,
        LocalDateTime endsAt,
        String driverId,
        String vehicleId) {

    public ParkingMeterDTO(ParkingMeter parkingMeter) {
        this(parkingMeter.getId(),
                new ParkingSpaceDTO(parkingMeter.getParkingSpace()),
                parkingMeter.getType(),
                parkingMeter.getStartsAt(),
                parkingMeter.getEndsAt().orElse(null),
                parkingMeter.getDriverId(),
                parkingMeter.getVehicleId());
    }
}
