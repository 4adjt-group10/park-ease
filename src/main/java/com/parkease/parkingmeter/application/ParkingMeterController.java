package com.parkease.parkingmeter.application;

import com.parkease.parkingmeter.domain.ParkingMeterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/parking-meter")
public class ParkingMeterController {

    private final ParkingMeterService parkingMeterService;

    public ParkingMeterController(ParkingMeterService parkingMeterService) {
        this.parkingMeterService = parkingMeterService;
    }

    @PostMapping("/create")
    public ResponseEntity<ParkingMeterDTO> create(@RequestBody ParkingMeterFormDTO formDTO) {
        ParkingMeterDTO parkingMeterDTO = parkingMeterService.createParkingMeter(formDTO);
        return ResponseEntity.created(URI.create("/parking-meter/" + parkingMeterDTO.id())).body(parkingMeterDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingMeterDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(parkingMeterService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ParkingMeterDTO>> findAll() {
        return ResponseEntity.ok(parkingMeterService.listParkingMeters());
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ParkingMeterDTO> update(@PathVariable String id, @RequestBody ParkingMeterFormDTO formDTO) {
//        return parkingMeterService.update(id, formDTO)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable String id) {
//        if (parkingMeterService.delete(id)) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
}
