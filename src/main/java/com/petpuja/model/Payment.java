package com.petpuja.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {

    // =====================================
    // PAYMENT ID
    // =====================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    // =====================================
    // ORDER ID
    // =====================================

    private Long orderId;

    // =====================================
    // PAYMENT AMOUNT
    // =====================================

    private Double amount;

    // =====================================
    // PAYMENT METHOD
    // =====================================

    private String paymentMethod;

    // =====================================
    // PAYMENT STATUS
    // =====================================

    private String paymentStatus;

    // =====================================
    // PAYMENT TIME
    // =====================================

    private LocalDateTime paymentTime;

    // =====================================
    // TRANSACTION ID
    // =====================================

    private String transactionId;

    // =====================================
    // QR PAYMENT STATUS
    // =====================================

    private String qrStatus;

    // =====================================
    // PAYMENT ATTEMPTS
    // =====================================

    private Integer paymentAttempts;

    // =====================================
    // PAYMENT EXPIRY TIME
    // =====================================

    private LocalDateTime paymentExpiryTime;

    // =====================================
    // GETTERS & SETTERS
    // =====================================

    public Long getPaymentId() {

        return paymentId;
    }

    public void setPaymentId(Long paymentId) {

        this.paymentId = paymentId;
    }

    public Long getOrderId() {

        return orderId;
    }

    public void setOrderId(Long orderId) {

        this.orderId = orderId;
    }

    public Double getAmount() {

        return amount;
    }

    public void setAmount(Double amount) {

        this.amount = amount;
    }

    public String getPaymentMethod() {

        return paymentMethod;
    }

    public void setPaymentMethod(
            String paymentMethod) {

        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {

        return paymentStatus;
    }

    public void setPaymentStatus(
            String paymentStatus) {

        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getPaymentTime() {

        return paymentTime;
    }

    public void setPaymentTime(
            LocalDateTime paymentTime) {

        this.paymentTime = paymentTime;
    }

    public String getTransactionId() {

        return transactionId;
    }

    public void setTransactionId(
            String transactionId) {

        this.transactionId = transactionId;
    }

    public String getQrStatus() {

        return qrStatus;
    }

    public void setQrStatus(
            String qrStatus) {

        this.qrStatus = qrStatus;
    }

    public Integer getPaymentAttempts() {

        return paymentAttempts;
    }

    public void setPaymentAttempts(
            Integer paymentAttempts) {

        this.paymentAttempts =
                paymentAttempts;
    }

    public LocalDateTime getPaymentExpiryTime() {

        return paymentExpiryTime;
    }

    public void setPaymentExpiryTime(
            LocalDateTime paymentExpiryTime) {

        this.paymentExpiryTime =
                paymentExpiryTime;
    }
}