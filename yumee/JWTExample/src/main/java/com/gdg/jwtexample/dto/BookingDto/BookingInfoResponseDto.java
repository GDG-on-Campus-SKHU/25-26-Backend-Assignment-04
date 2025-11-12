package com.gdg.jwtexample.dto.BookingDto;

import com.gdg.jwtexample.domain.Booking;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
public class BookingInfoResponseDto {
    private Long id;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private Long userId;
    private String userName;

    public static BookingInfoResponseDto from(Booking booking) {
        return BookingInfoResponseDto.builder()
                .id(booking.getId())
                .bookingDate(booking.getBookingDate())
                .bookingTime(booking.getBookingTime())
                .userId(booking.getUser().getId())
                .userName(booking.getUser().getName())
                .build();
    }
}
