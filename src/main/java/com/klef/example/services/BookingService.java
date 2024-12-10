package com.klef.example.services;

import com.klef.example.entity.Booking;
import com.klef.example.entity.Counsellor;
import com.klef.example.entity.User;
import com.klef.example.repo.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Check if the time slot is already booked
    public boolean isSlotBooked(Long counsellorId, LocalDateTime bookingDateTime) {
        return bookingRepository.existsByCounsellorIdAndBookingDate(counsellorId, bookingDateTime);
    }

    // Create a new booking
    public void createBooking(User user, Counsellor counsellor, LocalDateTime bookingDateTime, String token) {
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setCounsellor(counsellor);
        booking.setBookingDate(bookingDateTime);
        booking.setStatus("Booked");
        // Store the unique token
        booking.setStatus("Booked - " + token);
        bookingRepository.save(booking);
    }
    public List<LocalDateTime> getBookedSlots(Long counsellorId) {
        List<Booking> bookings = bookingRepository.findByCounsellorId(counsellorId);
        return bookings.stream()
                .map(Booking::getBookingDate)
                .collect(Collectors.toList());
    }
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }


}
