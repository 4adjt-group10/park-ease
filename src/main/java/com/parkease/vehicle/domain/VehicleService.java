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

    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle findById(String id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));
    }

    public VehicleDTO getById(String id) {
        return new VehicleDTO(findById(id));
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public void delete(String id) {
        vehicleRepository.deleteById(id);
    }

    public void delete(Vehicle vehicle) {
        vehicleRepository.delete(vehicle);
    }

    public boolean existsById(String id) {
        return vehicleRepository.existsById(id);
    }

    public VehicleDTO update(String id, VehicleFormDTO formDTO) {
        Vehicle vehicle = findById(id);
        vehicle.merge(formDTO);
        return new VehicleDTO(vehicleRepository.save(vehicle));
    }
}
