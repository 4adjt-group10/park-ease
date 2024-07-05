package com.parkease.driver.domain;

import com.parkease.driver.application.DriverFormDTO;
import com.parkease.vehicle.domain.Vehicle;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Driver {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Address address;
    private List<Vehicle> vehicles;

    @Deprecated(since = "Only for frameworks use")
    public Driver() {
    }

    public Driver(String firstName, String lastName, String email, String phone, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.vehicles = new ArrayList<>();
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

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        this.vehicles.removeIf(v -> v.getId().equals(vehicle.getId()));
    }
}
