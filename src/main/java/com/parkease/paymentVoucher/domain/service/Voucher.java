package com.parkease.paymentVoucher.domain.service;

import com.parkease.parkingmeter.domain.ParkingMeter;
import com.parkease.payment.domain.Payment;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Voucher {
    @Id
    private UUID id;
    private BigDecimal value;
    private BigDecimal extraValue;
    private LocalDateTime arrivedAt;
    private LocalDateTime leftAt;
    private Long time;
    private BigDecimal currentPrice;
    private BigDecimal extraCurrentPrice;
    private String driverId;
    private String vehicleId;

    public Voucher() {
    }

    public Voucher(BigDecimal value, BigDecimal extraValue, LocalDateTime arrivedAt, LocalDateTime leftAt, Long time, BigDecimal currentPrice, String driverId, String vehicleId) {
        this.value = value;
        this.extraValue = extraValue;
        this.arrivedAt = arrivedAt;
        this.leftAt = leftAt;
        this.time = time;
        this.currentPrice = currentPrice;
        this.driverId = driverId;
        this.vehicleId = vehicleId;
    }

    public Voucher(Payment payment, ParkingMeter parkingMeter) {
        this(payment.getAmount(),
                new BigDecimal(0),
                parkingMeter.getStartAt(),
                parkingMeter.getEndAt(),
                parkingMeter.getTotalHours(parkingMeter.getStartAt(),parkingMeter.getEndAt()),
                parkingMeter.getPrice(),
                parkingMeter.getDriverId(),
                parkingMeter.getVehicleId());
    }

    //todo melhorar a logica para tempo fixo
    public Voucher(List<Payment> payment, ParkingMeter parkingMeter) {
        this(payment.stream().findFirst().get().getAmount(),
                payment.get(payment.size() - 1).getAmount(),
                parkingMeter.getStartAt(),
                parkingMeter.getEndAt(),
                parkingMeter.getTotalHours(parkingMeter.getStartAt(),parkingMeter.getEndAt()),
                parkingMeter.getPrice(),
                parkingMeter.getDriverId(),
                parkingMeter.getVehicleId());
    }


    public UUID getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public BigDecimal getExtraValue() {
        return extraValue;
    }

    public LocalDateTime getArrivedAt() {
        return arrivedAt;
    }

    public LocalDateTime getLeftAt() {
        return leftAt;
    }

    public Long getTime() {
        return time;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public BigDecimal getExtraCurrentPrice() {
        return extraCurrentPrice;
    }

    public String getDriverId() {
        return driverId;
    }

    public String getVehicleId() {
        return vehicleId;
    }
}
