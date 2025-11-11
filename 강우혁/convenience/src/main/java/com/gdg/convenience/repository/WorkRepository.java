package com.gdg.convenience.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gdg.convenience.domain.Work;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {
    List<Work> findByUserId(Long userId);
}
