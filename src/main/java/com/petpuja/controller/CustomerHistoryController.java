package com.petpuja.controller;

import com.petpuja.dto.CustomerOrderHistoryDTO;
import com.petpuja.service.CustomerOrderHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
public class CustomerHistoryController {

    @Autowired
    private CustomerOrderHistoryService
            customerOrderHistoryService;

    // =====================================
    // CUSTOMER ORDER HISTORY
    // =====================================

    @GetMapping("/order-history")
    public List<CustomerOrderHistoryDTO>
    orderHistory(

            @RequestParam Long customerId){

        return customerOrderHistoryService

                .getOrderHistory(
                        customerId);
    }
}