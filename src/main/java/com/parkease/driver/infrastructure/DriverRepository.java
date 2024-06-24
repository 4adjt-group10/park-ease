package com.parkease.driver.infrastructure;

import com.parkease.driver.domain.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DriverRepository extends MongoRepository<Driver, String> {

}
