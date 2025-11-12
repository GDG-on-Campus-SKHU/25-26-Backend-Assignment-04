package com.gdg.jwtexample.dto.UserDto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TokenDto {
    private String access_token;
}
