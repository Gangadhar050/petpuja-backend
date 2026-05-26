package com.petpuja.service;

import com.petpuja.dto.TopSellingFoodDTO;
import com.petpuja.model.OrderItem;
import com.petpuja.repository.OrderItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TopSellingFoodService {

    @Autowired
    private OrderItemRepository
            orderItemRepository;

    // =====================================
    // TOP SELLING FOODS
    // =====================================

    public List<TopSellingFoodDTO>
    topSellingFoods(){

        // GET ALL ITEMS

        List<OrderItem> items =
                orderItemRepository.findAll();

        // STORE TOTAL QUANTITY

        Map<String, Integer> quantityMap =
                new HashMap<>();

        // STORE TOTAL REVENUE

        Map<String, Double> revenueMap =
                new HashMap<>();

        // LOOP ITEMS

        for(OrderItem item : items){

            String foodName =
                    item.getFoodName();

            Integer quantity =
                    item.getQuantity();

            Double price =
                    item.getPrice();

            // NULL SAFETY

            if(foodName == null){

                continue;
            }

            if(quantity == null){

                quantity = 0;
            }

            if(price == null){

                price = 0.0;
            }

            // ADD QUANTITY

            quantityMap.put(

                    foodName,

                    quantityMap.getOrDefault(
                            foodName,
                            0)

                            + quantity
            );

            // ADD REVENUE

            revenueMap.put(

                    foodName,

                    revenueMap.getOrDefault(
                            foodName,
                            0.0)

                            + (price * quantity)
            );
        }

        // FINAL RESPONSE

        List<TopSellingFoodDTO> response =
                new ArrayList<>();

        // MAP -> DTO

        for(String food :
                quantityMap.keySet()){

            TopSellingFoodDTO dto =
                    new TopSellingFoodDTO();

            dto.setFoodName(food);

            dto.setTotalQuantitySold(

                    quantityMap.get(food)
            );

            dto.setTotalRevenue(

                    revenueMap.get(food)
            );

            response.add(dto);
        }

        // SORT DESCENDING

        response.sort(

                (a, b) ->

                        b.getTotalQuantitySold()
                                .compareTo(

                                        a.getTotalQuantitySold()
                                )
        );

        return response;
    }
}