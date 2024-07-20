package com.parkease.parkingmeter.infrastructure;

import com.parkease.parkingmeter.application.ParkingMeterType;
import com.parkease.parkingmeter.domain.ParkingMeter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ParkingMeterRepository extends MongoRepository<ParkingMeter, String> {

    List<ParkingMeter> findAllByTypeAndEndAtBefore(ParkingMeterType type, LocalDateTime endAt);

    List<ParkingMeter> findAllByTypeAndEndAtAfter(ParkingMeterType type, LocalDateTime endAt);

    List<ParkingMeter> findAllByType(ParkingMeterType type);
}
