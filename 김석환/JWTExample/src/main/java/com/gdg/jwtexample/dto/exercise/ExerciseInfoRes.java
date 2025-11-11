package com.gdg.jwtexample.dto.exercise;

import com.gdg.jwtexample.domain.Exercise;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ExerciseInfoRes(
        Long id,
        String name,
        int weight,
        int repeats,
        String userName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ExerciseInfoRes fromEntity(Exercise exercise) {
        return ExerciseInfoRes.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .weight(exercise.getWeight())
                .repeats(exercise.getRepeats())
                .userName(exercise.getUser().getName())
                .createdAt(exercise.getCreatedAt())
                .updatedAt(exercise.getUpdatedAt())
                .build();
    }
}
