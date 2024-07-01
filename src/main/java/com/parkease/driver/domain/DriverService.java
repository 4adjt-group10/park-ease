package com.parkease.driver.domain;

import com.parkease.driver.application.dto.DriverDTO;
import com.parkease.driver.application.dto.DriverFormDTO;
import com.parkease.driver.infrastructure.DriverRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private final AddressService addressService;

    public DriverService(DriverRepository driverRepository, AddressService addressService) {
        this.driverRepository = driverRepository;
        this.addressService = addressService;
    }

    @Transactional
    public DriverDTO createDriver(DriverFormDTO formDTO) {
        Address newAddress = addressService.createAddress(formDTO.address());
        Driver newDriver = driverRepository.save(new Driver(formDTO, newAddress));
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
}
