package com.petpuja.controller;

import com.petpuja.dto.CartRequestDTO;
import com.petpuja.model.Cart;
import com.petpuja.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    // =====================================
    // ADD TO CART
    // =====================================

    @PostMapping
    public Cart addToCart(
            @RequestBody CartRequestDTO dto){

        return cartService.addToCart(dto);
    }

    // =====================================
    // GET CART
    // =====================================

    @GetMapping("/{tableId}")
    public List<Cart> getCart(
            @PathVariable Long tableId){

        return cartService.getCart(tableId);
    }

    // =====================================
    // REMOVE ITEM
    // =====================================

    @DeleteMapping("/{cartId}")
    public String removeItem(
            @PathVariable Long cartId){

        return cartService.removeItem(cartId);
    }

    // =====================================
    // UPDATE QUANTITY
    // =====================================

    @PutMapping("/quantity")
    public Cart updateQuantity(
            @RequestParam Long cartId,
            @RequestParam Integer quantity){

        return cartService
                .updateQuantity(
                        cartId,
                        quantity);
    }

    // =====================================
    // TOTAL PRICE
    // =====================================

    @GetMapping("/total/{tableId}")
    public Double totalPrice(
            @PathVariable Long tableId){

        return cartService
                .calculateTotal(tableId);
    }
}