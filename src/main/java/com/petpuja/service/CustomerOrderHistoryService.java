package com.petpuja.service;

import com.petpuja.dto.CustomerOrderHistoryDTO;
import com.petpuja.model.OrderItem;
import com.petpuja.model.Orders;

import com.petpuja.repository.OrderItemRepository;
import com.petpuja.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerOrderHistoryService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository
            orderItemRepository;

    // =====================================
    // CUSTOMER ORDER HISTORY
    // =====================================

    public List<CustomerOrderHistoryDTO>
    getOrderHistory(Long customerId){

        // GET CUSTOMER ORDERS

        List<Orders> orders =

                orderRepository
                        .findByCustomerId(
                                customerId);

        // FINAL RESPONSE

        List<CustomerOrderHistoryDTO>
                response =
                new ArrayList<>();

        // LOOP ORDERS

        for(Orders order : orders){

            // GET ORDER ITEMS

            List<OrderItem> items =

                    orderItemRepository
                            .findByOrderId(
                                    order.getOrderId());

            // LOOP ITEMS

            for(OrderItem item : items){

                CustomerOrderHistoryDTO dto =

                        new CustomerOrderHistoryDTO();

                dto.setFoodName(
                        item.getFoodName());

                dto.setQuantity(
                        item.getQuantity());

                dto.setPrice(
                        item.getPrice());

                dto.setOrderId(
                        item.getOrderId());

                response.add(dto);
            }
        }

        return response;
    }
}