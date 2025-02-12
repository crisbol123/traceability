package com.pragma.traceability.domain.model;

import java.time.LocalDateTime;

public class Traceability {
    private Long orderId;
    private String state;
    private LocalDateTime date;

    public Traceability(Long orderId, String state, LocalDateTime date) {
        this.orderId = orderId;
        this.state = state;
        this.date = date;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
