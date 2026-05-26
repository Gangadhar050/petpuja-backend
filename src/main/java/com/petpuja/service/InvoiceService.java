package com.petpuja.service;

import com.petpuja.dto.InvoiceDTO;
import com.petpuja.model.OrderItem;
import com.petpuja.model.Orders;
import com.petpuja.model.Payment;
import com.petpuja.repository.OrderItemRepository;
import com.petpuja.repository.OrderRepository;
import com.petpuja.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    // =====================================
    // GENERATE INVOICE
    // =====================================

    public InvoiceDTO generateInvoice(
            Long orderId,
            Long tableId) {

        // =====================================
        // FIND ORDER
        // =====================================

        Orders order =
                orderRepository
                        .findById(orderId)
                        .orElse(null);

        // ORDER NOT FOUND

        if(order == null){

            return null;
        }

        // =====================================
        // TABLE VALIDATION
        // =====================================

        if(!order.getTableId().equals(tableId)){

            return null;
        }

        // =====================================
        // GET ORDER ITEMS
        // =====================================

        List<OrderItem> items =
                orderItemRepository
                        .findByOrderId(orderId);

        // =====================================
        // ITEM LIST
        // =====================================

        List<String> itemList =
                new ArrayList<>();

        // =====================================
        // SUBTOTAL
        // =====================================

        double subtotal = 0;

        // =====================================
        // LOOP ITEMS
        // =====================================

        for(OrderItem item : items){

            // NULL SAFETY

            Double price =
                    item.getPrice() != null
                    ? item.getPrice()
                    : 0.0;

            Integer quantity =
                    item.getQuantity() != null
                    ? item.getQuantity()
                    : 0;

            String foodName =
                    item.getFoodName() != null
                    ? item.getFoodName()
                    : "Unknown Item";

            // ITEM TOTAL

            double itemTotal =
                    price * quantity;

            // ADD SUBTOTAL

            subtotal += itemTotal;

            // ADD ITEM TO LIST

            itemList.add(

                    foodName
                    + " x "
                    + quantity
                    + " = ₹"
                    + itemTotal
            );
        }

        // =====================================
        // GST CALCULATION
        // =====================================

        double gst =
                subtotal * 0.05;

        // =====================================
        // FINAL TOTAL
        // =====================================

        double total =
                subtotal + gst;

        // =====================================
        // FIND PAYMENT
        // =====================================

        Payment payment =
                paymentRepository
                .findAll()
                .stream()
                .filter(p ->
                        p.getOrderId()
                        .equals(orderId))
                .findFirst()
                .orElse(null);

        // =====================================
        // CREATE DTO
        // =====================================

        InvoiceDTO invoice =
                new InvoiceDTO();

        invoice.setOrderId(orderId);

        invoice.setTableId(tableId);

        invoice.setItems(itemList);

        invoice.setSubtotal(subtotal);

        invoice.setGst(gst);

        invoice.setTotal(total);

        // =====================================
        // PAYMENT STATUS
        // =====================================

        if(payment != null){

            invoice.setPaymentStatus(
                    payment.getPaymentStatus());

        } else {

            invoice.setPaymentStatus(
                    "PENDING");
        }

        // =====================================
        // RETURN INVOICE
        // =====================================

        return invoice;
    }
}