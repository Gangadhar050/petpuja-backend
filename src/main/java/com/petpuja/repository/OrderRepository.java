package com.petpuja.repository;

import com.petpuja.model.Orders;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository
        extends JpaRepository<Orders, Long> {

    // =====================================
    // FIND ORDERS BY CUSTOMER ID
    // =====================================

    List<Orders> findByCustomerId(
            Long customerId);
}