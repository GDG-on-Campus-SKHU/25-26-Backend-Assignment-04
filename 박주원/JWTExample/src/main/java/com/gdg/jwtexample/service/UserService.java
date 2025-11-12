package com.gdg.jwtexample.service;

import com.gdg.jwtexample.domain.Role;
import com.gdg.jwtexample.domain.User;
import com.gdg.jwtexample.dto.jwt.TokenRes;
import com.gdg.jwtexample.dto.user.UserInfoRes;
import com.gdg.jwtexample.dto.user.UserSignUpReq;
import com.gdg.jwtexample.dto.user.UserUpdateReq;
import com.gdg.jwtexample.jwt.TokenProvider;
import com.gdg.jwtexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public TokenRes signUp(UserSignUpReq userSignupReq) {
        User user = userRepository.save(User.builder()
                .email(userSignupReq.email())
                .password(passwordEncoder.encode(userSignupReq.password()))
                .name(userSignupReq.name())
                .role(Role.ROLE_USER)
                .build());

        return TokenRes.builder()
                .accessToken(tokenProvider.createAccessToken(user))
                .build();
    }

    @Transactional(readOnly = true)
    public UserInfoRes getMyInfo(Principal principal) {
        User user = getUserEntity(Long.parseLong(principal.getName()));

        return UserInfoRes.fromEntity(user);
    }

    @Transactional(readOnly = true)
    public UserInfoRes getUserInfo(Long userId) {
        User user = getUserEntity(userId);

        return UserInfoRes.fromEntity(user);
    }

    @Transactional
    public UserInfoRes updateMyInfo(Principal principal, UserUpdateReq userUpdateReq) {
        User user = getUserEntity(Long.parseLong(principal.getName()));
        user.updateInfo(
                userUpdateReq.password() == null ? user.getPassword() : passwordEncoder.encode(userUpdateReq.password()),
                userUpdateReq.name() == null ? user.getName() : userUpdateReq.name()
        );

        return UserInfoRes.fromEntity(user);
    }

    @Transactional
    public void deleteUser(Principal principal) {
        userRepository.deleteById(Long.parseLong(principal.getName()));
    }

    public User getUserEntity(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저가 존재하지 않습니다."));
    }
}
