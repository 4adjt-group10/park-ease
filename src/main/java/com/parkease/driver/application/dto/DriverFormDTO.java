package com.parkease.driver.application.dto;

public record DriverFormDTO(String firstName, String lastName, String email, String phone, AddressFormDTO address) {
}
