package com.parkease.parkease.application;

public record ParkingLotFormDTO(String driverId,
                                String vehicleId,
                                ParkeTypeEnum parkeTypeEnum,
                                Long timeParking
) {

}
