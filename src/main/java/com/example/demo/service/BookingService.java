package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.dto.BookingResponse;
import com.example.demo.dto.CreateBookingRequest;
import com.example.demo.dto.UpdateRequest;
import com.example.demo.entity.Booking;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Seat;
import com.example.demo.entity.Show;

import jakarta.transaction.Transactional;

import com.example.demo.Exception.BookingNotFoundException;
import com.example.demo.Repository.BookingRepository;
import com.example.demo.Repository.BookingSeatRepository;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Repository.SeatRepository;
import com.example.demo.Repository.ShowRepository;


@Service 
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final ShowRepository showRepository;
    private final SeatRepository seatRepository;
    private final BookingSeatRepository bookingSeatRepository;

    public BookingService(BookingRepository bookingRepository, CustomerRepository customerRepository, ShowRepository showRepository, SeatRepository seatRepository, BookingSeatRepository bookingSeatRepository) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.showRepository = showRepository;
        this.seatRepository = seatRepository;
        this.bookingSeatRepository = bookingSeatRepository;
    }

    public BookingResponse createBooking(CreateBookingRequest createBookingRequest) {
        Customer customer = customerRepository.findById(createBookingRequest.getCustomerId())
                .orElseThrow(() -> new BookingNotFoundException("Customer not found for ID: " + createBookingRequest.getCustomerId()));

        Show show = showRepository.findById(createBookingRequest.getShowId())
                .orElseThrow(() -> new BookingNotFoundException("Show not found for ID: " + createBookingRequest.getShowId()));

        List<Seat> seats = seatRepository.findAllById(createBookingRequest.getSeatIds());
        if (seats.size() != createBookingRequest.getSeatIds().size()) {
            throw new BookingNotFoundException("One or more seats not found");
        }

        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setShow(show);
        booking.setSeats(seats);

        Booking savedBooking = bookingRepository.save(booking);

        return new BookingResponse(savedBooking.getId(), savedBooking.getCustomerId(), savedBooking.getShowId(), savedBooking.getSeatIds());
    }

    public List<BookingResponse> getAllBookings() {
        // List<Booking> bookings = bookingRepository.findAll();
        return bookingRepository.findAll().stream()
        .map(booking -> new BookingResponse(booking.getId(), booking.getCustomerName(), booking.getMovieName(), booking.getSeatNumber()))
        .toList()  ;
    }

    public BookingResponse getBookingByID(Long bookingID) {
        Booking booking = bookingRepository.findById(bookingID).orElseThrow(() -> new BookingNotFoundException("Booking not found for ID: " + bookingID));
        return new BookingResponse(booking.getId(), booking.getCustomerName(), booking.getMovieName(), booking.getSeatNumber());
    }

    @Transactional 
    public BookingResponse updateBooking(Long bookingId, UpdateRequest updateRequest) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found for ID: " + bookingId));

        // Update the booking details
        booking.setCustomerName(updateRequest.getCustomerName());
        booking.setMovieName(updateRequest.getMovieName());
        booking.setSeatNumber(updateRequest.getSeatNumber());

        // Save the updated booking
        // Booking updatedBooking = bookingRepository.save(booking);

        return new BookingResponse(booking.getId(), booking.getCustomerName(), booking.getMovieName(), booking.getSeatNumber());
    }


    @Transactional 
    public void deleteBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found for ID: " + bookingId));

        bookingRepository.delete(booking);
    }
    public List<BookingResponse> getBookingByCustomerName(String customerName) {
        List<Booking> bookings = bookingRepository.findByCustomerName(customerName);
        if (bookings.isEmpty()) {
            throw new BookingNotFoundException("Booking not found for customer name: " + customerName);
        }
        List<BookingResponse> bookingResponses = new ArrayList<>();
        for (Booking booking : bookings) {
            bookingResponses.add(new BookingResponse(booking.getId(), booking.getCustomerName(), booking.getMovieName(), booking.getSeatNumber()));
        }
        return bookingResponses;
    }

    public List<BookingResponse> getBookingByCustomerNameAndMovieName(String customerName, String movieName) {
        List<Booking> bookings = bookingRepository.findByCustomerNameAndMovieName(customerName, movieName);
        if (bookings.isEmpty()) {
            throw new BookingNotFoundException("Booking not found for customer name: " + customerName + " and movie name: " + movieName);
        }
        List<BookingResponse> bookingResponses = new ArrayList<>();
        for (Booking booking : bookings) {
            bookingResponses.add(new BookingResponse(booking.getId(), booking.getCustomerName(), booking.getMovieName(), booking.getSeatNumber()));
        }
        return bookingResponses;
    }

    public List<BookingResponse> getBookingByMovieName(String movieName) {
        List<Booking> bookings = bookingRepository.findBookingsByMovieName(movieName);
        if (bookings.isEmpty()) {
            throw new BookingNotFoundException("Booking not found for movie name: " + movieName);
        }
        List<BookingResponse> bookingResponses = new ArrayList<>();
        for (Booking booking : bookings) {
            bookingResponses.add(new BookingResponse(booking.getId(), booking.getCustomerName(), booking.getMovieName(), booking.getSeatNumber()));
        }
        return bookingResponses;
    }

}
