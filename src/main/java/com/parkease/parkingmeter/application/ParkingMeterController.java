package com.parkease.parkingmeter.application;

import com.parkease.parkingmeter.domain.ParkingMeter;
import com.parkease.parkingmeter.domain.ParkingMeterService;
import com.parkease.payment.domain.PaymentMethod;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/parking-meter")
public class ParkingMeterController {

    private final ParkingMeterService parkingMeterService;

    public ParkingMeterController(ParkingMeterService parkingMeterService) {
        this.parkingMeterService = parkingMeterService;
    }

    @PostMapping("/arriving")
    public ParkingMeter parking(@RequestBody @Valid ParkingMeterFormDTO parkingLotDTO) {
        return parkingMeterService.arrivingParkingLot(parkingLotDTO);
    }

    @PostMapping("/leaving/{id}/variable-time")
    public ResponseEntity leavingVariableTime(@PathVariable String id) {
        parkingMeterService.leavingVariableTime(id);
        return ResponseEntity.status(OK).build();
    }

    @PostMapping("/leaving/{id}/fixed-time")
    public ResponseEntity leaving (@PathVariable String id, @RequestBody Optional<PaymentMethod> paymentMethod) {
        parkingMeterService.leavingFixedTime(id, paymentMethod);
        return ResponseEntity.status(OK).build();
    }

    @GetMapping("/list-all")
    public ResponseEntity listAllParkingMeters() {
        return ResponseEntity.ok(parkingMeterService.listAllParkingMeters());
    }
}
