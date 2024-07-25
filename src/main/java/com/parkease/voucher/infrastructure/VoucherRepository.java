package com.parkease.voucher.infrastructure;

import com.parkease.voucher.domain.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public interface VoucherRepository extends JpaRepository<Voucher, UUID> {

    Page<Voucher> findAll(Pageable pageable);

    Page<Voucher> findAllByDriverId(Pageable pageable, String driverId);

    Page<Voucher> findAllByArrivedAtBetween(Pageable pageable, LocalDateTime start, LocalDateTime end);
}
