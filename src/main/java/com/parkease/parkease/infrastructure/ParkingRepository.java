package com.parkease.parkease.infrastructure;

import com.parkease.parkease.domain.ParkingLot;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParkingRepository extends MongoRepository<ParkingLot, String> {
}
