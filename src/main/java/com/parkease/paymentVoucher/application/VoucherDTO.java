package com.parkease.paymentVoucher.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.parkease.paymentVoucher.domain.Voucher;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record VoucherDTO(
        UUID id,
        BigDecimal value,
        BigDecimal extraValue,
        LocalDateTime arrivedAt,
        LocalDateTime leftAt,
        String totalTime,
        BigDecimal currentPrice,
        BigDecimal extraCurrentPrice,
        BigDecimal totalPaid,
        String driverName,
        String vehiclePlate
) {

    public VoucherDTO(Voucher voucher, String driverName, String vehiclePlate) {
        this(voucher.getId(),
                voucher.getValue(),
                voucher.getExtraValue(),
                voucher.getArrivedAt(),
                voucher.getLeftAt(),
                voucher.getTime()+" hours",
                voucher.getCurrentPrice(),
                voucher.getExtraCurrentPrice(),
                voucher.getTotalPaid(),
                driverName,
                vehiclePlate);
    }
}
