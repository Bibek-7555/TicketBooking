package com.example.demo.dto;

public class UpdateRequest {
    

    private String customerName;
    private String movieName;
    private String seatNumber;

    public UpdateRequest() {
        
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}
