package com.gdg.jwtexample.dto.user;

import com.gdg.jwtexample.domain.User;
import lombok.Builder;

@Builder
public record UserInfoRes(
        Long id,
        String email,
        String name,
        String role
) {
    public static UserInfoRes fromEntity(User user) {
        return UserInfoRes.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole().toString())
                .build();
    }
}
