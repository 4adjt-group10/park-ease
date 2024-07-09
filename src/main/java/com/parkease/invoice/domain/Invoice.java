package com.parkease.invoice.domain;

import com.parkease.payment.domain.Payment;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Invoice {

    private String id;
    private Payment payment;
    private LocalDateTime creationDate;
    private LocalDateTime processingDate;

    public Invoice(Payment payment, LocalDateTime creationDate) {
        this.payment = payment;
        this.creationDate = creationDate;
        this.processingDate = processingDate;
    }

    public String getId() {
        return id;
    }

    public Payment getPayment() {
        return payment;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getProcessingDate() {
        return processingDate;
    }

    public void processed() {
        this.processingDate = LocalDateTime.now();
    }
}
