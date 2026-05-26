package com.petpuja.service;

import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;

@Service
public class QueueService {

    // WAITING TABLE LIST

    private final List<Long> waitingTables =
            new ArrayList<>();

    // =====================================
    // ADD TABLE TO WAITING QUEUE
    // =====================================

    public String addTable(Long tableId){

        // DUPLICATE CHECK

        if(waitingTables.contains(tableId)){

            return "⚠️ Table already in waiting queue";
        }

        // ADD TABLE

        waitingTables.add(tableId);

        // ESTIMATED WAIT TIME

        int estimatedTime =
                waitingTables.size() * 7;

        return "✅ Table " + tableId +

                " added to waiting queue. " +

                "Estimated waiting time : " +

                estimatedTime + " mins";
    }

    // =====================================
    // REMOVE TABLE FROM QUEUE
    // =====================================

    public String removeTable(Long tableId){

        if(waitingTables.remove(tableId)){

            return "✅ Table removed from queue";
        }

        return "❌ Table not found in queue";
    }

    // =====================================
    // AI SMART QUEUE PREDICTION
    // =====================================

    public Map<String, Object> getQueuePrediction(){

        Map<String, Object> response =
                new HashMap<>();

        // QUEUE SIZE

        int queueSize =
                waitingTables.size();

        // BASE WAIT TIME

        int estimatedWaitTime =
                queueSize * 7;

        // CURRENT TIME

        LocalTime now =
                LocalTime.now();

        // RUSH HOUR DETECTION

        boolean lunchRush =
                now.getHour() >= 12 &&
                now.getHour() <= 15;

        boolean dinnerRush =
                now.getHour() >= 19 &&
                now.getHour() <= 22;

        boolean rushHour =
                lunchRush || dinnerRush;

        // EXTRA WAIT TIME

        if(rushHour){

            estimatedWaitTime += 10;
        }

        // CROWD LEVEL

        String crowdLevel;

        if(queueSize <= 2){

            crowdLevel = "LOW";

        } else if(queueSize <= 5){

            crowdLevel = "MEDIUM";

        } else {

            crowdLevel = "HIGH";
        }

        // NEXT TABLE FREE

        int nextTableFreeIn =
                estimatedWaitTime / 2;

        // RESTAURANT STATUS

        String restaurantStatus;

        if(queueSize == 0){

            restaurantStatus = "TABLES AVAILABLE";

        } else if(queueSize <= 3){

            restaurantStatus = "MODERATE RUSH";

        } else {

            restaurantStatus = "FULLY BUSY";
        }

        // RESPONSE

        response.put(
                "waitingTables",
                waitingTables);

        response.put(
                "queueSize",
                queueSize);

        response.put(
                "estimatedWaitTime",
                estimatedWaitTime + " mins");

        response.put(
                "crowdLevel",
                crowdLevel);

        response.put(
                "rushHour",
                rushHour);

        response.put(
                "nextTableFreeIn",
                nextTableFreeIn + " mins");

        response.put(
                "restaurantStatus",
                restaurantStatus);

        return response;
    }
}