package com.petpuja.dto;

public class OrderRequestDTO {

    // =====================================
    // CUSTOMER ID
    // =====================================

    private Long customerId;

    // =====================================
    // TABLE ID
    // =====================================

    private Long tableId;

    // =====================================
    // TOTAL PRICE
    // =====================================

    private Double totalPrice;

    // =====================================
    // GET CUSTOMER ID
    // =====================================

    public Long getCustomerId() {

        return customerId;
    }

    // =====================================
    // SET CUSTOMER ID
    // =====================================

    public void setCustomerId(
            Long customerId) {

        this.customerId = customerId;
    }

    // =====================================
    // GET TABLE ID
    // =====================================

    public Long getTableId() {

        return tableId;
    }

    // =====================================
    // SET TABLE ID
    // =====================================

    public void setTableId(
            Long tableId) {

        this.tableId = tableId;
    }

    // =====================================
    // GET TOTAL PRICE
    // =====================================

    public Double getTotalPrice() {

        return totalPrice;
    }

    // =====================================
    // SET TOTAL PRICE
    // =====================================

    public void setTotalPrice(
            Double totalPrice) {

        this.totalPrice = totalPrice;
    }
}