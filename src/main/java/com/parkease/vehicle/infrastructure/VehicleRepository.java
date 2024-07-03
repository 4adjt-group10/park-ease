package com.parkease.vehicle.infrastructure;

import com.parkease.vehicle.domain.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {

}
