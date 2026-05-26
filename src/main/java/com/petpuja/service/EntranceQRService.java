package com.petpuja.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.springframework.stereotype.Service;

import java.nio.file.FileSystems;
import java.nio.file.Path;

@Service
public class EntranceQRService {

    // =====================================
    // GENERATE ONE ENTRANCE QR
    // =====================================

    public String generateEntranceQR() {

        // ONLY ONE ENTRANCE QR

        String qrUrl =
                "https://petpuja-backend.onrender.com/entrance";

        try {

            QRCodeWriter qrCodeWriter =
                    new QRCodeWriter();

            BitMatrix bitMatrix =
                    qrCodeWriter.encode(
                            qrUrl,
                            BarcodeFormat.QR_CODE,
                            600,
                            600);

            // SAVE QR IMAGE

            String filePath =
                    "qr-codes/restaurant-entrance.png";

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

        return "✅ Entrance QR Generated";
    }
}