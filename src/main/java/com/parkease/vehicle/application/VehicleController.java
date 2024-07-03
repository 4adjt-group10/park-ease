package com.parkease.vehicle.application;

import com.parkease.vehicle.domain.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/list-all")
    public ResponseEntity<List<VehicleDTO>> listAll() {
        return ResponseEntity.ok(vehicleService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<VehicleDTO> create(VehicleFormDTO formDTO) {
        return ResponseEntity.ok(vehicleService.save(formDTO));
    }
}
