package com.gdg.jwtexample.controller;

import com.gdg.jwtexample.dto.jwt.TokenRes;
import com.gdg.jwtexample.dto.user.UserInfoRes;
import com.gdg.jwtexample.dto.user.UserSignUpReq;
import com.gdg.jwtexample.dto.user.UserUpdateReq;
import com.gdg.jwtexample.service.UserService;
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
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<TokenRes> signUp(@RequestBody UserSignUpReq userSignUpReq) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUp(userSignUpReq));
    }

    @GetMapping("/my")
    public ResponseEntity<UserInfoRes> getMyInfo(Principal principal) {
        return ResponseEntity.ok(userService.getMyInfo(principal));
    }

    @GetMapping("/info/{userId}")
    public ResponseEntity<UserInfoRes> getUserInfo(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserInfo(userId));
    }

    @PatchMapping("/update")
    public ResponseEntity<UserInfoRes> updateMyInfo(Principal principal, @RequestBody UserUpdateReq userUpdateReq) {
        return ResponseEntity.ok(userService.updateMyInfo(principal, userUpdateReq));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(Principal principal) {
        userService.deleteUser(principal);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
