package com.gdg.convenience.service;

import com.gdg.convenience.domain.User;
import com.gdg.convenience.domain.Work;
import com.gdg.convenience.dto.CreateWorkRequestDto;
import com.gdg.convenience.dto.WorkDto;
import com.gdg.convenience.global.EntityFinder;
import com.gdg.convenience.global.WorkNotFoundException;
import com.gdg.convenience.repository.UserRepository;
import com.gdg.convenience.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkService {

    private final WorkRepository workRepository;
    private final UserRepository userRepository;
    private final EntityFinder entityFinder;

    @Transactional
    public WorkDto createWork(CreateWorkRequestDto createWorkRequestDto) {
        User user = entityFinder.checkUser(createWorkRequestDto.getId());

        Work work = Work.builder()
                .user(user)
                .scheduledStart(createWorkRequestDto.getScheduledStart())
                .scheduledEnd(createWorkRequestDto.getScheduledEnd())
                .build();

        workRepository.save(work);
        return WorkDto.from(work);
    }

    @Transactional(readOnly = true)
    public List<WorkDto> getUserWorks(Long userId) {
        return workRepository.findByUserId(userId)
                .stream()
                .map(WorkDto::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public WorkDto getWork(Long id) {
        Work work = entityFinder.checkWork(id);
        return WorkDto.from(work);
    }

    @Transactional
    public WorkDto updateWork(Long id, CreateWorkRequestDto createWorkRequestDto) {
        Work work = entityFinder.checkWork(id);

        work.update(createWorkRequestDto.getScheduledStart(), createWorkRequestDto.getScheduledEnd());
        return WorkDto.from(work);
    }

    @Transactional
    public void deleteWork(Long id) {
        workRepository.deleteById(id);
    }

}
