package com.gdg.jwtexample.controller;

import com.gdg.jwtexample.dto.BookingDto.BookingInfoResponseDto;
import com.gdg.jwtexample.dto.BookingDto.BookingRequestDto;
import com.gdg.jwtexample.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingInfoResponseDto> booking(@RequestBody BookingRequestDto bookingRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.booking(bookingRequestDto));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingInfoResponseDto> getBooking(@PathVariable Long bookingId) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBooking(bookingId));
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<BookingInfoResponseDto> updateBooking(@PathVariable Long bookingId,
                                                                @RequestBody BookingRequestDto bookingRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.updateBooking(bookingId, bookingRequestDto));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<BookingInfoResponseDto> deleteBooking(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<BookingInfoResponseDto>> getAllBookings() {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getAllBooking());
    }
}
