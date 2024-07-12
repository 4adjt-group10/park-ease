package com.parkease.paymentVoucher.infrastructure;

import com.parkease.paymentVoucher.domain.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentVoucherRepository extends JpaRepository<Voucher, UUID> {
}
