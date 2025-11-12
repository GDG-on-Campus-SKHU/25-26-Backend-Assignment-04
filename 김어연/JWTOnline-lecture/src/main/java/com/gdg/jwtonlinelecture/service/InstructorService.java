package com.gdg.jwtonlinelecture.service;

import com.gdg.jwtonlinelecture.domain.Instructor;
import com.gdg.jwtonlinelecture.domain.Role;
import com.gdg.jwtonlinelecture.dto.InstructorInfoResponseDto;
import com.gdg.jwtonlinelecture.dto.InstructorSignUpDto;
import com.gdg.jwtonlinelecture.dto.TokenDto;
import com.gdg.jwtonlinelecture.jwt.TokenProvider;
import com.gdg.jwtonlinelecture.repository.InstructorRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public TokenDto signup(InstructorSignUpDto dto) {
        Instructor instructor = instructorRepository.save(Instructor.builder()
                .name(dto.getName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .title(dto.getTitle())
                .role(Role.ROLE_INSTRUCTOR)
                .build());

        String accessToken = tokenProvider.createAccessToken(instructor);
        return TokenDto.builder().accessToken(accessToken).build();
    }

    public InstructorInfoResponseDto findInstructorByPrincipal(Principal principal) {
        Long id = Long.parseLong(principal.getName());
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        return InstructorInfoResponseDto.builder()
                .id(instructor.getId())
                .name(instructor.getName())
                .title(instructor.getTitle())
                .role(instructor.getRole())
                .build();
    }
}