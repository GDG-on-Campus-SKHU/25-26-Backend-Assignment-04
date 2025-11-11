package com.gdg.convenience.controller;

import com.gdg.convenience.service.WorkLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workLog")
public class WorkLogController {

    private final WorkLogService workLogService;

    @PostMapping("/check-in")
    public String checkIn(Principal principal) {
        Long userId = Long.parseLong(principal.getName());
        return workLogService.checkIn(userId);
    }

    @PostMapping("/check-out")
    public String checkOut(Principal principal) {
        Long userId = Long.parseLong(principal.getName());
        return workLogService.checkOut(userId);
    }
}
