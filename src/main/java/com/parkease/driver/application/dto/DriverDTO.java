package com.parkease.driver.application.dto;

import com.parkease.driver.domain.Driver;

public record DriverDTO(String id, String name, String email, String phone, AddressDTO address) {

        public DriverDTO(Driver driver) {
            this(driver.getId(), driver.getFullName(), driver.getEmail(), driver.getPhone(), new AddressDTO(driver.getAddress()));
        }
}
