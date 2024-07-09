package com.parkease.admin.infrastructure;

import com.parkease.admin.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    Optional<Price> findByCurrentPriceTrue();
}
