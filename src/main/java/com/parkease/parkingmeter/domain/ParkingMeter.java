package com.parkease.parkingmeter.domain;

import com.parkease.driver.domain.Driver;
import com.parkease.parkingmeter.application.ParkingMeterFormDTO;
import com.parkease.vehicle.domain.Vehicle;
import jakarta.annotation.Nullable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Document
public class ParkingMeter {

    private String id;
    private ParkingSpace parkingSpace;
    private ParkingMeterType type;
    private LocalDateTime startsAt;
    private LocalDateTime endsAt;
    private Driver driver;
    private Vehicle vehicle;

    @Deprecated(since = "Only for frameworks")
    public ParkingMeter() {
    }

    public ParkingMeter(ParkingSpace parkingSpace,
                        ParkingMeterType type,
                        LocalDateTime startsAt,
                        @Nullable LocalDateTime endsAt,
                        Driver driver,
                        Vehicle vehicle) {
        this.parkingSpace = parkingSpace;
        this.type = type;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.driver = driver;
        this.vehicle = vehicle;
    }

    public ParkingMeter(ParkingSpace parkingSpace, ParkingMeterFormDTO formDTO, Driver driver, Vehicle vehicle) {
        this(parkingSpace, formDTO.type(), formDTO.startsAt(), formDTO.endsAt().orElse(null), driver, vehicle);
    }

    public String getId() {
        return id;
    }

    public String getParkingZone() {
        return parkingSpace.getParkingZoneId();
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public ParkingMeterType getType() {
        return type;
    }

    public LocalDateTime getStartsAt() {
        return startsAt;
    }

    public Optional<LocalDateTime> getEndsAt() {
        return Optional.ofNullable(endsAt);
    }

    public long getDurationInHours() {
        return getEndsAt().map(end -> startsAt.until(end, ChronoUnit.HOURS)).orElse(0L);
    }

    public Driver getDriver() {
        return driver;
    }

    public String getDriverId() {
        return driver.getId();
    }

    public String getVehicleId() {
        return vehicle.getId();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

}
