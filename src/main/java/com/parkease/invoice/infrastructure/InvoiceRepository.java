package com.parkease.invoice.infrastructure;

import com.parkease.invoice.domain.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InvoiceRepository extends MongoRepository<Invoice, String>{

    List<Invoice> findAllByPayment_Driver_Id(String driverId);

    void deleteByPayment_Driver_Id(String driverId);
}
