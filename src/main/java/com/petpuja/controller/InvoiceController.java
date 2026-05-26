package com.petpuja.controller;

import com.petpuja.dto.InvoiceDTO;
import com.petpuja.model.Invoice;
import com.petpuja.repository.InvoiceRepository;
import com.petpuja.service.InvoiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceService invoiceService;

    // =====================================
    // GENERATE INVOICE USING ORDER ID
    // =====================================

    @GetMapping
    public InvoiceDTO getInvoice(

            @RequestParam Long orderId,

            @RequestParam Long tableId){

        return invoiceService
                .generateInvoice(orderId, tableId);
    }

    // =====================================
    // CUSTOMER OWN INVOICE
    // =====================================

    @GetMapping("/customer/{id}")
    public Invoice getCustomerInvoice(

            @PathVariable Long id){

        return invoiceRepository
                .findById(id)
                .orElse(null);
    }

    // =====================================
    // ADMIN ALL INVOICE HISTORY
    // =====================================

    @GetMapping("/admin/all")
    public List<Invoice> getAllInvoices(){

        return invoiceRepository.findAll();
    }

    // =====================================
    // ADMIN PRINT BILL
    // =====================================

    @GetMapping("/admin/print/{invoiceId}")
    public String printBill(

            @PathVariable Long invoiceId){

        Invoice invoice =
                invoiceRepository
                        .findById(invoiceId)
                        .orElse(null);

        if(invoice == null){

            return "❌ Invoice Not Found";
        }

        return
                "========================\n" +

                "       PETPUJA\n" +

                "========================\n\n" +

                "Table No : "
                + invoice.getTableId()
                + "\n" +

                "Order No : "
                + invoice.getOrderId()
                + "\n\n" +

                "Total Amount : ₹"
                + invoice.getTotalAmount()
                + "\n\n" +

                "Payment Status : "
                + invoice.getPaymentStatus()
                + "\n\n" +

                "Thank You Visit Again\n" +

                "========================";
    }
}