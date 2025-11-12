package com.example.jwtexample.service;

import com.example.jwtexample.domain.Review;
import com.example.jwtexample.domain.Snack;
import com.example.jwtexample.domain.User;
import com.example.jwtexample.dto.ReviewInfoResponseDto;
import com.example.jwtexample.dto.ReviewSaveRequestDto;
import com.example.jwtexample.repository.ReviewRepository;
import com.example.jwtexample.repository.SnackRepository;
import com.example.jwtexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final UserRepository userRepository;
    private final SnackRepository snackRepository;
    private final ReviewRepository reviewRepository;

    public ReviewInfoResponseDto createReview(ReviewSaveRequestDto dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        Snack snack = snackRepository.findById(dto.getSnackId())
                .orElseThrow(() -> new RuntimeException("스낵을 찾을 수 없습니다."));

        Review review = reviewRepository.save(dto.toEntity(user, snack));

        return ReviewInfoResponseDto.builder()
                .id(review.getId())
                .content(review.getContent())
                .score(review.getScore())
                .writer(review.getUser().getName())
                .build();
    }



    public void addReview(Long userId, Long snackId, int score) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을수없음"));

        Snack snack = snackRepository.findById(snackId)
                .orElseThrow(() -> new RuntimeException("과자를 찾을수없음"));

        Review review = Review.builder()
                .user(user)
                .snack(snack)
                .score(score)
                .build();

        reviewRepository.save(review);
    }
}

