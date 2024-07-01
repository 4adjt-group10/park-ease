package com.parkease.payment.infrastructure;

import com.parkease.payment.domain.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PaymentRepository extends MongoRepository<Payment, String> {

    Optional<Payment> findFirstByDriverIdOrderByCreationDateDesc(String driverId);
}
