package com.parkease.invoice.domain;

import com.parkease.InvoiceProcessingException;
import com.parkease.invoice.application.InvoiceDTO;
import com.parkease.invoice.infrastructure.InvoiceRepository;
import com.parkease.parkingmeter.domain.ParkingMeter;
import com.parkease.payment.domain.Payment;
import com.parkease.payment.domain.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final PaymentService paymentService;
    private final RestTemplate restTemplate;
    private final Logger logger = Logger.getLogger(InvoiceService.class.getName());

    public InvoiceService(InvoiceRepository invoiceRepository, PaymentService paymentService, RestTemplate restTemplate) {
        this.invoiceRepository = invoiceRepository;
        this.paymentService = paymentService;
        this.restTemplate = restTemplate;
    }

    @Async
    @Transactional
    public void createInvoice(Payment payment, LocalDateTime creationDate) {
        Invoice invoice = invoiceRepository.save(new Invoice(payment, creationDate));
//        ResponseEntity<Invoice> response = restTemplate
//                .postForEntity("https://run.mocky.io/v3/025c106e-d4ee-457d-9168-2b1df7a2dc40?mocky-delay=1s", invoice, Invoice.class);
//        if (!response.getStatusCode().is2xxSuccessful()) {
//            logger.warning("Failed to process invoice - " + invoice.getId() + " - Status: " + response.getStatusCode());
//            throw new InvoiceProcessingException("Invoice processing failed: Try again");
//        }

        invoice.processed();
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
