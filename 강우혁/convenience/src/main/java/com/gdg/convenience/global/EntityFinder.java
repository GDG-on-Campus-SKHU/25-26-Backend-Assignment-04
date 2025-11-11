package com.gdg.convenience.global;

import com.gdg.convenience.domain.User;
import com.gdg.convenience.domain.Work;
import com.gdg.convenience.repository.UserRepository;
import com.gdg.convenience.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityFinder {
    private final UserRepository userRepository;
    private final WorkRepository workRepository;
    public User checkUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("사용자가 없습니다."));
    }
    public Work checkWork(Long workId) {
        return workRepository.findById(workId)
                .orElseThrow(() -> new WorkNotFoundException("작업 내용이 없습니다."));
    }
}
