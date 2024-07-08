package com.parkease.driver.domain;

import com.parkease.driver.application.DriverDTO;
import com.parkease.driver.application.DriverFormDTO;
import com.parkease.driver.infrastructure.DriverRepository;
import com.parkease.vehicle.application.VehicleDTO;
import com.parkease.vehicle.application.VehicleFormDTO;
import com.parkease.vehicle.domain.Vehicle;
import com.parkease.vehicle.domain.VehicleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private final AddressService addressService;
    private final VehicleService vehicleService;

    public DriverService(DriverRepository driverRepository,
                         AddressService addressService,
                         VehicleService vehicleService) {
        this.driverRepository = driverRepository;
        this.addressService = addressService;
        this.vehicleService = vehicleService;
    }

    @Transactional
    public DriverDTO createDriver(DriverFormDTO formDTO) {
        Address newAddress = addressService.createAddress(formDTO.address());
        Driver newDriver = driverRepository.save(new Driver(formDTO, newAddress));
        formDTO.vehicle().ifPresent(vehicleFormDTO -> {
            Vehicle vehicle = vehicleService.save(new Vehicle(vehicleFormDTO, newDriver.getId()));
            newDriver.addVehicle(vehicle);
            driverRepository.save(newDriver);
        });
        return new DriverDTO(newDriver);
    }

    public Driver findById(String id) {
        return driverRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void updateDriver(Driver driver) {
        driverRepository.save(driver);
    }

    public List<DriverDTO> listDrivers() {
        return driverRepository.findAll().stream().map(DriverDTO::new).toList();
    }

    public VehicleDTO addVehicle(VehicleFormDTO vehicleFormDTO) {
        Driver driver = findById(vehicleFormDTO.driverId());
        Vehicle vehicle = vehicleService.save(new Vehicle(vehicleFormDTO, driver.getId()));
        driver.addVehicle(vehicle);
        driverRepository.save(driver);
        return new VehicleDTO(vehicle);
    }

    @Transactional
    public void removeVehicle(String vehicleId) {
        Vehicle vehicle = vehicleService.findById(vehicleId);
        Driver driver = findById(vehicle.getDriverId());
        driver.removeVehicle(vehicle);
        vehicleService.delete(vehicle);
        driverRepository.save(driver);
    }

    public boolean existsById(String id) {
        return driverRepository.existsById(id);
    }
}
