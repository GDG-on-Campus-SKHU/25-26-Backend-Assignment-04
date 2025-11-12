package com.example.jwtexample.dto;

import com.example.jwtexample.domain.Snack;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SnackInfoResponseDto {
    private Long id;
    private String name;
    private int releaseYear;
    private Long companyId;
    private String companyName;
    private double averageScore;

    public static SnackInfoResponseDto from(Snack snack) {
        return SnackInfoResponseDto.builder()
                .id(snack.getId())
                .name(snack.getName())
                .releaseYear(snack.getReleaseYear())
                .companyId(snack.getCompany().getId())
                .companyName(snack.getCompany().getName())
                .averageScore(0.0) //
                .build();
    }
    public static SnackInfoResponseDto from(Snack snack, double averageScore) {
        return SnackInfoResponseDto.builder()
                .id(snack.getId())
                .name(snack.getName())
                .releaseYear(snack.getReleaseYear())
                .averageScore(averageScore)
                .build();
    }
}

