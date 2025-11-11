package com.gdg.jwtexample.dto.exercise;

public record ExerciseCreateReq(
        String name,
        int weight,
        int repeats
) { }
