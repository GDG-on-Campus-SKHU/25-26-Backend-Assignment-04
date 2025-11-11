package com.gdg.convenience.service;

import com.gdg.convenience.domain.User;
import com.gdg.convenience.domain.WorkLog;
import com.gdg.convenience.global.EntityFinder;
import com.gdg.convenience.global.UserNotFoundException;
import com.gdg.convenience.global.WorkNotFoundException;
import com.gdg.convenience.repository.UserRepository;
import com.gdg.convenience.repository.WorkLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WorkLogService {

    private final WorkLogRepository workLogRepository;
    private final EntityFinder entityFinder;

    public String checkIn(Long userId) {
        User user = entityFinder.checkUser(userId);

        boolean alreadyWorking = workLogRepository
                .findTopByUserIdOrderByIdDesc(userId)
                .isPresent();
        if (alreadyWorking) {
            throw new IllegalStateException("이미 출근 완료 상태입니다.");
        }

        WorkLog workLog = WorkLog.builder()
                .user(user)
                .checkInTime(LocalDateTime.now())
                .build();

        workLogRepository.save(workLog);
        return "출근처리가 완료되었습니다.";
    }

    public String checkOut(Long userId) {
        User user = entityFinder.checkUser(userId);

        WorkLog lastWorkLog = workLogRepository.findTopByUserIdOrderByIdDesc(user.getId())
                .orElseThrow(() -> new WorkNotFoundException("출근 기록이 없습니다."));

        if (lastWorkLog.getCheckOutTime() != null) {
            return "이미 퇴근 처리된 상태입니다.";
        }

        lastWorkLog.setCheckOutTime(LocalDateTime.now());
        workLogRepository.save(lastWorkLog);
        return "퇴근 처리가 완료되었습니다.";
    }
}
