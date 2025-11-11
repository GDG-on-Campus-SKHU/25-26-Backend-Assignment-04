package com.gdg.jwtexample.service;

import com.gdg.jwtexample.domain.User;
import com.gdg.jwtexample.dto.jwt.TokenRes;
import com.gdg.jwtexample.dto.user.UserInfoRes;
import com.gdg.jwtexample.dto.user.UserSignUpReq;
import com.gdg.jwtexample.jwt.TokenProvider;
import com.gdg.jwtexample.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    // 회원가입
    public TokenRes signUp(UserSignUpReq request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("이미 가입된 이메일입니다.");
        }

        User user = User.of(
                request.email(),
                passwordEncoder.encode(request.password()),
                request.name()
        );

        String accessToken = tokenProvider.createAccessToken(user);
        String refreshToken = tokenProvider.createAccessToken(user);

        user.updateRefreshToken(refreshToken);
        userRepository.save(user);

        return new TokenRes(accessToken, refreshToken);
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("가입되지 않은 이메일입니다."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return tokenProvider.createAccessToken(user);
    }

    // 내 정보 조회
    public UserInfoRes getMyInfo(String email) {
        User user = getByEmail(email);
        return UserInfoRes.from(user);
    }

    // 남 정보 조회
    public UserInfoRes getUserInfo(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));
        return UserInfoRes.from(user);
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }
    public TokenRes reissue(HttpServletRequest request) {

        // 1. 쿠키에서 refreshToken 가져오기
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("refreshToken"))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new RuntimeException("Refresh Token not found"));

        // 2. refreshToken 에서 이메일 추출 (검증 포함)
        String email = tokenProvider.getEmailFromRefreshToken(refreshToken);

        // 3. DB 에 저장된 refreshToken 과 비교 (※ 지금 DB 저장 구조라서 이렇게 함)
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!refreshToken.equals(user.getRefreshToken())) {
            throw new RuntimeException("Invalid Refresh Token");
        }

        // 4. 새로운 accessToken 발급
        String newAccessToken = tokenProvider.createAccessToken(user);

        // 5. 응답으로 새로운 accessToken 반환
        return new TokenRes(newAccessToken, refreshToken);
    }

}
