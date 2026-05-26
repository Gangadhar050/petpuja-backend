package com.petpuja.controller;

import com.petpuja.dto.ScanResponseDTO;
import com.petpuja.model.RestaurantTable;
import com.petpuja.service.TableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TableController {

    @Autowired
    private TableService tableService;

    // =====================================
    // ADMIN CREATE TABLE
    // =====================================

    @PostMapping("/table/{tableId}")
    public RestaurantTable createTable(
            @PathVariable Long tableId){

        return tableService
                .createTable(tableId);
    }

    // =====================================
    // CUSTOMER SCAN QR
    // =====================================

    @GetMapping("/scan/{tableId}")
    public ScanResponseDTO scanQR(
            @PathVariable Long tableId){

        return tableService
                .scanTable(tableId);
    }

    // =====================================
    // CUSTOMER ORDER + PAYMENT
    // TABLE STATUS = OCCUPIED
    // =====================================

    @PutMapping("/table/occupy/{tableId}")
    public String occupyTable(
            @PathVariable Long tableId){

        return tableService
                .occupyTable(tableId);
    }

    // =====================================
    // CUSTOMER LEFT TABLE
    // STAFF / ADMIN CLICK FREE
    // TABLE STATUS = AVAILABLE
    // =====================================

    @PutMapping("/table/free/{tableId}")
    public String freeTable(
            @PathVariable Long tableId){

        return tableService
                .freeTable(tableId);
    }

    // =====================================
    // ADMIN & STAFF VIEW TABLE STATUS
    // =====================================

    @GetMapping("/tables")
    public List<RestaurantTable> getAllTables(){

        return tableService
                .getAllTables();
    }
}


