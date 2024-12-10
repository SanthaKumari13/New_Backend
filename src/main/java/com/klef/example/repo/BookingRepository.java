package com.klef.example.repo;

import com.klef.example.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsByCounsellorIdAndBookingDate(Long counsellorId, LocalDateTime bookingDate);
    List<Booking> findByCounsellorId(Long counsellorId);
    public List<Booking> findByUserId(Long userId);

}
