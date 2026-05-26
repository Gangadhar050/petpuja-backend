package com.petpuja.controller;

import com.petpuja.dto.TopSellingFoodDTO;
import com.petpuja.model.Orders;
import com.petpuja.model.Payment;

import com.petpuja.repository.OrderRepository;
import com.petpuja.repository.PaymentRepository;

import com.petpuja.service.TopSellingFoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminPaymentController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TopSellingFoodService
            topSellingFoodService;

    // =====================================
    // TOTAL REVENUE
    // =====================================

    @GetMapping("/revenue")
    public Map<String, Double> getTotalRevenue(){

        double totalRevenue =

                orderRepository.findAll()
                        .stream()
                        

                        .filter(order ->
                                "PAID".equals(
                                        order.getPaymentStatus()))

                        .mapToDouble(
                                Orders::getTotalPrice)

                        .sum();

        Map<String, Double> response =
                new HashMap<>();

        response.put(
                "totalRevenue",
                totalRevenue);

        return response;
    }

    // =====================================
    // TOTAL ORDERS
    // =====================================

    @GetMapping("/orders-count")
    public Map<String, Long> getOrdersCount(){

        long totalOrders =
                orderRepository.count();

        Map<String, Long> response =
                new HashMap<>();

        response.put(
                "totalOrders",
                totalOrders);

        return response;
    }

    // =====================================
    // SUCCESS PAYMENTS
    // =====================================

    @GetMapping("/success-payments")
    public Map<String, Long> successPayments(){

        long successPayments =

                paymentRepository.findAll()
                        .stream()

                        .filter(payment ->
                                "SUCCESS".equals(
                                        payment.getPaymentStatus()))

                        .count();

        Map<String, Long> response =
                new HashMap<>();

        response.put(
                "successPayments",
                successPayments);

        return response;
    }

    // =====================================
    // FAILED PAYMENTS
    // =====================================

    @GetMapping("/failed-payments")
    public Map<String, Long> failedPayments(){

        long failedPayments =

                paymentRepository.findAll()
                        .stream()

                        .filter(payment ->

                                "FAILED".equals(
                                        payment.getPaymentStatus())

                                        ||

                                "BANK_SERVER_BUSY".equals(
                                        payment.getPaymentStatus())

                                        ||

                                "TIMEOUT".equals(
                                        payment.getPaymentStatus()))

                        .count();

        Map<String, Long> response =
                new HashMap<>();

        response.put(
                "failedPayments",
                failedPayments);

        return response;
    }

    // =====================================
    // PAYMENT SUCCESS RATE
    // =====================================

    @GetMapping("/payment-success-rate")
    public Map<String, Double> paymentSuccessRate(){

        List<Payment> payments =
                paymentRepository.findAll();

        long success =

                payments.stream()

                        .filter(payment ->
                                "SUCCESS".equals(
                                        payment.getPaymentStatus()))

                        .count();

        long total =
                payments.size();

        double rate = 0;

        if(total > 0){

            rate =
                    ((double) success / total) * 100;
        }

        Map<String, Double> response =
                new HashMap<>();

        response.put(
                "paymentSuccessRate",
                rate);

        return response;
    }

    // =====================================
    // ACTIVE ORDERS
    // =====================================

    @GetMapping("/active-orders")
    public List<Orders> activeOrders(){

        return orderRepository.findAll()
                .stream()

                .filter(order ->

                        "ORDER_CONFIRMED".equals(
                                order.getStatus())

                                ||

                        "PREPARING".equals(
                                order.getStatus())

                                ||

                        "COOKING".equals(
                                order.getStatus()))

                .collect(Collectors.toList());
    }

    // =====================================
    // CANCELLED ORDERS
    // =====================================

    @GetMapping("/cancelled-orders")
    public List<Orders> cancelledOrders(){

        return orderRepository.findAll()
                .stream()

                .filter(order ->
                        "CANCELLED".equals(
                                order.getStatus()))

                .collect(Collectors.toList());
    }

    // =====================================
    // TOTAL PAID ORDERS
    // =====================================

    @GetMapping("/paid-orders")
    public long paidOrders(){

        return orderRepository.findAll()
                .stream()

                .filter(order ->
                        "PAID".equals(
                                order.getPaymentStatus()))

                .count();
    }

    // =====================================
    // TODAY SALES
    // =====================================

    @GetMapping("/today-sales")
    public Map<String, Double> todaySales(){

        double todayRevenue =

                orderRepository.findAll()
                        .stream()

                        .filter(order ->
                                "PAID".equals(
                                        order.getPaymentStatus()))

                        .mapToDouble(
                                Orders::getTotalPrice)

                        .sum();

        Map<String, Double> response =
                new HashMap<>();

        response.put(
                "todaySales",
                todayRevenue);

        return response;
    }

    // =====================================
    // ACTIVE TABLES
    // =====================================

    @GetMapping("/active-tables")
    public Map<String, Long> activeTables(){

        long tables =

                orderRepository.findAll()
                        .stream()

                        .filter(order ->

                                "ORDER_CONFIRMED".equals(
                                        order.getStatus())

                                        ||

                                "PREPARING".equals(
                                        order.getStatus()))

                        .map(Orders::getTableId)

                        .distinct()

                        .count();

        Map<String, Long> response =
                new HashMap<>();

        response.put(
                "activeTables",
                tables);

        return response;
    }

    // =====================================
    // TOP SELLING FOODS
    // =====================================

    @GetMapping("/top-selling-foods")
    public List<TopSellingFoodDTO>
    topSellingFoods(){

        return topSellingFoodService
                .topSellingFoods();
    }
}