package com.gdg.jwtexample.dto.jwt;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenRes {
    private String accessToken;
}
