package com.parkease.paymentVoucher.infrastructure.repository;

import com.parkease.paymentVoucher.domain.service.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentVoucherRepository extends JpaRepository<Voucher, UUID> {
}
