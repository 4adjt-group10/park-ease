package com.parkease.payment.domain;

import com.parkease.driver.domain.Driver;
import com.parkease.payment.application.PaymentFormDTO;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
public class Payment {

    private String id;
    private Driver driver;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private LocalDateTime creationDate;

    public Payment(Driver driver, BigDecimal amount, PaymentMethod paymentMethod, PaymentStatus status) {
        this.driver = driver;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.creationDate = LocalDateTime.now();
    }

    public Payment(Driver driver, PaymentFormDTO formDTO) {
        this(driver, formDTO.amount(), formDTO.paymentMethod(), formDTO.status());
    }

    @Deprecated(since = "For frameworks only")
    public Payment() {
    }

    public String getId() {
        return id;
    }

    public Driver getDriver() {
        return driver;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
