package com.gdg.jwtexample.dto.exercise;

public record ExerciseUpdateReq(
        String name,
        int weight,
        int repeats
) { }
