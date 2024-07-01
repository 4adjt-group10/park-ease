package com.parkease.payment.domain;

public enum PaymentMethod {
    PIX("Pix"),
    CREDIT_CARD("Cartão de crédito"),
    DEBIT_CARD("Cartão de débito");

    private final String description;

    PaymentMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
