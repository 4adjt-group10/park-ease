package com.parkease.driver.application;

import com.parkease.driver.domain.Driver;
import com.parkease.vehicle.application.VehicleDTO;

import java.util.List;

public record DriverDTO(String id, String name, String email, String phone, AddressDTO address, List<VehicleDTO> vehicles) {

        public DriverDTO(Driver driver) {
            this(driver.getId(),
                    driver.getFullName(),
                    driver.getEmail(),
                    driver.getPhone(),
                    new AddressDTO(driver.getAddress()),
                    driver.getVehicles().stream().map(VehicleDTO::new).toList());
        }
}
