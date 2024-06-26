package com.parkease.vehicle.domain;

import com.parkease.vehicle.application.dto.VehicleFormDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Vehicle {

    @Id
    private UUID id;
    private String brand;
    private String model;
    private String color;
    private LocalDate year;
    private String licensePlate;
    private String driverId;
    private VehicleType type;

    public Vehicle(String brand,
                   String model,
                   String color,
                   LocalDate year,
                   String licensePlate,
                   String driverId,
                   VehicleType type) {
        this.id = UUID.randomUUID();
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

    @Deprecated(since = "Only for frameworks use")
    public Vehicle() {
    }

    public UUID getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public LocalDate getYear() {
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
