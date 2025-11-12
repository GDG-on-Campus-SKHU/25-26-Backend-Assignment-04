package com.gdg.jwtexample.dto.UserDto;

import com.gdg.jwtexample.domain.Authority;
import com.gdg.jwtexample.domain.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserInfoResponseDto {
    private Long id;
    private String name;
    private String phone;
    private Authority role;

    public static UserInfoResponseDto from(User user) {
        return UserInfoResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
    }
}