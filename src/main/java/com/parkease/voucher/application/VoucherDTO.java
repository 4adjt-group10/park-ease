package com.parkease.voucher.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.parkease.voucher.domain.Voucher;
import org.springframework.lang.Nullable;

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
        @Nullable String driverName,
        @Nullable String vehiclePlate,
        @Nullable String driverId,
        @Nullable String vehicleId
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
                vehiclePlate,
                null,
                null);
    }

    public VoucherDTO(Voucher voucher) {
        this(voucher.getId(),
                voucher.getValue(),
                voucher.getExtraValue(),
                voucher.getArrivedAt(),
                voucher.getLeftAt(),
                voucher.getTime()+" hours",
                voucher.getCurrentPrice(),
                voucher.getExtraCurrentPrice(),
                voucher.getTotalPaid(),
                null,
                null,
                voucher.getDriverId(),
                voucher.getVehicleId());
    }
}
