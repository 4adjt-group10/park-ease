package com.parkease.driver.application;

import com.parkease.driver.domain.DriverService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<DriverDTO>> listDrivers() {
        return ResponseEntity.ok(driverService.listDrivers());
    }

    @PostMapping("/create")
    public ResponseEntity<DriverDTO> createDriver(@RequestBody @Valid DriverFormDTO formDTO) {
        return ResponseEntity.ok(driverService.createDriver(formDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DriverDTO> updateDriver(@PathVariable String id, @RequestBody @Valid DriverUpdateDTO formDTO) {
        return ResponseEntity.ok(driverService.updateDriver(id, formDTO));
    }
}
