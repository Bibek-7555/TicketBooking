package com.example.demo.Repository;

import com.example.demo.entity.BookingSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingSeatRepository extends JpaRepository<BookingSeat, Long> {
    boolean existsByBookingShowIdAndSeatId(Long showId, Long seatId);
}