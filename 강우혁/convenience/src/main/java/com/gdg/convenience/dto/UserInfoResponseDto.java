package com.gdg.convenience.dto;

import com.gdg.convenience.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoResponseDto {
    private long id;
    private String name;
    private String email;
    private String phone;
    private String role;

    public static UserInfoResponseDto from(User user) {
        return UserInfoResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .role(user.getRole().toString())
                .build();
    }
}
