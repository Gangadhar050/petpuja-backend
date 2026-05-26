package com.petpuja.repository;

import com.petpuja.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository
        extends JpaRepository<Invoice, Long> {
}