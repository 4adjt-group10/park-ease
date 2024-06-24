package com.parkease.driver.domain;

import com.parkease.driver.application.dto.DriverFormDTO;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Driver {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Address address;

    @Deprecated(since = "Only for frameworks use")
    public Driver() {
    }

    public Driver(String firstName, String lastName, String email, String phone, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Driver(DriverFormDTO formDTO, Address address) {
        this(formDTO.firstName(), formDTO.lastName(), formDTO.email(), formDTO.phone(), address);
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return "%s %s".formatted(firstName, lastName);
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
