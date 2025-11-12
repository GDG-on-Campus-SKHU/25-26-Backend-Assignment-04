package com.gdg.jwtonlinelecture.dto;

import com.gdg.jwtonlinelecture.domain.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InstructorInfoResponseDto {
    private Long id;
    private String name;
    private String title;
    private Role role;
}