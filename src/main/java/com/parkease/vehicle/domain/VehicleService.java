package com.parkease.vehicle.domain;

import com.parkease.vehicle.infrastructure.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle findById(UUID id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public void delete(UUID id) {
        vehicleRepository.deleteById(id);
    }
}
