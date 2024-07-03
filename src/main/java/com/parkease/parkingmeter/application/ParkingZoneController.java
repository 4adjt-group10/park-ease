package com.parkease.parkingmeter.application;

import com.parkease.parkingmeter.domain.ParkingZoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking-zone")
public class ParkingZoneController {

    private final ParkingZoneService parkingZoneService;

    public ParkingZoneController(ParkingZoneService parkingZoneService) {
        this.parkingZoneService = parkingZoneService;
    }

    @GetMapping("/list-all")
    public ResponseEntity<List<ParkingZoneDTO>> listAll() {
        return ResponseEntity.ok(parkingZoneService.getAllParkingZones());
    }

    @GetMapping("/parking-space/list-all")
    public ResponseEntity<List<ParkingSpaceDTO>> listAllParkingSpaces() {
        return ResponseEntity.ok(parkingZoneService.getAllParkingSpaces());
    }
}
