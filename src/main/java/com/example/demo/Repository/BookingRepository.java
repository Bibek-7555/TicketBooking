package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.demo.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomerName(String customerName);
    List<Booking> findByCustomerNameAndMovieName(
        String customerName,
        String movieName
    );

    @Query("""
    SELECT b
    FROM Booking b
    WHERE b.movieName = :movieName
    """)
    List<Booking> findBookingsByMovieName(
        @Param("movieName") String movieName
    );
}
