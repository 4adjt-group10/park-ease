package com.parkease.parkingmeter.application;

import com.parkease.parkingmeter.domain.ParkingMeter;
import com.parkease.payment.domain.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ParkingMeterDTO(
        String id,
        String vehicleId,
        String driverId,
        ParkingMeterType type,
        LocalDateTime startAt,
        LocalDateTime endAt,
        BigDecimal price,
        PaymentMethod paymentMethod
) {

    public ParkingMeterDTO(ParkingMeter parkingMeter) {
        this(parkingMeter.getId(),
                parkingMeter.getVehicleId(),
                parkingMeter.getDriverId(),
                parkingMeter.getType(),
                parkingMeter.getStartAt(),
                parkingMeter.getEndAt(),
                parkingMeter.getPrice(),
                parkingMeter.getPaymentMethod());
    }
}
