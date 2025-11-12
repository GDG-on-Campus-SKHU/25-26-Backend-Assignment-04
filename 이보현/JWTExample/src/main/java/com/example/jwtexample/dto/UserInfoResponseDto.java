package com.example.jwtexample.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserInfoResponseDto {
    private Long id;
    private String email;
    private String name;
    private String role;
}