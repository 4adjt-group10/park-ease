package com.parkease.parkingmeter.infrastructure;

import com.parkease.parkingmeter.domain.ParkingZone;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParkingZoneRepository extends MongoRepository<ParkingZone, String> {
}
