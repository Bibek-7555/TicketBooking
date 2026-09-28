package com.example.demo.dto;
import com.example.demo.entity.BookingStatus;
import java.util.List;

public class BookingResponse {
    
    private String bookingId;
    private String customerName;
    private String movieName;
    private java.time.LocalDateTime showTime;
    private List<String> seats;
    private BookingStatus bookingStatus;
    private String email;
    public BookingResponse() {
        
    }

    public BookingResponse(Long bookingId, String customerName, String email, String movieName, java.time.LocalDateTime showTime, List<String> seats, BookingStatus bookingStatus) {
        this.bookingId = bookingId.toString();
        this.customerName = customerName;
        this.email = email;
        this.movieName = movieName;
        this.showTime = showTime;
        this.seats = seats;
        this.bookingStatus = bookingStatus;
    }

    // Getters and setters
    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public java.time.LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(java.time.LocalDateTime showTime) {
        this.showTime = showTime;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
