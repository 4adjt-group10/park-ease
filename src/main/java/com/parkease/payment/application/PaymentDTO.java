package com.parkease.payment.application;

import com.parkease.driver.application.DriverDTO;
import com.parkease.payment.domain.Payment;
import com.parkease.payment.domain.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentDTO(String id, DriverDTO driver, BigDecimal amount, PaymentMethod paymentMethod, LocalDateTime creationDate) {

    public PaymentDTO(Payment payment) {
        this(payment.getId(),
                new DriverDTO(payment.getDriver()),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getCreationDate());
    }
}
