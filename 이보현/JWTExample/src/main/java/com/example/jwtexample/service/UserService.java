package com.example.jwtexample.service;

import com.example.jwtexample.domain.Role;
import com.example.jwtexample.domain.User;
import com.example.jwtexample.dto.TokenDto;
import com.example.jwtexample.dto.UserInfoResponseDto;
import com.example.jwtexample.dto.UserSignUpDto;
import com.example.jwtexample.jwt.TokenProvider;
import com.example.jwtexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public TokenDto signUp(UserSignUpDto userSignUpDto) {

        if (userRepository.existsByEmail(userSignUpDto.getEmail())) {
            throw new IllegalArgumentException("이미 등록된 이메일입니다.");
        }

        User user = userRepository.save(User.builder()
                .email(userSignUpDto.getEmail())
                .password(passwordEncoder.encode(userSignUpDto.getPassword()))
                .name(userSignUpDto.getName())
                .role(Role.ROLE_USER)
                .build());

        String accessToken = tokenProvider.createAccessToken(user);

        return TokenDto.builder()
                .accessToken(accessToken)
                .build();
    }

    public UserInfoResponseDto findUserByPrincipal(Principal principal) {
        Long userId = Long.parseLong(principal.getName());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        return UserInfoResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole().name())
                .build();
    }
}
