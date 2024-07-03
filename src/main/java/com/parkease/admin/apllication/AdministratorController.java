package com.parkease.admin.apllication;

import com.parkease.parkingmeter.application.ParkingSpaceFormDTO;
import com.parkease.parkingmeter.application.ParkingZoneDTO;
import com.parkease.parkingmeter.application.ParkingZoneFormDTO;
import com.parkease.parkingmeter.domain.ParkingZoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/admin")
public class AdministratorController {

    private final ParkingZoneService parkingZoneService;

    public AdministratorController(ParkingZoneService parkingZoneService) {
        this.parkingZoneService = parkingZoneService;
    }

    @PostMapping("/parking-zone/create")
    public ResponseEntity<ParkingZoneDTO> createParkingZone(@RequestBody ParkingZoneFormDTO formDTO) {
        ParkingZoneDTO parkingZoneDTO = parkingZoneService.createParkingZone(formDTO);
        return ResponseEntity.created(URI.create("/parking-zone/" + parkingZoneDTO.id())).body(parkingZoneDTO);
    }

    @PostMapping("/parking-space/create/{zoneId}")
    public ResponseEntity<String> createParkingSpace(@RequestBody ParkingSpaceFormDTO formDTO,
                                                     @PathVariable String zoneId) {
        parkingZoneService.addParkingSpace(zoneId, formDTO);
        return ResponseEntity.ok().body("Parking space created successfully");
    }

}