package com.parkease.vehicle.domain;

import com.parkease.vehicle.application.VehicleFormDTO;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Vehicle {

    private String id;
    private String brand;
    private String model;
    private String color;
    private Integer year;
    private String licensePlate;
    private String driverId;
    private VehicleType type;

    public Vehicle(String brand,
                   String model,
                   String color,
                   Integer year,
                   String licensePlate,
                   String driverId,
                   VehicleType type) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.year = year;
        this.licensePlate = licensePlate;
        this.driverId = driverId;
        this.type = type;
    }

    public Vehicle(VehicleFormDTO formDTO) {
        this(formDTO.brand(),
                formDTO.model(),
                formDTO.color(),
                formDTO.year(),
                formDTO.licensePlate(),
                formDTO.driverId(),
                formDTO.type());
    }

    public Vehicle(VehicleFormDTO formDTO, String driverId) {
        this(formDTO);
        this.driverId = driverId;
    }

    @Deprecated(since = "Only for frameworks use")
    public Vehicle() {
    }

    public String getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Integer getYear() {
        return year;
    }

    public String getDriverId() {
        return driverId;
    }

    public VehicleType getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }
}
