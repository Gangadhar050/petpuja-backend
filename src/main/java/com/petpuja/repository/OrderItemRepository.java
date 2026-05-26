package com.petpuja.repository;

import com.petpuja.model.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository
        extends JpaRepository<OrderItem, Long> {

    // =====================================
    // FIND ORDER ITEMS BY ORDER ID
    // =====================================

    List<OrderItem> findByOrderId(
            Long orderId);

    // =====================================
    // FIND FOOD BY FOOD NAME
    // =====================================

    List<OrderItem> findByFoodName(
            String foodName);
}