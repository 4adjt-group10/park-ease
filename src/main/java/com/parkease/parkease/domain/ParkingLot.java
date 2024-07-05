package com.parkease.parkease.domain;

import com.parkease.parkease.application.ParkeTypeEnum;
import com.parkease.parkease.application.ParkingLotFormDTO;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
public class ParkingLot {
    private String id;
    private String vehicleid;
    private String driverId;
    private ParkeTypeEnum type;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private BigDecimal price;

    public ParkingLot(String vehicleid, String driverId, ParkeTypeEnum type, LocalDateTime startAt, LocalDateTime endAt, BigDecimal price) {
        this.vehicleid = vehicleid;
        this.driverId = driverId;
        this.type = type;
        this.startAt = startAt;
        this.endAt = endAt;
        this.price = price;
    }



    public ParkingLot(ParkingLotFormDTO formDTO, LocalDateTime startAt, LocalDateTime endAt, BigDecimal price) {
        this(formDTO.vehicleId(),
                formDTO.driverId(),
                formDTO.parkeTypeEnum(),
                startAt,
                endAt,
                price);
    }




    public String getId() {
        return id;
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public String getDriverId() {
        return driverId;
    }

    public ParkeTypeEnum getType() {
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
