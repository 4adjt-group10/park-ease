package com.parkease.driver.infrastructure;

import com.parkease.driver.domain.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String>{

}
