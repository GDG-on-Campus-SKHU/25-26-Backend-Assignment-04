package com.gdg.convenience.service;

import com.gdg.convenience.domain.User;
import com.gdg.convenience.dto.LoginRequest;
import com.gdg.convenience.dto.TokenDto;
import com.gdg.convenience.global.UserNotFoundException;
import com.gdg.convenience.jwt.TokenProvider;
import com.gdg.convenience.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public TokenDto login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundException("사용자가 없습니다."));
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        String token = tokenProvider.createAccessToken(user);
        return TokenDto.builder()
                .accessToken(token)
                .build();
    }
}
