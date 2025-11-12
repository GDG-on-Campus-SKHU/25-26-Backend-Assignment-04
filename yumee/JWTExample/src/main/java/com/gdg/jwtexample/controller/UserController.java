package com.gdg.jwtexample.controller;

import com.gdg.jwtexample.dto.UserDto.TokenDto;
import com.gdg.jwtexample.dto.UserDto.UserInfoResponseDto;
import com.gdg.jwtexample.dto.UserDto.UserRequestDto;
import com.gdg.jwtexample.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @PostMapping("signup")
    public ResponseEntity<TokenDto> signUp(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUp(userRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoResponseDto> findUserByPrincipal(Principal principal) {
        return ResponseEntity.ok(userService.findUserByPrincipal(principal));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserInfoResponseDto> update(@PathVariable Long id,
                                                      @Valid @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, userRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserInfoResponseDto> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
