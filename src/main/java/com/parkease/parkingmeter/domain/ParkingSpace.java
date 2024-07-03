package com.parkease.parkingmeter.domain;

import com.parkease.parkingmeter.application.ParkingSpaceFormDTO;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ParkingSpace {

    private String id;
    private String code;
    private String parkingZoneId;
    private ParkingSpaceStatus status;

    @Deprecated(since = "Only for frameworks")
    public ParkingSpace() {
    }

    public ParkingSpace(String code, String parkingZoneId, ParkingSpaceStatus status) {
        this.code = code;
        this.parkingZoneId = parkingZoneId;
        this.status = status;
    }

    public ParkingSpace(ParkingSpaceFormDTO formDTO, String parkingZoneId) {
        this(formDTO.code(), parkingZoneId, formDTO.status());
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getParkingZoneId() {
        return parkingZoneId;
    }

    public ParkingSpaceStatus getStatus() {
        return status;
    }
}
