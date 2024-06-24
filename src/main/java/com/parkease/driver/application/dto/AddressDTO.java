package com.parkease.driver.application.dto;

import com.parkease.driver.domain.Address;

public record AddressDTO(String id, String street, String city, String state, String zipCode) {

    public AddressDTO(Address address) {
        this(address.getId(), address.getStreet(), address.getCity(), address.getState(), address.getZipCode());
    }
}
