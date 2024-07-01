package com.parkease.payment.application;

import com.parkease.driver.application.dto.DriverDTO;
import com.parkease.payment.domain.Payment;
import com.parkease.payment.domain.PaymentMethod;
import com.parkease.payment.domain.PaymentStatus;

import java.math.BigDecimal;

public record PaymentDTO(DriverDTO driver, BigDecimal amount, PaymentMethod paymentMethod, PaymentStatus status) {

    public PaymentDTO(Payment payment) {
        this(new DriverDTO(payment.getDriver()), payment.getAmount(), payment.getPaymentMethod(), payment.getStatus());
    }
}
