package com.parkease.invoice.infrastructure;

import com.parkease.invoice.domain.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvoiceRepository extends MongoRepository<Invoice, String>{
}