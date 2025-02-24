package com.pragma.traceability.domain.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class StateOrder {
    @Id
    private String id;
    private long orderId;
    private String state;
    private LocalDateTime date;

    public StateOrder(long orderId, String state, LocalDateTime date) {
        this.orderId = orderId;
        this.state = state;
        this.date = date;
    }

    public String getId() {
        return id;
    }

        public void setId(String  id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
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
