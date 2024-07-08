package com.parkease.parkingmeter.domain;

import com.parkease.parkingmeter.application.ParkingMeterType;
import com.parkease.parkingmeter.application.ParkingLotFormDTO;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
public class ParkingMeter {

    private String id;
    private String vehicleId;
    private String driverId;
    private ParkingMeterType type;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private BigDecimal price;

    public ParkingMeter(String vehicleId,
                        String driverId,
                        ParkingMeterType type,
                        LocalDateTime startAt,
                        LocalDateTime endAt,
                        BigDecimal price) {
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.type = type;
        this.startAt = startAt;
        this.endAt = endAt;
        this.price = price;
    }



    public ParkingMeter(ParkingLotFormDTO formDTO, LocalDateTime startAt, LocalDateTime endAt, BigDecimal price) {
        this(formDTO.vehicleId(),
                formDTO.driverId(),
                formDTO.parkingMeterType(),
                startAt,
                endAt,
                price);
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
}
