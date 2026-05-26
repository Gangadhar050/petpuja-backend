package com.petpuja.repository;

import com.petpuja.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository
        extends JpaRepository<Cart, Long> {

    List<Cart> findByTableId(Long tableId);
}
