package com.petpuja.controller;

import com.petpuja.dto.PaymentRequestDTO;
import com.petpuja.model.Payment;
import com.petpuja.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // =====================================
    // MAKE PAYMENT
    // =====================================

    @PostMapping
    public Payment makePayment(
            @RequestBody PaymentRequestDTO dto) {

        return paymentService
                .makePayment(dto);
    }

    // =====================================
    // GET PAYMENT
    // =====================================

    @GetMapping("/{id}")
    public Payment getPayment(
            @PathVariable Long id) {

        return paymentService
                .getPayment(id);
    }

    // =====================================
    // UPDATE PAYMENT STATUS
    // =====================================

    @PutMapping("/status")
    public Payment updatePaymentStatus(

            @RequestParam Long paymentId,

            @RequestParam String status) {

        return paymentService
                .updatePaymentStatus(
                        paymentId,
                        status);
    }

    // =====================================
    // CHECK PAYMENT TIMER
    // =====================================

    @GetMapping("/check-timer")
    public String checkPaymentTimer(

            @RequestParam Long orderId){

        return paymentService
                .checkPaymentTimer(
                        orderId);
    }

    // =====================================
    // PAYMENT SUCCESS API
    // =====================================

    @PutMapping("/success")
    public String paymentSuccess(

            @RequestParam Long orderId){

        Payment payment =
                paymentService
                        .getPayment(orderId);

        if(payment == null){

            return "❌ Payment Not Found";
        }

        payment.setPaymentStatus(
                "SUCCESS");

        paymentService
                .updatePaymentStatus(
                        payment.getPaymentId(),
                        "SUCCESS");

        return "✅ Payment successful.";
    }

    // =====================================
    // PAYMENT FAILED API
    // =====================================

    @PutMapping("/failed")
    public String paymentFailed(

            @RequestParam Long orderId){

        Payment payment =
                paymentService
                        .getPayment(orderId);

        if(payment == null){

            return "❌ Payment Not Found";
        }

        payment.setPaymentStatus(
                "FAILED");

        paymentService
                .updatePaymentStatus(
                        payment.getPaymentId(),
                        "FAILED");

        return "❌ Payment failed.";
    }

    // =====================================
    // RETRY PAYMENT
    // =====================================

    @PutMapping("/retry")
    public String retryPayment(

            @RequestParam Long orderId){

        return "🔄 Please scan QR and pay again.";
    }
}