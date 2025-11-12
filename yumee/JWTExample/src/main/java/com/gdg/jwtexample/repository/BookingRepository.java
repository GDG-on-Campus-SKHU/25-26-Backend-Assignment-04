package com.gdg.jwtexample.repository;

import com.gdg.jwtexample.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
