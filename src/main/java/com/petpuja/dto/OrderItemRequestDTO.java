package com.petpuja.dto;

public class OrderItemRequestDTO {

    private Long itemId;

    private String foodName;

    private Integer quantity;

    private Double price;

    // =========================
    // GETTERS
    // =========================

    public Long getItemId() {
        return itemId;
    }

    public String getFoodName() {
        return foodName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    // =========================
    // SETTERS
    // =========================

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}