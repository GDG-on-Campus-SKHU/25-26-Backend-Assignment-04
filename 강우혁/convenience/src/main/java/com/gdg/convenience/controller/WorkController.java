package com.gdg.convenience.controller;

import com.gdg.convenience.dto.CreateWorkRequestDto;
import com.gdg.convenience.dto.WorkDto;
import com.gdg.convenience.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/work")
public class WorkController {

    private final WorkService workService;

    @PostMapping("/create")
    public WorkDto createWork(@RequestBody CreateWorkRequestDto createWorkRequestDto) {
        return workService.createWork(createWorkRequestDto);
    }

    @GetMapping("/{id}")
    public WorkDto getWork(@PathVariable Long id) {
        return workService.getWork(id);
    }

    @GetMapping("/user/{userId}")
    public List<WorkDto> getUserWorks(@PathVariable Long userId) {
        return workService.getUserWorks(userId);
    }

    @PatchMapping("/update/{id}")
    public WorkDto updateWork(@PathVariable Long id,
                              @RequestBody CreateWorkRequestDto createWorkRequestDto) {
        return workService.updateWork(id, createWorkRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteWork(@PathVariable Long id) {
        workService.deleteWork(id);
    }
}
