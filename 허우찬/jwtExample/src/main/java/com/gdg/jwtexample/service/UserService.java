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
        if (userRepository.findByEmail(userSignupReq.email()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

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
        User user = getLoggedInUserEntity(principal);
        return UserInfoRes.fromEntity(user);
    }

    @Transactional(readOnly = true)
    public UserInfoRes getUserInfo(Long userId) {
        User user = getUserEntity(userId);
        return UserInfoRes.fromEntity(user);
    }

    @Transactional
    public UserInfoRes updateMyInfo(Principal principal, UserUpdateReq userUpdateReq) {
        User user = getLoggedInUserEntity(principal);
        user.updateInfo(
                userUpdateReq.password() == null ? user.getPassword() : passwordEncoder.encode(userUpdateReq.password()),
                userUpdateReq.name() == null ? user.getName() : userUpdateReq.name()
        );
        return UserInfoRes.fromEntity(user);
    }

    @Transactional
    public void deleteUser(Principal principal) {
        User userToDelete = getLoggedInUserEntity(principal);
        userRepository.delete(userToDelete);
    }

    @Transactional(readOnly = true)
    public User getUserEntity(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저가 존재하지 않습니다."));
    }

    @Transactional(readOnly = true)
    public User getLoggedInUserEntity(Principal principal) {
        if (principal == null || principal.getName() == null) {
            throw new RuntimeException("Principal이 존재하지 않습니다. (인증 필요)");
        }
        Long userId = Long.parseLong(principal.getName());
        return getUserEntity(userId);
    }
}
