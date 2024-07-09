package com.parkease.invoice.application;

import com.parkease.invoice.domain.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/list-all")
    public ResponseEntity<List<InvoiceDTO>> listAllInvoices() {
        return ResponseEntity.ok(invoiceService.listAllInvoices());
    }
}
