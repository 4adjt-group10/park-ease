package com.parkease.invoice.application;

import com.parkease.invoice.domain.Invoice;
import com.parkease.payment.application.PaymentDTO;

import java.time.LocalDateTime;

public record InvoiceDTO(
        String id,
        PaymentDTO payment,
        LocalDateTime creationDate,
        LocalDateTime processingDate
) {

    public InvoiceDTO(Invoice invoice) {
        this(invoice.getId(), new PaymentDTO(invoice.getPayment()), invoice.getCreationDate(), invoice.getProcessingDate());
    }
}
