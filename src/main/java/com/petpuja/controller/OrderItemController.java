package com.petpuja.controller;

import com.petpuja.dto.OrderItemRequestDTO;
import com.petpuja.model.OrderItem;
import com.petpuja.service.OrderItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-items")
@CrossOrigin(origins = "*")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    // =====================================
    // ADD ORDER ITEM
    // =====================================

    @PostMapping("/orderId={orderId}/tableId={tableId}")
    public OrderItem addItem(

            @PathVariable Long orderId,

            @PathVariable Long tableId,

            @RequestBody OrderItemRequestDTO dto){

        return orderItemService
                .addItem(
                        orderId,
                        tableId,
                        dto);
    }
}