package com.parkease.parkingmeter.domain;

import com.parkease.parkingmeter.application.ParkingZoneFormDTO;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.parkease.parkingmeter.domain.ParkingSpaceStatus.OCCUPIED;

@Document
public class ParkingZone {

    private String id;
    private String name;
    private String location;
    private BigDecimal price;
    private ParkingZoneStatus status;
    private List<ParkingSpace> parkingSpaces;

    @Deprecated(since = "Only for frameworks")
    public ParkingZone() {
    }

    public ParkingZone(String name, String location, BigDecimal price, ParkingZoneStatus status) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.status = status;
        this.parkingSpaces = new ArrayList<>();
    }

    public ParkingZone(ParkingZoneFormDTO formDTO) {
        this(formDTO.name(), formDTO.location(), formDTO.price(), formDTO.status());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public ParkingZoneStatus getStatus() {
        return status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<ParkingSpace> getParkingSpaces() {
        return parkingSpaces;
    }

    public void addParkingSpace(ParkingSpace parkingSpace) {
        parkingSpaces.add(parkingSpace);
    }

    public void removeParkingSpace(ParkingSpace parkingSpace) {
        parkingSpaces.remove(parkingSpace);
    }

    public boolean isFull() {
        return parkingSpaces.stream().allMatch(parkingSpace -> parkingSpace.getStatus() == OCCUPIED);
    }

    public ParkingSpace getAvailableSpace() {
        return parkingSpaces.stream()
                .filter(parkingSpace -> parkingSpace.getStatus() != OCCUPIED)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No available parking spaces"));
    }
}
