package com.parkease.parkingmeter.infrastructure;

import com.parkease.parkingmeter.domain.ParkingSpace;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParkingSpaceRepository extends MongoRepository<ParkingSpace, String>{
}
