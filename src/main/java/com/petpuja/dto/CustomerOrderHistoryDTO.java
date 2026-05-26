package com.petpuja.dto;

public class CustomerOrderHistoryDTO {

    private String foodName;

    private Integer quantity;

    private Double price;

    private Long orderId;

    // GETTERS

    public String getFoodName() {
        return foodName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Long getOrderId() {
        return orderId;
    }

    // SETTERS

    public void setFoodName(
            String foodName) {

        this.foodName = foodName;
    }

    public void setQuantity(
            Integer quantity) {

        this.quantity = quantity;
    }

    public void setPrice(
            Double price) {

        this.price = price;
    }

    public void setOrderId(
            Long orderId) {

        this.orderId = orderId;
    }
}