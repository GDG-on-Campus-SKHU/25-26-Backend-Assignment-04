package com.gdg.jwtexample.dto.user;

public record UserSignUpReq(
        String email,
        String password,
        String name
) {
}
