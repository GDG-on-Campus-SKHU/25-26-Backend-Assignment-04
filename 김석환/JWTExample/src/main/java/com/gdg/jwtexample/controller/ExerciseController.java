package com.gdg.jwtexample.controller;

import com.gdg.jwtexample.dto.exercise.ExerciseCreateReq;
import com.gdg.jwtexample.dto.exercise.ExerciseInfoRes;
import com.gdg.jwtexample.dto.exercise.ExerciseUpdateReq;
import com.gdg.jwtexample.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<ExerciseInfoRes> create(Principal principal, @RequestBody ExerciseCreateReq request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(exerciseService.create(principal, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseInfoRes> get(@PathVariable Long id) {
        return ResponseEntity.ok(exerciseService.get(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ExerciseInfoRes> update(Principal principal, @PathVariable Long id, @RequestBody ExerciseUpdateReq request) {
        return ResponseEntity.ok(exerciseService.update(principal, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Principal principal, @PathVariable Long id) {
        exerciseService.delete(principal, id);
        return ResponseEntity.noContent().build();
    }

}
