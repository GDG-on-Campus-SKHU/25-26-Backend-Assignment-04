package com.example.jwtexample.repository;

import com.example.jwtexample.domain.Snack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnackRepository extends JpaRepository<Snack, Long> {
}

