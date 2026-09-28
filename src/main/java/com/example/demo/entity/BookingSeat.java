package com.example.demo.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity 
public class BookingSeat {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne 
    @JoinColumn (name = "booking_id")
    private Booking booking;

    @ManyToOne 
    @JoinColumn (name = "seat_id")
    private Seat seat;

    public BookingSeat() {
    }

    public BookingSeat(Booking booking, Seat seat) {
        this.booking = booking;
        this.seat = seat;
    }

    public Long getId() {
        return id;
    }

    public Booking getBooking() {
        return booking;
    }

    public Seat getSeat() {
        return seat;
    }
}
