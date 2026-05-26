package com.petpuja.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RestaurantTable {

    @Id
    private Long tableId;

    private String qrCode;

    // AVAILABLE / OCCUPIED

    private String tableStatus;

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(String tableStatus) {
        this.tableStatus = tableStatus;
    }
}

