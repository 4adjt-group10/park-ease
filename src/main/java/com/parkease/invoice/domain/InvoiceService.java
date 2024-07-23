package com.parkease.invoice.domain;

import com.parkease.invoice.application.InvoiceDTO;
import com.parkease.invoice.infrastructure.InvoiceRepository;
import com.parkease.parkingmeter.domain.ParkingMeter;
import com.parkease.payment.domain.Payment;
import com.parkease.payment.domain.PaymentService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final PaymentService paymentService;
    private final Logger logger = Logger.getLogger(InvoiceService.class.getName());

    public InvoiceService(InvoiceRepository invoiceRepository, PaymentService paymentService) {
        this.invoiceRepository = invoiceRepository;
        this.paymentService = paymentService;
    }

    @Async
    @Transactional
    public void createInvoice(Payment payment, LocalDateTime creationDate) {
        Invoice invoice = invoiceRepository.save(new Invoice(payment, creationDate));
        logger.info("Invoice created: " + invoice.getId());
        invoice.processed();
        logger.info("Invoice processed: " + invoice.getId());
        invoiceRepository.save(invoice);
        paymentService.processPayment(invoice.getPayment());
    }

    public InvoiceDTO getInvoice(String id) {
        return invoiceRepository.findById(id).map(InvoiceDTO::new).orElseThrow();
    }

    public List<InvoiceDTO> listAllInvoices() {
        return invoiceRepository.findAll().stream().map(InvoiceDTO::new).toList();
    }

    public void deleteInvoice(String id) {
        invoiceRepository.deleteById(id);
    }


    public void delete(Payment payment) {
        invoiceRepository.deleteAllByPayment(payment);
    }

    public void deleteAllInvoicesAndPayments(ParkingMeter parkingMeter) {
        List<Payment> payments = paymentService.findAllByDriver(parkingMeter.getDriverId());
        payments.forEach(invoiceRepository::deleteAllByPayment);
        paymentService.deleteAll(parkingMeter.getDriverId());
    }

    public List<Invoice> findAllByDriverId(String driverId) {
        return invoiceRepository.findAllByPayment_Driver_Id(driverId);
    }
}
