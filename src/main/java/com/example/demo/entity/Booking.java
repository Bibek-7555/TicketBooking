package com.example.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;
import java.util.ArrayList;



@Entity
public class Booking {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus = BookingStatus.Pending;
    private LocalDateTime bookingTime;

    @ManyToOne 
    @JoinColumn (name = "customer_id")
    private Customer customer;
    
    @ManyToOne 
    @JoinColumn (name = "show_id")
    private Show show;

    @OneToMany (mappedBy = "booking")
    private List<BookingSeat> bookingSeats = new ArrayList<>();

    public Booking() {
        this.bookingTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public List<BookingSeat> getBookingSeats() {
        return bookingSeats;
    }

}
