package com.gdg.jwtexample.dto.user;

import com.gdg.jwtexample.domain.User;

public record UserInfoRes(
        Long id,
        String email,
        String name
) {
    public static UserInfoRes from(User user) {
        return new UserInfoRes(user.getId(), user.getEmail(), user.getName());
    }
}
