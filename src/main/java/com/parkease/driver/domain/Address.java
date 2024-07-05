package com.parkease.driver.domain;

import com.parkease.driver.application.AddressFormDTO;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Address {

    private String id;
    private String street;
    private String city;
    private String state;
    private String zipCode;

    @Deprecated(since = "Only for frameworks use")
    public Address() {
    }

    public Address(String street, String city, String state, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public Address(AddressFormDTO formDTO) {
        this(formDTO.address(), formDTO.city(), formDTO.state(), formDTO.zipCode());
    }

    public String getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }
}
