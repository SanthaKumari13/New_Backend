package com.klef.example.controller;

import com.klef.example.entity.Booking;
import com.klef.example.entity.Counsellor;
import com.klef.example.entity.User;
import com.klef.example.services.BookingService;
import com.klef.example.services.CounsellorService;
import com.klef.example.services.UserService;
import com.klef.example.utils.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private CounsellorService counsellorService;

    @PostMapping("/create")
    public ResponseEntity<String> createBooking(
            @RequestParam("userId") Long userId,
            @RequestParam("counsellorId") Long counsellorId,
            @RequestParam("bookingDate") String bookingDate) {

        User user = userService.getUserById(userId);
        Counsellor counsellor = counsellorService.findCounsellorById(counsellorId);

        if (user == null || counsellor == null) {
            return new ResponseEntity<>("Invalid user or counsellor", HttpStatus.BAD_REQUEST);
        }

        try {
            LocalDateTime bookingDateTime = LocalDateTime.parse(bookingDate);

            // Check if the session is already booked for the selected time slot
            if (bookingService.isSlotBooked(counsellorId, bookingDateTime)) {
                return new ResponseEntity<>("Time slot already booked", HttpStatus.CONFLICT);
            }

            // Generate a unique token for this booking
            String token = TokenGenerator.generateToken();

            // Create the booking session
            bookingService.createBooking(user, counsellor, bookingDateTime, token);

            return new ResponseEntity<>("Booking successful! Token: " + token, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Error creating booking", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/user")
    public ResponseEntity<List<Booking>> getBookingsByUser(@RequestParam("userId") Long userId) {
        List<Booking> bookings = bookingService.getBookingsByUserId(userId);
        if (bookings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookings);
    }
   



}
