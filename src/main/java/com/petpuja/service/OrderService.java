package com.petpuja.service;

import com.petpuja.dto.OrderRequestDTO;

import com.petpuja.model.OrderItem;
import com.petpuja.model.Orders;

import com.petpuja.repository.OrderItemRepository;
import com.petpuja.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository
            orderItemRepository;

    // =====================================
    // CREATE ORDER
    // =====================================

    public Orders createOrder(
            OrderRequestDTO dto) {

        Orders order =
                new Orders();

        // CUSTOMER ID

        order.setCustomerId(
                dto.getCustomerId());

        // TABLE ID

        order.setTableId(
                dto.getTableId());

        // TOTAL PRICE

        order.setTotalPrice(
                dto.getTotalPrice());

        // STATUS

        order.setStatus(
                "PENDING_PAYMENT");

        // PAYMENT STATUS

        order.setPaymentStatus(
                "NOT_PAID");

        // PAYMENT ATTEMPTS

        order.setPaymentAttempts(
                0);

        // PAYMENT EXPIRY

        order.setPaymentExpiryTime(

                LocalDateTime.now()
                        .plusMinutes(5)
        );

        // CREATED TIME

        order.setCreatedTime(
                LocalDateTime.now());

        // PREPARATION STATUS

        order.setPreparationStatus(
                "NOT_STARTED");

        return orderRepository
                .save(order);
    }

    // =====================================
    // GET ORDER
    // =====================================

    public Orders getOrder(Long id) {

        return orderRepository
                .findById(id)
                .orElse(null);
    }

    // =====================================
    // PAYMENT SUCCESS
    // =====================================

    public String paymentSuccess(
            Long orderId) {

        Orders order =

                orderRepository
                        .findById(orderId)
                        .orElse(null);

        if(order == null){

            return "❌ Order Not Found";
        }

        // PAYMENT TIMEOUT

        if(LocalDateTime.now()

                .isAfter(
                        order.getPaymentExpiryTime())){

            order.setStatus(
                    "CANCELLED");

            orderRepository.save(order);

            return "❌ Payment timeout. Order cancelled.";
        }

        // SUCCESS

        order.setPaymentStatus(
                "PAID");

        order.setStatus(
                "ORDER_CONFIRMED");

        orderRepository.save(order);

        return "✅ Payment successful.";
    }

    // =====================================
    // PAYMENT FAILED
    // =====================================

    public String paymentFailed(
            Long orderId) {

        Orders order =

                orderRepository
                        .findById(orderId)
                        .orElse(null);

        if(order == null){

            return "❌ Order Not Found";
        }

        // PAYMENT ATTEMPTS

        int attempts =

                order.getPaymentAttempts()
                        + 1;

        order.setPaymentAttempts(
                attempts);

        // MAX ATTEMPTS

        if(attempts >= 3){

            order.setStatus(
                    "CANCELLED");

            order.setPaymentStatus(
                    "FAILED");

            orderRepository.save(order);

            return "❌ 3 payment attempts failed.";
        }

        orderRepository.save(order);

        return "⚠️ Payment failed. Retry again.";
    }

    // =====================================
    // CHECK PAYMENT TIMER
    // =====================================

    public String checkPaymentTimer(
            Long orderId) {

        Orders order =

                orderRepository
                        .findById(orderId)
                        .orElse(null);

        if(order == null){

            return "❌ Order Not Found";
        }

        // PAYMENT COMPLETED

        if("PAID".equals(
                order.getPaymentStatus())){

            return "✅ Payment completed.";
        }

        // PAYMENT TIMEOUT

        if(LocalDateTime.now()

                .isAfter(
                        order.getPaymentExpiryTime())){

            order.setStatus(
                    "CANCELLED");

            order.setPaymentStatus(
                    "TIMEOUT");

            orderRepository.save(order);

            return "❌ Payment timeout.";
        }

        return "⏳ Payment pending...";
    }

    // =====================================
    // UPDATE ORDER STATUS
    // =====================================

    public String updateOrderStatus(

            Long orderId,

            String status) {

        Orders order =

                orderRepository
                        .findById(orderId)
                        .orElse(null);

        if(order == null){

            return "❌ Order Not Found";
        }

        // PAYMENT CHECK

        if(!"PAID".equals(
                order.getPaymentStatus())){

            return "❌ Payment not completed.";
        }

        // UPDATE STATUS

        order.setStatus(status);

        order.setPreparationStatus(
                status);

        orderRepository.save(order);

        return "✅ Order status updated : "
                + status;
    }

    // =====================================
    // AI PREPARATION TIME
    // =====================================

    public String estimatePreparationTime(

            Integer itemCount,

            boolean rushHour) {

        long activeOrders =

                orderRepository.findAll()
                        .stream()

                        .filter(order ->

                                "ORDER_CONFIRMED"

                                        .equals(
                                                order.getStatus()))

                        .count();

        // BASE TIME

        int basePrepTime =
                itemCount * 5;

        // KITCHEN LOAD

        int kitchenLoad =
                (int) activeOrders * 2;

        // RUSH HOUR

        int rushFactor = 0;

        if(rushHour){

            rushFactor = 10;
        }

        int totalTime =

                basePrepTime
                        +
                        kitchenLoad
                        +
                        rushFactor;

        return "⏳ Estimated Preparation Time : "
                + totalTime + " mins";
    }

    // =====================================
    // SMART WAITING TIME
    // =====================================

    public String estimateWaitingTime() {

        long totalTables = 10;

        long occupiedTables =

                orderRepository.findAll()
                        .stream()

                        .filter(order ->

                                "ORDER_CONFIRMED"

                                        .equals(
                                                order.getStatus()))

                        .count();

        long availableTables =

                totalTables
                        -
                        occupiedTables;

        long queueLength =
                occupiedTables;

        int averageDiningTime = 7;

        int waitingTime;

        if(availableTables <= 0){

            waitingTime =

                    (int)(queueLength
                            *
                            averageDiningTime);

        } else {

            waitingTime =

                    (int)((queueLength
                            *
                            averageDiningTime)

                            / availableTables);
        }

        return "🚶 Estimated waiting time : "
                + waitingTime + " mins";
    }

    // =====================================
    // TRACK ORDER BY TABLE ID
    // =====================================

    public String trackOrder(
            Long tableId){

        List<Orders> orders =
                orderRepository.findAll();

        for(Orders order : orders){

            if(order.getTableId() != null &&

                    order.getTableId()
                            .equals(tableId)){

                List<OrderItem> items =

                        orderItemRepository
                                .findByOrderId(
                                        order.getOrderId());

                StringBuilder foods =
                        new StringBuilder();

                for(OrderItem item : items){

                    foods.append("🍔 ")
                            .append(item.getFoodName())
                            .append("\n");
                }

                return "📦 Order Status : "
                        + order.getStatus()

                        + "\n\n"

                        + foods;
            }
        }

        return "❌ No active order found";
    }

    // =====================================
    // CHECK ORDER DELAY
    // =====================================

    public String checkOrderDelay(
            Long tableId){

        List<Orders> orders =
                orderRepository.findAll();

        if(orders.isEmpty()){

            return "❌ No Orders Found";
        }

        for(Orders order : orders){

            if(order.getTableId() != null &&

                    order.getTableId()
                            .equals(tableId)){

                LocalDateTime currentTime =
                        LocalDateTime.now();

                LocalDateTime createdTime =
                        order.getCreatedTime();

                if(createdTime == null){

                    return "❌ Order time not found";
                }

                long minutes =

                        java.time.Duration

                                .between(
                                        createdTime,
                                        currentTime
                                )

                                .toMinutes();

                int expectedTime = 10;

                List<OrderItem> items =

                        orderItemRepository
                                .findByOrderId(
                                        order.getOrderId()
                                );

                String foodName =
                        "Food";

                if(items != null &&
                        !items.isEmpty()){

                    foodName =
                            items.get(0)
                                    .getFoodName();
                }

                if(minutes > expectedTime){

                    long delayMinutes =
                            minutes - expectedTime;

                    return

                            "🚨 DELAY ALERT\n\n"

                            +

                            "📢 CUSTOMER MESSAGE:\n"

                            +

                            "Your "

                            + foodName +

                            " order for Table "

                            + tableId +

                            " is delayed.\n\n"

                            +

                            "⏳ Delay Time : "

                            + delayMinutes +

                            " mins";
                }

                return "✅ Order preparation on time.";
            }
        }

        return "❌ No active order found";
    }

    // =====================================
    // TABLE ORDER PREPARATION TIME
    // =====================================

    public String tablePreparationTime(
            Long tableId){

        List<Orders> orders =
                orderRepository.findAll();

        StringBuilder response =
                new StringBuilder();

        int totalTime = 0;

        boolean tableFound = false;

        for(Orders order : orders){

            if(order.getTableId() != null &&

                    order.getTableId()
                            .equals(tableId)){

                tableFound = true;

                List<OrderItem> items =

                        orderItemRepository
                                .findByOrderId(
                                        order.getOrderId()
                                );

                for(OrderItem item : items){

                    String foodName =
                            item.getFoodName();

                    int prepTime = 5;

                    if(foodName.equalsIgnoreCase(
                            "Burger")){

                        prepTime = 10;
                    }

                    else if(foodName.equalsIgnoreCase(
                            "Pizza")){

                        prepTime = 20;
                    }

                    else if(foodName.equalsIgnoreCase(
                            "Mojito")){

                        prepTime = 5;
                    }

                    else if(foodName.equalsIgnoreCase(
                            "Pasta")){

                        prepTime = 15;
                    }

                    totalTime += prepTime;

                    response.append("🍔 ")
                            .append(foodName)
                            .append(" → ")
                            .append(prepTime)
                            .append(" mins\n");
                }
            }
        }

        if(!tableFound){

            return "❌ No orders found";
        }

        return

                "🪑 Table "
                + tableId +

                " Orders\n\n"

                +

                response +

                "\n⏳ Total Estimated Time : "

                + totalTime +

                " mins";
    }
}