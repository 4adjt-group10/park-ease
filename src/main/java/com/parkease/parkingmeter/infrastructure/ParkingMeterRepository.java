package com.parkease.parkingmeter.infrastructure;

import com.parkease.parkingmeter.domain.ParkingMeter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParkingMeterRepository extends MongoRepository<ParkingMeter, String> {
}
