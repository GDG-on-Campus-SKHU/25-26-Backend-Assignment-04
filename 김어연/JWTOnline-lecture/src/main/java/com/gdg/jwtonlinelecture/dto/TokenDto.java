package com.gdg.jwtonlinelecture.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TokenDto {
    private String accessToken;
}