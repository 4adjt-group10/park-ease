package com.parkease.parkingmeter.application;

import com.parkease.parkingmeter.domain.ParkingSpace;
import com.parkease.parkingmeter.domain.ParkingSpaceStatus;

public record ParkingSpaceDTO(String id, String code, String parkingZoneId, ParkingSpaceStatus status) {

    public ParkingSpaceDTO(ParkingSpace parkingSpace) {
        this(parkingSpace.getId(), parkingSpace.getCode(), parkingSpace.getParkingZoneId(), parkingSpace.getStatus());
    }
}
