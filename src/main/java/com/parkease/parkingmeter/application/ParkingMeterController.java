package com.parkease.parkingmeter.application;

import com.parkease.parkingmeter.domain.ParkingMeter;
import com.parkease.parkingmeter.domain.ParkingMeterService;
import com.parkease.payment.domain.PaymentMethod;
import com.parkease.paymentVoucher.application.VoucherDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<VoucherDTO> leavingVariableTime(@PathVariable String id) {
        return ResponseEntity.ok(parkingMeterService.leavingVariableTime(id));
    }

    @PostMapping("/leaving/{id}/fixed-time")
    public ResponseEntity<VoucherDTO> leaving (@PathVariable String id, @RequestBody Optional<PaymentMethod> paymentMethod) {
        return ResponseEntity.ok(parkingMeterService.leavingFixedTime(id, paymentMethod));
    }

    @GetMapping("/list-all")
    public ResponseEntity<List<ParkingMeterDTO>> listAllParkingMeters() {
        return ResponseEntity.ok(parkingMeterService.listAllParkingMeters());
    }
}
