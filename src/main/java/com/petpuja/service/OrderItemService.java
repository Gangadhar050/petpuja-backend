package com.petpuja.service;

import com.petpuja.dto.OrderItemRequestDTO;
import com.petpuja.model.OrderItem;
import com.petpuja.repository.OrderItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    // =====================================
    // ADD ORDER ITEM
    // =====================================

    public OrderItem addItem(

            Long orderId,

            Long tableId,

            OrderItemRequestDTO dto){

        // CREATE ENTITY

        OrderItem item =
                new OrderItem();

        // SET VALUES

        item.setOrderId(orderId);

        item.setFoodName(
                dto.getFoodName());

        item.setQuantity(
                dto.getQuantity());

        item.setPrice(
                dto.getPrice());

        // OPTIONAL LOG

        System.out.println(
                "Table ID : " + tableId);

        // SAVE

        return orderItemRepository
                .save(item);
    }
}