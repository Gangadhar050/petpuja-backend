package com.petpuja.dto;

public class TopSellingFoodDTO {

    // =====================================
    // FOOD NAME
    // =====================================

    private String foodName;

    // =====================================
    // TOTAL QUANTITY SOLD
    // =====================================

    private Integer totalQuantitySold;

    // =====================================
    // TOTAL REVENUE
    // =====================================

    private Double totalRevenue;

    // =====================================
    // DEFAULT CONSTRUCTOR
    // =====================================

    public TopSellingFoodDTO() {
    }

    // =====================================
    // PARAMETERIZED CONSTRUCTOR
    // =====================================

    public TopSellingFoodDTO(
            String foodName,
            Integer totalQuantitySold,
            Double totalRevenue) {

        this.foodName = foodName;
        this.totalQuantitySold =
                totalQuantitySold;
        this.totalRevenue =
                totalRevenue;
    }

    // =====================================
    // GETTERS
    // =====================================

    public String getFoodName() {
        return foodName;
    }

    public Integer getTotalQuantitySold() {
        return totalQuantitySold;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    // =====================================
    // SETTERS
    // =====================================

    public void setFoodName(
            String foodName) {

        this.foodName = foodName;
    }

    public void setTotalQuantitySold(
            Integer totalQuantitySold) {

        this.totalQuantitySold =
                totalQuantitySold;
    }

    public void setTotalRevenue(
            Double totalRevenue) {

        this.totalRevenue =
                totalRevenue;
    }

    // =====================================
    // TO STRING
    // =====================================

    @Override
    public String toString() {

        return "TopSellingFoodDTO{" +

                "foodName='"
                + foodName + '\'' +

                ", totalQuantitySold="
                + totalQuantitySold +

                ", totalRevenue="
                + totalRevenue +

                '}';
    }
}