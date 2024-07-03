package com.parkease.parkingmeter.application;

import com.parkease.parkingmeter.domain.ParkingMeterType;

import java.time.LocalDateTime;
import java.util.Optional;

public record ParkingMeterFormDTO(
        String parkingSpaceId,
        ParkingMeterType type,
        LocalDateTime startsAt,
        Optional<LocalDateTime> endsAt,
        String driverId,
        String vehicleId
) {
}
