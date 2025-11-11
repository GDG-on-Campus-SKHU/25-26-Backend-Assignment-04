package com.gdg.convenience.controller;

import com.gdg.convenience.dto.TokenDto;
import com.gdg.convenience.dto.UserInfoResponseDto;
import com.gdg.convenience.dto.UserSignUpDto;
import com.gdg.convenience.service.UserService;
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

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/manager")
    public ResponseEntity<TokenDto> managerSignUp(@RequestBody UserSignUpDto userSignUpDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.managerSignUp(userSignUpDto));
    }

    @PostMapping("/staff")
    public ResponseEntity<TokenDto> staffSignUp(@RequestBody UserSignUpDto userSignUpDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.staffSignUp(userSignUpDto));
    }
    @GetMapping("/info")
    public ResponseEntity<UserInfoResponseDto> getMyInfo(Principal principal) {
        return ResponseEntity.ok(userService.getMyInfo(principal));
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<UserInfoResponseDto> getUserInfo(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserInfo(id));
    }

    @PatchMapping("/update")
    public ResponseEntity<UserInfoResponseDto> updateUserInfo(Principal principal, @RequestBody UserSignUpDto userSignUpDto) {
        return ResponseEntity.ok(userService.updateUserInfo(principal, userSignUpDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<UserInfoResponseDto> deleteUserInfo(Principal principal) {
        userService.deleteUser(principal);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/refresh/{id}")
    public ResponseEntity<TokenDto> refresh(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.refresh(id));
    }
}
