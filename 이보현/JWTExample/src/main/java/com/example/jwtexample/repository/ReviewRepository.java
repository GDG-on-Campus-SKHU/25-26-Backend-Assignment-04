package com.example.jwtexample.repository;

import com.example.jwtexample.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT AVG(r.score) FROM Review r WHERE r.snack.id = :snackId")
    Double getAverageScoreBySnack(@Param("snackId") Long snackId);
}

