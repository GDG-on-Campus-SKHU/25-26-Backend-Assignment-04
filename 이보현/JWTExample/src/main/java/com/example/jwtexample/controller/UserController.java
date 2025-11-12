package com.example.jwtexample.controller;

import com.example.jwtexample.domain.Role;
import com.example.jwtexample.domain.User;
import com.example.jwtexample.dto.TokenDto;
import com.example.jwtexample.dto.UserInfoResponseDto;
import com.example.jwtexample.dto.UserSignUpDto;
import com.example.jwtexample.jwt.TokenProvider;
import com.example.jwtexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<TokenDto> signUp(@RequestBody UserSignUpDto userSignUpDto) {
        User user = userRepository.save(User.builder()
                .email(userSignUpDto.getEmail())
                .password(passwordEncoder.encode(userSignUpDto.getPassword()))
                .name(userSignUpDto.getName())
                .role(Role.ROLE_USER)
                .build());

        String accessToken = tokenProvider.createAccessToken(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TokenDto.builder()
                        .accessToken(accessToken)
                        .build());
    }

    @GetMapping("/me")
    public ResponseEntity<UserInfoResponseDto> findUserByPrincipal(Principal principal) {
        Long userId = Long.parseLong(principal.getName());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        return ResponseEntity.ok(UserInfoResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole().name())
                .build());
    }
}
