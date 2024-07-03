package com.parkease.vehicle.domain;

import com.parkease.vehicle.infrastructure.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle findById(String id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public void delete(String id) {
        vehicleRepository.deleteById(id);
    }
}
