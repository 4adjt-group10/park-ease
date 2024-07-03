package com.parkease.parkingmeter.application;

import com.parkease.parkingmeter.domain.ParkingZone;
import com.parkease.parkingmeter.domain.ParkingZoneStatus;

import java.math.BigDecimal;
import java.util.List;

public record ParkingZoneDTO(
        String id,
        String name,
        String location,
        BigDecimal price,
        ParkingZoneStatus status,
        List<ParkingSpaceDTO> parkingSpaces
) {

    public ParkingZoneDTO(ParkingZone parkingZone) {
        this(parkingZone.getId(),
                parkingZone.getName(),
                parkingZone.getLocation(),
                parkingZone.getPrice(),
                parkingZone.getStatus(),
                parkingZone.getParkingSpaces().stream().map(ParkingSpaceDTO::new).toList());
    }
}
