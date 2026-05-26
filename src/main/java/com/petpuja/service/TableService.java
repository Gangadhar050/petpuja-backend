package com.petpuja.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import com.petpuja.dto.ScanResponseDTO;
import com.petpuja.model.RestaurantTable;
import com.petpuja.repository.TableRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    // =====================================
    // ADMIN CREATE TABLE + QR
    // =====================================

    public RestaurantTable createTable(Long tableId) {

        RestaurantTable table =
                new RestaurantTable();

        // TABLE ID

        table.setTableId(tableId);

        // DEFAULT STATUS

        table.setTableStatus("AVAILABLE");

        // =====================================
        // ONLINE QR URL
        // =====================================

        String qrUrl =
                "https://petpuja-backend.onrender.com/scan/"
                        + tableId;

        // SAVE QR URL

        table.setQrCode(qrUrl);

        // =====================================
        // GENERATE REAL QR IMAGE
        // =====================================

        try {

            QRCodeWriter qrCodeWriter =
                    new QRCodeWriter();

            BitMatrix bitMatrix =
                    qrCodeWriter.encode(
                            qrUrl,
                            BarcodeFormat.QR_CODE,
                            600,
                            600);

            // =====================================
            // QR IMAGE SAVE LOCATION
            // =====================================

            String filePath =
                    "C:/qr-codes/table-"
                            + tableId + ".png";

            Path path =
                    FileSystems
                            .getDefault()
                            .getPath(filePath);

            MatrixToImageWriter
                    .writeToPath(
                            bitMatrix,
                            "PNG",
                            path);

        } catch (Exception e){

            e.printStackTrace();
        }

        // SAVE DATABASE

        return tableRepository.save(table);
    }

    // =====================================
    // CUSTOMER SCAN QR
    // =====================================

    public ScanResponseDTO scanTable(Long tableId) {

        RestaurantTable table =
                tableRepository
                        .findById(tableId)
                        .orElse(null);

        if(table == null) {

            return null;
        }

        ScanResponseDTO response =
                new ScanResponseDTO();

        // WELCOME MESSAGE

        response.setWelcomeMessage(
                "Welcome To PETPUJA 🍽️"
        );

        // TABLE NUMBER

        response.setTableNumber(
                table.getTableId()
        );

        // MENU API

        response.setMenuApi(
                "https://petpuja-backend.onrender.com/menu"
        );

        // TABLE STATUS

        response.setTableStatus(
                table.getTableStatus()
        );

        return response;
    }

    // =====================================
    // CUSTOMER ORDER + PAYMENT
    // TABLE STATUS = OCCUPIED
    // =====================================

    public String occupyTable(Long tableId){

        RestaurantTable table =
                tableRepository
                        .findById(tableId)
                        .orElse(null);

        if(table == null){

            return "❌ Table Not Found";
        }

        table.setTableStatus("OCCUPIED");

        tableRepository.save(table);

        return "✅ Table Occupied";
    }

    // =====================================
    // CUSTOMER LEFT TABLE
    // STAFF / ADMIN FREE TABLE
    // =====================================

    public String freeTable(Long tableId){

        RestaurantTable table =
                tableRepository
                        .findById(tableId)
                        .orElse(null);

        if(table == null){

            return "❌ Table Not Found";
        }

        table.setTableStatus("AVAILABLE");

        tableRepository.save(table);

        return "✅ Table Available";
    }

    // =====================================
    // ADMIN & STAFF VIEW TABLE STATUS
    // =====================================

    public List<RestaurantTable> getAllTables(){

        return tableRepository.findAll();
    }
}
