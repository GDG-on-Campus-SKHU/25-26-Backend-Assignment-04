package com.example.jwtexample.controller;

import com.example.jwtexample.dto.SnackInfoResponseDto;
import com.example.jwtexample.dto.SnackSaveRequestDto;
import com.example.jwtexample.service.SnackService;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/snacks")
public class SnackController {

    private final SnackService snackService;

    @PostMapping
    public ResponseEntity<SnackInfoResponseDto> saveSnack(@RequestBody SnackSaveRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(snackService.saveSnack(dto));
    }

    @GetMapping("/{snackId}")
    public ResponseEntity<SnackInfoResponseDto> getSnack(@PathVariable Long snackId) {
        return ResponseEntity.ok(snackService.getSnack(snackId));
    }

    @PatchMapping("/{snackId}")
    public ResponseEntity<SnackInfoResponseDto> updateSnack(@PathVariable Long snackId,
                                                            @RequestBody SnackSaveRequestDto dto) {
        return ResponseEntity.ok(snackService.updateSnack(snackId, dto));
    }

    @DeleteMapping("/{snackId}")
    public ResponseEntity<Void> deleteSnack(@PathVariable Long snackId) {
        snackService.deleteSnack(snackId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SnackInfoResponseDto>> getAllSnacks() {
        return ResponseEntity.ok(snackService.getAllSnacks());
    }
}
