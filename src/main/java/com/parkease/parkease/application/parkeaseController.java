package com.parkease.parkease.application;

import com.parkease.parkease.domain.ParkingLot;
import com.parkease.parkease.domain.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/park")
public class parkeaseController {
    @Autowired
    ParkingService parkingService;
    @PostMapping("/parklot")
    public ParkingLot parkinglotIn (@RequestBody ParkingLotFormDTO parkingLotDTO) {
        return parkingService.parkingLotIn(parkingLotDTO);
    }
}
