package com.parkease.parkingmeter.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.parkease.parkingmeter.domain.ParkingMeter;
import com.parkease.payment.domain.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
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
