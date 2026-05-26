package com.petpuja.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Orders {

    // =====================================
    // PRIMARY KEY
    // =====================================

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long orderId;

    // =====================================
    // CUSTOMER ID
    // =====================================

    private Long customerId;

    // =====================================
    // TABLE ID
    // =====================================

    private Long tableId;

    // =====================================
    // ORDER STATUS
    // =====================================

    private String status;

    // =====================================
    // PAYMENT STATUS
    // =====================================

    private String paymentStatus;

    // =====================================
    // TOTAL PRICE
    // =====================================

    private Double totalPrice;

    // =====================================
    // ORDER CREATED TIME
    // =====================================

    private LocalDateTime createdTime;

    // =====================================
    // PAYMENT ATTEMPTS
    // =====================================

    private Integer paymentAttempts;

    // =====================================
    // PAYMENT TIMER EXPIRY
    // =====================================

    private LocalDateTime paymentExpiryTime;

    // =====================================
    // PREPARATION STATUS
    // =====================================

    private String preparationStatus;

    // =====================================
    // GETTERS & SETTERS
    // =====================================

    public Long getOrderId() {

        return orderId;
    }

    public void setOrderId(
            Long orderId) {

        this.orderId = orderId;
    }

    public Long getCustomerId() {

        return customerId;
    }

    public void setCustomerId(
            Long customerId) {

        this.customerId = customerId;
    }

    public Long getTableId() {

        return tableId;
    }

    public void setTableId(
            Long tableId) {

        this.tableId = tableId;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(
            String status) {

        this.status = status;
    }

    public String getPaymentStatus() {

        return paymentStatus;
    }

    public void setPaymentStatus(
            String paymentStatus) {

        this.paymentStatus = paymentStatus;
    }

    public Double getTotalPrice() {

        return totalPrice;
    }

    public void setTotalPrice(
            Double totalPrice) {

        this.totalPrice = totalPrice;
    }

    public LocalDateTime getCreatedTime() {

        return createdTime;
    }

    public void setCreatedTime(
            LocalDateTime createdTime) {

        this.createdTime = createdTime;
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

    public String getPreparationStatus() {

        return preparationStatus;
    }

    public void setPreparationStatus(
            String preparationStatus) {

        this.preparationStatus =
                preparationStatus;
    }
}
