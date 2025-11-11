package com.gdg.jwtexample.dto.user;

import com.gdg.jwtexample.domain.Role;
import com.gdg.jwtexample.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoRes {
    private Long id;
    private String email;
    private String name;
    private Role role;

    public static UserInfoRes fromEntity(User user) {
        return UserInfoRes.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }
}
