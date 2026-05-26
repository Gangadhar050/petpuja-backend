package com.petpuja.service;

import com.petpuja.dto.AiFoodSuggestionDTO;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AiFoodSuggestionService {

    // =====================================
    // AI FOOD SUGGESTIONS
    // =====================================

    public List<AiFoodSuggestionDTO>
    getSuggestions(

            Double minAmount,
            Double maxAmount){

        // TEMP MENU LIST

        List<AiFoodSuggestionDTO> menu =
                new ArrayList<>();

        // MENU ITEMS

        menu.add(

                new AiFoodSuggestionDTO(

                        "Burger",
                        150.0,
                        "Fast Food",
                        "Budget friendly"
                )
        );

        menu.add(

                new AiFoodSuggestionDTO(

                        "Pizza",
                        350.0,
                        "Pizza",
                        "Trending food"
                )
        );

        menu.add(

                new AiFoodSuggestionDTO(

                        "Mojito",
                        250.0,
                        "Drink",
                        "Refreshing drink"
                )
        );

        menu.add(

                new AiFoodSuggestionDTO(

                        "Brownie",
                        220.0,
                        "Dessert",
                        "Most loved dessert"
                )
        );

        menu.add(

                new AiFoodSuggestionDTO(

                        "Grill Chicken",
                        650.0,
                        "Premium",
                        "Chef special"
                )
        );

        // FINAL AI RESULT

        List<AiFoodSuggestionDTO>
                suggestions =
                new ArrayList<>();

        // FILTER BASED ON AMOUNT

        for(AiFoodSuggestionDTO item
                : menu){

            if(item.getPrice() >= minAmount

                    &&

               item.getPrice() <= maxAmount){

                suggestions.add(item);
            }
        }

        return suggestions;
    }
}