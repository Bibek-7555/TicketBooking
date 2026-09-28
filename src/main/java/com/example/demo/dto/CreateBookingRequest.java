package com.example.demo.dto;

import java.util.List;

public class CreateBookingRequest {
    

    public CreateBookingRequest() {
    }

    private Long customerId;
    private Long showId;
    private List<Long> seatIds;
    public CreateBookingRequest(Long customerId, Long showId, List<Long> seatIds) {
        this.customerId = customerId;
        this.showId = showId;
        this.seatIds = seatIds;
    }

    // Getters and setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public List<Long> getSeatIds() {
        return seatIds;
    }

    public void setSeatIds(List<Long> seatIds) {
        this.seatIds = seatIds;
    }
}
