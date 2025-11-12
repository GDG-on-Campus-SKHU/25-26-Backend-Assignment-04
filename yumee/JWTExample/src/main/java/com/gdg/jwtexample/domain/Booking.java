package com.gdg.jwtexample.domain;

import com.gdg.jwtexample.dto.BookingDto.BookingRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BOOKER_DATE", nullable = false)
    private LocalDate bookingDate;

    @Column(name = "BOOKER_TIME", nullable = false)
    private LocalTime bookingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Booking(Long id, LocalDate date, LocalTime time, User user) {
        this.id = id;
        this.bookingDate = date;
        this.bookingTime = time;
        this.user = user;
    }

    public void update(BookingRequestDto bookingRequestDto, User user) {
        this.bookingDate = bookingRequestDto.getBookingDate();
        this.bookingTime = bookingRequestDto.getBookingTime();
        this.user = user;
    }
}
