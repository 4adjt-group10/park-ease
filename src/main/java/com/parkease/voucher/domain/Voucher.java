package com.parkease.voucher.domain;

import com.parkease.parkingmeter.domain.ParkingMeter;
import com.parkease.payment.domain.Payment;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.math.BigDecimal.ZERO;
import static java.time.LocalDateTime.now;

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

    @Deprecated(since = "Only for frameworks")
    public Voucher() {
    }

    public Voucher(BigDecimal value,
                   BigDecimal extraValue,
                   LocalDateTime arrivedAt,
                   LocalDateTime leftAt,
                   Long time,
                   BigDecimal currentPrice,
                   BigDecimal extraCurrentPrice,
                   String driverId,
                   String vehicleId) {
        this.id = UUID.randomUUID();
        this.value = value;
        this.extraValue = extraValue;
        this.arrivedAt = arrivedAt;
        this.leftAt = leftAt;
        this.time = time;
        this.currentPrice = currentPrice;
        this.extraCurrentPrice = extraCurrentPrice;
        this.driverId = driverId;
        this.vehicleId = vehicleId;
    }

    public Voucher(Payment payment, ParkingMeter parkingMeter) {
        this(payment.getAmount(),
                ZERO,
                parkingMeter.getStartAt(),
                now(),
                parkingMeter.getTotalHours(parkingMeter.getStartAt(), now()),
                parkingMeter.getPrice(),
                ZERO,
                parkingMeter.getDriverId(),
                parkingMeter.getVehicleId());
    }

    public Voucher(List<Payment> payments,
                   ParkingMeter parkingMeter,
                   BigDecimal extraCurrentPrice,
                   LocalDateTime extraLeft) {

        this(payments.stream().findFirst().get().getAmount(),
                payments.get(payments.size() - 1).getAmount(),
                parkingMeter.getStartAt(),
                extraLeft,
                parkingMeter.getTotalHours(parkingMeter.getStartAt(), extraLeft),
                parkingMeter.getPrice(),
                extraCurrentPrice,
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

    public BigDecimal getTotalPaid() {
        return value.add(extraValue);
    }

    public String getTimeToString() {
        return time + " hour".concat(time > 1L ? "s" : "");
    }
}
