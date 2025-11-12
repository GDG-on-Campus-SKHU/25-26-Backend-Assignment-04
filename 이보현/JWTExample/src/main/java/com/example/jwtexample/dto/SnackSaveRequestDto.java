package com.example.jwtexample.dto;

import lombok.Getter;

@Getter
public class SnackSaveRequestDto {
    private Long id;
    private String name;
    private int releaseYear;
    private Long companyId;
    private double averageScore;
}
