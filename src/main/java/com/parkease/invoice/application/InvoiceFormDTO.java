package com.parkease.invoice.application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record InvoiceFormDTO(
        @NotBlank String paymentId,
        @NotNull LocalDateTime creationDate
) {

}
