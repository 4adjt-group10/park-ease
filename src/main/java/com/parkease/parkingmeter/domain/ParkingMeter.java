package com.parkease.parkingmeter.domain;

import com.parkease.parkingmeter.application.ParkingMeterFormDTO;
import com.parkease.parkingmeter.application.ParkingMeterType;
import com.parkease.payment.domain.PaymentMethod;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Document
public class ParkingMeter {

    private String id;
    private String vehicleId;
    private String driverId;
    private ParkingMeterType type;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private BigDecimal price;
    private PaymentMethod paymentMethod;

    public ParkingMeter(String vehicleId,
                        String driverId,
                        ParkingMeterType type,
                        LocalDateTime startAt,
                        LocalDateTime endAt,
                        BigDecimal price,
                        PaymentMethod paymentMethod) {
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.type = type;
        this.startAt = startAt;
        this.endAt = endAt;
        this.price = price;
        this.paymentMethod = paymentMethod;
    }

    public ParkingMeter(ParkingMeterFormDTO formDTO, LocalDateTime startAt, LocalDateTime endAt, BigDecimal price) {
        this(formDTO.vehicleId(),
                formDTO.driverId(),
                formDTO.parkingMeterType(),
                startAt,
                endAt,
                price,
                formDTO.paymentMethod());
    }

    @Deprecated(since = "For frameworks only")
    public ParkingMeter() {
    }

    public String getId() {
        return id;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getDriverId() {
        return driverId;
    }

    public ParkingMeterType getType() {
        return type;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public boolean isFixedTime() {
        return type.equals(ParkingMeterType.FIXED_TIME);
    }

    public long getTotalHours(LocalDateTime start,LocalDateTime end) {
        long differenceInMinutes = ChronoUnit.MINUTES.between(start, end);
        return (long) Math.ceil(differenceInMinutes / 60.0);
    }

}
