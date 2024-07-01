package com.parkease.payment.application;

import com.parkease.payment.domain.PaymentMethod;
import com.parkease.payment.domain.PaymentStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PaymentFormDTO(
        @NotBlank String driverId,
        @NotNull @Min(0) BigDecimal amount,
        @NotNull PaymentMethod paymentMethod,
        @NotNull PaymentStatus status
) {
}
