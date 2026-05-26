package com.petpuja.dto;

public class AiFoodSuggestionDTO {

    // =====================================
    // FOOD NAME
    // =====================================

    private String foodName;

    // =====================================
    // PRICE
    // =====================================

    private Double price;

    // =====================================
    // CATEGORY
    // =====================================

    private String category;

    // =====================================
    // AI REASON
    // =====================================

    private String reason;

    // =====================================
    // DEFAULT CONSTRUCTOR
    // =====================================

    public AiFoodSuggestionDTO() {
    }

    // =====================================
    // PARAMETERIZED CONSTRUCTOR
    // =====================================

    public AiFoodSuggestionDTO(
            String foodName,
            Double price,
            String category,
            String reason) {

        this.foodName = foodName;
        this.price = price;
        this.category = category;
        this.reason = reason;
    }

    // =====================================
    // GETTERS
    // =====================================

    public String getFoodName() {
        return foodName;
    }

    public Double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getReason() {
        return reason;
    }

    // =====================================
    // SETTERS
    // =====================================

    public void setFoodName(
            String foodName) {

        this.foodName = foodName;
    }

    public void setPrice(
            Double price) {

        this.price = price;
    }

    public void setCategory(
            String category) {

        this.category = category;
    }

    public void setReason(
            String reason) {

        this.reason = reason;
    }
}