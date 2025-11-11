package com.gdg.convenience.repository;

import com.gdg.convenience.domain.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkLogRepository extends JpaRepository<WorkLog, Long> {
        Optional<WorkLog> findTopByUserIdOrderByIdDesc(Long userId);
    }