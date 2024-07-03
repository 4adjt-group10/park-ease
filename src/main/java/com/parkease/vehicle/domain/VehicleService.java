package com.parkease.vehicle.domain;

import com.parkease.vehicle.application.VehicleDTO;
import com.parkease.vehicle.application.VehicleFormDTO;
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

    public VehicleDTO save(VehicleFormDTO formDTO) {
        Vehicle vehicle = vehicleRepository.save(new Vehicle(formDTO));
        return new VehicleDTO(vehicle);
    }

    public Vehicle findById(String id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));
    }

    public List<VehicleDTO> findAll() {
        return vehicleRepository.findAll().stream().map(VehicleDTO::new).toList();
    }

    public void delete(String id) {
        vehicleRepository.deleteById(id);
    }
}
