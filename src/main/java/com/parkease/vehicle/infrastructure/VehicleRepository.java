package com.parkease.vehicle.infrastructure;

import com.parkease.vehicle.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID>{

}
