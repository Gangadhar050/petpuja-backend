package com.petpuja.dto;

public class ScanResponseDTO {

    private String welcomeMessage;

    private Long tableNumber;

    private String menuApi;

    private String tableStatus;

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public Long getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Long tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getMenuApi() {
        return menuApi;
    }

    public void setMenuApi(String menuApi) {
        this.menuApi = menuApi;
    }

    public String getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(String tableStatus) {
        this.tableStatus = tableStatus;
    }
}

