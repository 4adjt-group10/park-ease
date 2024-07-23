package com.parkease.vehicle.application;

import com.parkease.driver.domain.DriverService;
import com.parkease.vehicle.domain.Vehicle;
import com.parkease.vehicle.domain.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;
    private final DriverService driverService;

    public VehicleController(VehicleService vehicleService, DriverService driverService) {
        this.vehicleService = vehicleService;
        this.driverService = driverService;
    }

    @GetMapping("/list-all")
    public ResponseEntity<List<Vehicle>> listAll() {
        return ResponseEntity.ok(vehicleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(vehicleService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<VehicleDTO> create(VehicleFormDTO formDTO) {
        VehicleDTO vehicleDTO = driverService.addVehicle(formDTO);
        return ResponseEntity.created(URI.create("/vehicle/" + vehicleDTO.id())).body(vehicleDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VehicleDTO> update(@PathVariable String id, VehicleFormDTO formDTO) {
        return ResponseEntity.ok(driverService.updateVehicle(id, formDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        driverService.removeVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
