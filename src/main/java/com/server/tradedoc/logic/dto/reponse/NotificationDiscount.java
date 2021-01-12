package com.server.tradedoc.logic.dto.reponse;

public class NotificationDiscount {
    private Integer timeRemaining;
    private String message;

    public Integer getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(Integer timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
