package com.gdg.jwtexample.service;

import com.gdg.jwtexample.domain.Authority;
import com.gdg.jwtexample.domain.User;
import com.gdg.jwtexample.dto.BookingDto.BookingInfoResponseDto;
import com.gdg.jwtexample.dto.UserDto.TokenDto;
import com.gdg.jwtexample.dto.UserDto.UserInfoResponseDto;
import com.gdg.jwtexample.dto.UserDto.UserRequestDto;
import com.gdg.jwtexample.exception.CustomErrorCode;
import com.gdg.jwtexample.exception.CustomException;
import com.gdg.jwtexample.jwt.TokenProvider;
import com.gdg.jwtexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public TokenDto signUp(UserRequestDto userRequestDto) {
        User user = userRepository.save(User.builder()
                .name(userRequestDto.getName())
                .phone(userRequestDto.getPhone())
                .role(Authority.USER)
                .build());

        String accessToken = tokenProvider.createAccessToken(user);

        return TokenDto.builder()
                .access_token(accessToken)
                .build();
    }

    @Transactional(readOnly = true)
    public UserInfoResponseDto findUserByPrincipal(Principal principal) {
        Long userId = Long.parseLong(principal.getName());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND_EXCEPTION));

        return UserInfoResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
    }

    @Transactional
    public UserInfoResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND_EXCEPTION));

        Authority role = user.getRole();

        user.update(userRequestDto, role);

        return UserInfoResponseDto.from(user);
    }

    @Transactional
    public void updateRole(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND_EXCEPTION));

        if (user.getBookings().size() >= 3) {
            user.updateRole(Authority.VIP);
        }
    }

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND_EXCEPTION));

        userRepository.delete(user);
    }
}
