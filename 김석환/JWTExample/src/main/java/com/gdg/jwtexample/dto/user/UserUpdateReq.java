package com.gdg.jwtexample.dto.user;

public record UserUpdateReq(
        String password,
        String name
) {
}
