package com.parkease.invoice.infrastructure;

import com.parkease.invoice.domain.Invoice;
import com.parkease.payment.domain.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InvoiceRepository extends MongoRepository<Invoice, String>{

    void deleteAllByPayment(Payment payment);

    List<Invoice> findAllByPayment(Payment payment);

    List<Invoice> findAllByPayment_Driver_Id(String driverId);
}
