package com.gdg.jwtexample.repository;

import com.gdg.jwtexample.domain.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> { }
