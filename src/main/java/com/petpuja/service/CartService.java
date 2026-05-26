package com.petpuja.service;

import com.petpuja.dto.CartRequestDTO;
import com.petpuja.model.Cart;
import com.petpuja.repository.CartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    // =====================================
    // ADD TO CART
    // =====================================

    public Cart addToCart(CartRequestDTO dto){

        Cart cart = new Cart();

        cart.setTableId(dto.getTableId());

        cart.setFoodName(dto.getFoodName());

        cart.setQuantity(dto.getQuantity());

        cart.setPrice(dto.getPrice());

        cart.setTotalPrice(
                dto.getQuantity() *
                dto.getPrice());

        return cartRepository.save(cart);
    }

    // =====================================
    // GET CART ITEMS
    // =====================================

    public List<Cart> getCart(Long tableId){

        return cartRepository
                .findByTableId(tableId);
    }

    // =====================================
    // REMOVE ITEM
    // =====================================

    public String removeItem(Long cartId){

        cartRepository.deleteById(cartId);

        return "🗑️ Item removed from cart";
    }

    // =====================================
    // UPDATE QUANTITY
    // =====================================

    public Cart updateQuantity(
            Long cartId,
            Integer quantity){

        Cart cart =
                cartRepository.findById(cartId)
                        .orElse(null);

        if(cart == null){
            return null;
        }

        cart.setQuantity(quantity);

        cart.setTotalPrice(
                quantity *
                cart.getPrice());

        return cartRepository.save(cart);
    }

    // =====================================
    // CALCULATE TOTAL PRICE
    // =====================================

    public Double calculateTotal(Long tableId){

        List<Cart> items =
                cartRepository
                        .findByTableId(tableId);

        double total = 0;

        for(Cart item : items){

            total += item.getTotalPrice();
        }

        return total;
    }
}