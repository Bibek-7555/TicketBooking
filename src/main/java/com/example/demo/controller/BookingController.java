package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.example.demo.dto.BookingResponse;
import com.example.demo.service.BookingService;
import com.example.demo.dto.CreateBookingRequest;
import com.example.demo.dto.UpdateRequest;
import com.example.demo.Exception.BookingNotFoundException;

@RestController 
@RequestMapping ("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping ("/{bookingId}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable Long bookingId) {
        // Implementation for fetching a specific booking by ID
        return ResponseEntity.ok(bookingService.getBookingByID(bookingId));
    }

    @GetMapping (params = "customerName")
    public ResponseEntity<List<BookingResponse>> getBookingByCustomerName(@RequestParam String customerName) {
        System.out.println("Customer Name: " + customerName);
        // Implementation for fetching a booking by customer name
        List<BookingResponse> bookingResponses = bookingService.getBookingByCustomerName(customerName);
        return ResponseEntity.ok(bookingResponses);
    }

    @GetMapping (params = {"customerName", "movieName"})
    public ResponseEntity<List<BookingResponse>> getBookingByCustomerNameAndMovieName(
        @RequestParam String customerName,
        @RequestParam String movieName
    ) {
        System.out.println("Customer Name: " + customerName);
        System.out.println("Movie Name: " + movieName);
        // Implementation for fetching a booking by customer name and movie name
        List<BookingResponse> bookingResponses = bookingService.getBookingByCustomerNameAndMovieName(customerName, movieName);
        return ResponseEntity.ok(bookingResponses);
    }

    @GetMapping (params = "movieName")
    public ResponseEntity<List<BookingResponse>> getBookingByMovieName(@RequestParam String movieName) {
        System.out.println("Movie Name: " + movieName);
        // Implementation for fetching a booking by movie name
        List<BookingResponse> bookingResponses = bookingService.getBookingByMovieName(movieName);
        return ResponseEntity.ok(bookingResponses);
    }

    @PostMapping 
    public ResponseEntity<BookingResponse> createBooking(@RequestBody CreateBookingRequest createBookingRequest) {
        System.out.println("POST CREATE BOOKING");
        BookingResponse bookingResponse = bookingService.createBooking(createBookingRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingResponse);
    }

    @PutMapping ("/{bookingId}")
    public ResponseEntity<BookingResponse> updateBooking(@PathVariable Long bookingId, @RequestBody UpdateRequest updateRequest) {
        BookingResponse updatedBooking = bookingService.updateBooking(bookingId, updateRequest);
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping ("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
}
