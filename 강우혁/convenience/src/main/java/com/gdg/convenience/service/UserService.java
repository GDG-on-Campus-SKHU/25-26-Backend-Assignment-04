package com.gdg.convenience.service;

import com.gdg.convenience.domain.Role;
import com.gdg.convenience.domain.User;
import com.gdg.convenience.dto.TokenDto;
import com.gdg.convenience.dto.UserInfoResponseDto;
import com.gdg.convenience.dto.UserSignUpDto;
import com.gdg.convenience.global.EntityFinder;
import com.gdg.convenience.global.UserNotFoundException;
import com.gdg.convenience.jwt.TokenProvider;
import com.gdg.convenience.repository.UserRepository;
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
    private final EntityFinder entityFinder;

    @Transactional
    public TokenDto managerSignUp(UserSignUpDto userSignUpDto) {
        User user = userRepository.save(User.builder()
                .name(userSignUpDto.getName())
                .email(userSignUpDto.getEmail())
                .password(passwordEncoder.encode(userSignUpDto.getPassword()))
                .phone(userSignUpDto.getPhone())
                .role(Role.ROLE_MANAGER)
                .build()
        );

        String accessToken = tokenProvider.createAccessToken(user);

        user.saveAccessToken(accessToken);

        return TokenDto.builder()
                .accessToken(accessToken)
                .build();
    }

    @Transactional
    public TokenDto staffSignUp(UserSignUpDto userSignUpDto) {
        User user = userRepository.save(User.builder()
                .name(userSignUpDto.getName())
                .email(userSignUpDto.getEmail())
                .password(passwordEncoder.encode(userSignUpDto.getPassword()))
                .phone(userSignUpDto.getPhone())
                .role(Role.ROLE_STAFF)
                .build()
        );

        String accessToken = tokenProvider.createAccessToken(user);

        user.saveAccessToken(accessToken);

        return  TokenDto.builder()
                .accessToken(accessToken)
                .build();
    }

    @Transactional
    public TokenDto refresh(Long id) {
        User user = entityFinder.checkUser(id);

        String refreshToken = tokenProvider.createRefreshToken(user);

        user.saveRefreshToken(refreshToken);
        userRepository.save(user);

        return TokenDto.builder()
                .accessToken(user.getAccessToken())
                .refreshToken(refreshToken)
                .build();
    }


    @Transactional(readOnly = true)
    public UserInfoResponseDto getMyInfo (Principal principal){
       User user = entityFinder.checkUser(Long.parseLong(principal.getName()));

       return UserInfoResponseDto.from(user);
    }

    @Transactional(readOnly = true)
    public UserInfoResponseDto getUserInfo(Long id){
        User user = entityFinder.checkUser(id);

        return UserInfoResponseDto.from(user);
    }

    @Transactional
    public UserInfoResponseDto updateUserInfo(Principal principal, UserSignUpDto userSignUpDto){
        User user = entityFinder.checkUser(Long.parseLong(principal.getName()));
        user.updateInfo(
                userSignUpDto.getName() == null ? user.getName() : userSignUpDto.getName(),
                userSignUpDto.getEmail() == null ? user.getEmail() : userSignUpDto.getEmail(),
                userSignUpDto.getPassword() == null ? user.getPassword() : passwordEncoder.encode(userSignUpDto.getPassword())
        );

        return UserInfoResponseDto.from(user);
    }

    @Transactional
    public void deleteUser(Principal principal){
        userRepository.deleteById(Long.parseLong(principal.getName()));
    }
}
