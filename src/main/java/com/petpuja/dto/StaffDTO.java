package com.petpuja.dto;

public class StaffDTO {

    // =====================================
    // STAFF ID
    // =====================================

    private Long id;

    // =====================================
    // STAFF NAME
    // =====================================

    private String name;

    // =====================================
    // STAFF EMAIL
    // =====================================

    private String email;

    // =====================================
    // TOTAL DELIVERED ORDERS
    // =====================================

    private Long totalDeliveredOrders;

    // =====================================
    // DEFAULT CONSTRUCTOR
    // =====================================

    public StaffDTO() {
    }

    // =====================================
    // PARAMETERIZED CONSTRUCTOR
    // =====================================

    public StaffDTO(Long id,
                    String name,
                    String email,
                    Long totalDeliveredOrders) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.totalDeliveredOrders =
                totalDeliveredOrders;
    }

    // =====================================
    // GET ID
    // =====================================

    public Long getId() {

        return id;
    }

    // =====================================
    // SET ID
    // =====================================

    public void setId(Long id) {

        this.id = id;
    }

    // =====================================
    // GET NAME
    // =====================================

    public String getName() {

        return name;
    }

    // =====================================
    // SET NAME
    // =====================================

    public void setName(String name) {

        this.name = name;
    }

    // =====================================
    // GET EMAIL
    // =====================================

    public String getEmail() {

        return email;
    }

    // =====================================
    // SET EMAIL
    // =====================================

    public void setEmail(String email) {

        this.email = email;
    }

    // =====================================
    // GET TOTAL DELIVERED ORDERS
    // =====================================

    public Long getTotalDeliveredOrders() {

        return totalDeliveredOrders;
    }

    // =====================================
    // SET TOTAL DELIVERED ORDERS
    // =====================================

    public void setTotalDeliveredOrders(
            Long totalDeliveredOrders) {

        this.totalDeliveredOrders =
                totalDeliveredOrders;
    }
}