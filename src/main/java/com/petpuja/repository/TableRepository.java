package com.petpuja.repository;

import com.petpuja.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository
        extends JpaRepository<RestaurantTable, Long> {
}