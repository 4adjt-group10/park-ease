package com.parkease.parkingmeter.application;

import com.parkease.parkingmeter.domain.ParkingMeter;
import com.parkease.parkingmeter.domain.ParkingMeterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/park")
public class ParkingMeterController {

    private final ParkingMeterService parkingMeterService;

    public ParkingMeterController(ParkingMeterService parkingMeterService) {
        this.parkingMeterService = parkingMeterService;
    }

    @PostMapping("/parklot")
    public ParkingMeter parkinglotIn (@RequestBody ParkingLotFormDTO parkingLotDTO) {
        return parkingMeterService.parkingLotIn(parkingLotDTO);
    }
}
