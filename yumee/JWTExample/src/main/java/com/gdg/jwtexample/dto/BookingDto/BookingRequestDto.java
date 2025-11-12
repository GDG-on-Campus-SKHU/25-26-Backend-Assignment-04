package com.gdg.jwtexample.dto.BookingDto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class BookingRequestDto {
    private Long userId;
    @NotNull(message = "날짜는 필수입니다.")
    private LocalDate bookingDate;
    @NotNull(message = "시간은 필수입니다.")
    private LocalTime bookingTime;
}
