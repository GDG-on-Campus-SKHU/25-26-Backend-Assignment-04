package com.gdg.jwtexample.dto.jwt;

import lombok.Builder;

@Builder
public record TokenRes(
        String accessToken
) {
}
