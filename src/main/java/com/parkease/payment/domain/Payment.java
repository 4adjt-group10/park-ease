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
    private LocalDateTime creationDate;

    public Payment(Driver driver, BigDecimal amount, PaymentMethod paymentMethod) {
        this.driver = driver;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.creationDate = LocalDateTime.now();
    }

    public Payment(Driver driver, PaymentFormDTO formDTO) {
        this(driver, formDTO.amount(), formDTO.paymentMethod());
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

    public String getDriverId() {
        return driver.getId();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

}
