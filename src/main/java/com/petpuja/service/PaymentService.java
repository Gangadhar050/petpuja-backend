package com.petpuja.service;

import com.petpuja.dto.PaymentRequestDTO;
import com.petpuja.model.Orders;
import com.petpuja.model.Payment;
import com.petpuja.repository.OrderRepository;
import com.petpuja.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    // =====================================
    // MAKE PAYMENT
    // =====================================

    public Payment makePayment(
            PaymentRequestDTO dto) {

        Payment payment = new Payment();

        // =====================================
        // GET ORDER
        // =====================================

        Orders order =
                orderRepository
                        .findById(dto.getOrderId())
                        .orElse(null);

        // =====================================
        // ORDER NOT FOUND
        // =====================================

        if(order == null){

            payment.setPaymentStatus(
                    "ORDER_NOT_FOUND");

            return paymentRepository
                    .save(payment);
        }

        // =====================================
        // CHECK ORDER CANCELLED
        // =====================================

        if("CANCELLED".equals(
                order.getStatus())){

            payment.setPaymentStatus(
                    "ORDER_CANCELLED");

            return paymentRepository
                    .save(payment);
        }

        // =====================================
        // SET DEFAULT PAYMENT EXPIRY TIME
        // =====================================

        if(order.getPaymentExpiryTime()
                == null){

            order.setPaymentExpiryTime(

                    LocalDateTime.now()
                            .plusMinutes(5));

            orderRepository.save(order);
        }

        // =====================================
        // CHECK PAYMENT TIMEOUT
        // =====================================

        if(order.getPaymentExpiryTime()
                != null &&

                LocalDateTime.now()
                        .isAfter(
                                order.getPaymentExpiryTime())){

            order.setStatus(
                    "CANCELLED");

            order.setPaymentStatus(
                    "TIMEOUT");

            orderRepository.save(order);

            payment.setPaymentStatus(
                    "PAYMENT_TIMEOUT");

            return paymentRepository
                    .save(payment);
        }

        // =====================================
        // SET PAYMENT DETAILS
        // =====================================

        payment.setOrderId(
                dto.getOrderId());

        payment.setAmount(
                dto.getAmount());

        payment.setPaymentMethod(
                dto.getPaymentMethod());

        payment.setPaymentTime(
                LocalDateTime.now());

        // =====================================
        // RANDOM PAYMENT RESULT
        // =====================================

        Random random = new Random();

        int result =
                random.nextInt(3);

        // =====================================
        // PAYMENT SUCCESS
        // =====================================

        if(result == 0) {

            payment.setPaymentStatus(
                    "SUCCESS");

            // UPDATE ORDER

            order.setPaymentStatus(
                    "PAID");

            order.setStatus(
                    "ORDER_CONFIRMED");

            orderRepository.save(order);
        }

        // =====================================
        // PAYMENT FAILED
        // =====================================

        else if(result == 1) {

            payment.setPaymentStatus(
                    "FAILED");

            // PAYMENT ATTEMPTS

            int attempts =
                    order.getPaymentAttempts()
                            + 1;

            order.setPaymentAttempts(
                    attempts);

            // 3 FAILED ATTEMPTS

            if(attempts >= 3){

                order.setStatus(
                        "CANCELLED");

                order.setPaymentStatus(
                        "FAILED");

            } else {

                order.setPaymentStatus(
                        "RETRY_PAYMENT");
            }

            orderRepository.save(order);
        }

        // =====================================
        // BANK SERVER BUSY
        // =====================================

        else {

            payment.setPaymentStatus(
                    "BANK_SERVER_BUSY");

            // PAYMENT ATTEMPTS

            int attempts =
                    order.getPaymentAttempts()
                            + 1;

            order.setPaymentAttempts(
                    attempts);

            // 3 FAILED ATTEMPTS

            if(attempts >= 3){

                order.setStatus(
                        "CANCELLED");

                order.setPaymentStatus(
                        "FAILED");

            } else {

                order.setPaymentStatus(
                        "RETRY_PAYMENT");
            }

            orderRepository.save(order);
        }

        // =====================================
        // SAVE PAYMENT
        // =====================================

        return paymentRepository
                .save(payment);
    }

    // =====================================
    // GET PAYMENT
    // =====================================

    public Payment getPayment(Long id) {

        return paymentRepository
                .findById(id)
                .orElse(null);
    }

    // =====================================
    // UPDATE PAYMENT STATUS
    // =====================================

    public Payment updatePaymentStatus(
            Long paymentId,
            String status) {

        Payment payment =
                paymentRepository
                        .findById(paymentId)
                        .orElse(null);

        if(payment != null) {

            payment.setPaymentStatus(
                    status);

            return paymentRepository
                    .save(payment);
        }

        return null;
    }

    // =====================================
    // CHECK PAYMENT TIMER
    // =====================================

    public String checkPaymentTimer(
            Long orderId){

        Orders order =
                orderRepository
                        .findById(orderId)
                        .orElse(null);

        if(order == null){

            return "❌ Order Not Found";
        }

        // =====================================
        // PAYMENT ALREADY SUCCESS
        // =====================================

        if("PAID".equals(
                order.getPaymentStatus())){

            return "✅ Payment completed.";
        }

        // =====================================
        // PAYMENT EXPIRY NULL CHECK
        // =====================================

        if(order.getPaymentExpiryTime()
                == null){

            order.setPaymentExpiryTime(

                    LocalDateTime.now()
                            .plusMinutes(5));

            orderRepository.save(order);

            return "⏳ Payment timer started.";
        }

        // =====================================
        // CHECK TIMEOUT
        // =====================================

        if(order.getPaymentExpiryTime()
                != null &&

                LocalDateTime.now()
                        .isAfter(
                                order.getPaymentExpiryTime())){

            order.setStatus(
                    "CANCELLED");

            order.setPaymentStatus(
                    "TIMEOUT");

            orderRepository.save(order);

            return "❌ Payment timeout. Order cancelled.";
        }

        // =====================================
        // WAITING
        // =====================================

        return "⏳ Waiting for payment...";
    }
}