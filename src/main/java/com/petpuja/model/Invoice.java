package com.petpuja.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "invoice")
public class Invoice {

    // =====================================
    // INVOICE ID
    // =====================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;

    // =====================================
    // ORDER ID
    // =====================================

    private Long orderId;

    // =====================================
    // TABLE ID
    // =====================================

    private Long tableId;

    // =====================================
    // CUSTOMER ITEMS
    // =====================================

    private String items;

    // =====================================
    // TOTAL AMOUNT
    // =====================================

    private Double totalAmount;

    // =====================================
    // PAYMENT STATUS
    // =====================================

    private String paymentStatus;

    // =====================================
    // PAYMENT METHOD
    // =====================================

    private String paymentMethod;

    // =====================================
    // TRANSACTION ID
    // =====================================

    private String transactionId;

    // =====================================
    // GENERATED TIME
    // =====================================

    private LocalDateTime generatedTime;

    // =====================================
    // GETTERS AND SETTERS
    // =====================================

    public Long getInvoiceId() {

        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {

        this.invoiceId = invoiceId;
    }

    public Long getOrderId() {

        return orderId;
    }

    public void setOrderId(Long orderId) {

        this.orderId = orderId;
    }

    public Long getTableId() {

        return tableId;
    }

    public void setTableId(Long tableId) {

        this.tableId = tableId;
    }

    public String getItems() {

        return items;
    }

    public void setItems(String items) {

        this.items = items;
    }

    public Double getTotalAmount() {

        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {

        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() {

        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {

        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethod() {

        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {

        this.paymentMethod = paymentMethod;
    }

    public String getTransactionId() {

        return transactionId;
    }

    public void setTransactionId(String transactionId) {

        this.transactionId = transactionId;
    }

    public LocalDateTime getGeneratedTime() {

        return generatedTime;
    }

    public void setGeneratedTime(
            LocalDateTime generatedTime) {

        this.generatedTime =
                generatedTime;
    }
}