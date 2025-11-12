package com.gdg.jwtonlinelecture.controller;

import com.gdg.jwtonlinelecture.dto.InstructorInfoResponseDto;
import com.gdg.jwtonlinelecture.dto.InstructorSignUpDto;
import com.gdg.jwtonlinelecture.dto.TokenDto;
import com.gdg.jwtonlinelecture.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @PostMapping("/signup")
    public ResponseEntity<TokenDto> signUp(@RequestBody InstructorSignUpDto instructorSignUpDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(instructorService.signup(instructorSignUpDto));
    }

    @GetMapping
    public ResponseEntity<InstructorInfoResponseDto> getInstructorInfo(Principal principal) {
        return ResponseEntity.ok(instructorService.findInstructorByPrincipal(principal));
    }
}