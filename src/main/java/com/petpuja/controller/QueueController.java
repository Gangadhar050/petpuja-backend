package com.petpuja.controller;

import com.petpuja.service.QueueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/queue")
@CrossOrigin(origins = "*")
public class QueueController {

    @Autowired
    private QueueService queueService;

    // =====================================
    // ADD TABLE TO QUEUE
    // =====================================

    @PostMapping
    public String addQueue(
            @RequestParam Long tableId){

        return queueService
                .addTable(tableId);
    }

    // =====================================
    // REMOVE TABLE FROM QUEUE
    // =====================================

    @DeleteMapping
    public String removeQueue(
            @RequestParam Long tableId){

        return queueService
                .removeTable(tableId);
    }

    // =====================================
    // AI QUEUE PREDICTION
    // =====================================

    @GetMapping("/status")
    public Map<String, Object> queueStatus(){

        return queueService
                .getQueuePrediction();
    }
}