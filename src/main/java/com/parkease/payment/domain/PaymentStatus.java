package com.parkease.payment.domain;

public enum PaymentStatus {
    PENDING("Pendente"),
    PAID("Pago"),
    CANCELED("Cancelado");

    private final String description;

    PaymentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
