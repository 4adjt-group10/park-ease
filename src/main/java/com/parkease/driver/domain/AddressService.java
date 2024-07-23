package com.parkease.driver.domain;

import com.parkease.driver.application.AddressFormDTO;
import com.parkease.driver.infrastructure.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address createAddress(AddressFormDTO formDTO) {
        return addressRepository.save(new Address(formDTO));
    }

    public Address getById(String id) {
        return addressRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void updateAddress(Address address, AddressFormDTO formDTO) {
        address.merge(formDTO);
        addressRepository.save(address);
    }
}
