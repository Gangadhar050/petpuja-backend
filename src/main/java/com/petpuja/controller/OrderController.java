package com.petpuja.controller;

import com.petpuja.dto.AiFoodSuggestionDTO;
import com.petpuja.dto.OrderRequestDTO;

import com.petpuja.model.Orders;

import com.petpuja.service.AiFoodSuggestionService;
import com.petpuja.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AiFoodSuggestionService
            aiFoodSuggestionService;

    // =====================================
    // CREATE ORDER
    // =====================================

    @PostMapping
    public Orders createOrder(
            @RequestBody OrderRequestDTO dto) {

        return orderService
                .createOrder(dto);
    }

    // =====================================
    // GET ORDER
    // =====================================

    @GetMapping("/{id}")
    public Orders getOrder(
            @PathVariable Long id) {

        return orderService
                .getOrder(id);
    }

    // =====================================
    // PAYMENT SUCCESS
    // =====================================

    @PutMapping("/payment-success")
    public String paymentSuccess(
            @RequestParam Long orderId){

        return orderService
                .paymentSuccess(orderId);
    }

    // =====================================
    // PAYMENT FAILED
    // =====================================

    @PutMapping("/payment-failed")
    public String paymentFailed(
            @RequestParam Long orderId){

        return orderService
                .paymentFailed(orderId);
    }

    // =====================================
    // CHECK PAYMENT TIMER
    // =====================================

    @GetMapping("/check-payment")
    public String checkPaymentTimer(
            @RequestParam Long orderId){

        return orderService
                .checkPaymentTimer(orderId);
    }

    // =====================================
    // UPDATE ORDER STATUS
    // =====================================

    @PutMapping("/status")
    public String updateStatus(

            @RequestParam Long orderId,

            @RequestParam String status) {

        return orderService
                .updateOrderStatus(
                        orderId,
                        status);
    }

    // =====================================
    // AI PREPARATION TIME
    // =====================================

    @GetMapping("/estimate-time")
    public String estimateTime(

            @RequestParam Integer itemCount,

            @RequestParam boolean rushHour){

        return orderService
                .estimatePreparationTime(
                        itemCount,
                        rushHour);
    }

    // =====================================
    // SMART WAITING TIME
    // =====================================

    @GetMapping("/waiting-time")
    public String waitingTime(){

        return orderService
                .estimateWaitingTime();
    }

    // =====================================
    // TRACK ORDER BY TABLE ID
    // =====================================

    @GetMapping("/track-order")
    public String trackOrder(

            @RequestParam Long tableId){

        return orderService
                .trackOrder(tableId);
    }

    // =====================================
    // CHECK ORDER DELAY
    // =====================================

    @GetMapping("/check-delay")
    public String checkDelay(

            @RequestParam Long tableId){

        return orderService
                .checkOrderDelay(tableId);
    }

    // =====================================
    // TABLE PREPARATION TIME
    // =====================================

    @GetMapping("/table-preparation-time")
    public String tablePreparationTime(

            @RequestParam Long tableId){

        return orderService
                .tablePreparationTime(tableId);
    }

    // =====================================
    // AI FOOD SUGGESTIONS
    // =====================================

    @GetMapping("/ai-suggestions")
    public List<AiFoodSuggestionDTO>
    aiSuggestions(

            @RequestParam Double minAmount,

            @RequestParam Double maxAmount){

        return aiFoodSuggestionService
                .getSuggestions(
                        minAmount,
                        maxAmount);
    }
}