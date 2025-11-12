package com.gdg.jwtexample.service;

import com.gdg.jwtexample.domain.Booking;
import com.gdg.jwtexample.domain.User;
import com.gdg.jwtexample.dto.BookingDto.BookingInfoResponseDto;
import com.gdg.jwtexample.dto.BookingDto.BookingRequestDto;
import com.gdg.jwtexample.exception.CustomErrorCode;
import com.gdg.jwtexample.exception.CustomException;
import com.gdg.jwtexample.repository.BookingRepository;
import com.gdg.jwtexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Transactional
    public BookingInfoResponseDto booking(BookingRequestDto bookingRequestDto) {
        User user = userRepository.findById(bookingRequestDto.getUserId())
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND_EXCEPTION));

        Booking booking = Booking.builder()
                .bookingDate(bookingRequestDto.getBookingDate())
                .bookingTime(bookingRequestDto.getBookingTime())
                .user(user)
                .build();

        bookingRepository.save(booking);

        userService.updateRole(user.getId());

        return BookingInfoResponseDto.from(booking);
    }

    @Transactional(readOnly = true)
    public BookingInfoResponseDto getBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.BOOKING_NOT_FOUND_EXCEPTION));

        return BookingInfoResponseDto.from(booking);
    }

    @Transactional(readOnly = true)
    public List<BookingInfoResponseDto> getAllBooking() {
        return bookingRepository.findAll()
                .stream()
                .map(BookingInfoResponseDto::from)
                .toList();
    }

    @Transactional
    public BookingInfoResponseDto updateBooking(Long id, BookingRequestDto bookingRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND_EXCEPTION));

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomErrorCode.BOOKING_NOT_FOUND_EXCEPTION));

        booking.update(bookingRequestDto, user);

        return BookingInfoResponseDto.from(booking);
    }

    @Transactional
    public void deleteBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.BOOKING_NOT_FOUND_EXCEPTION));

        bookingRepository.delete(booking);
    }
}
