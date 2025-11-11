package com.gdg.jwtexample.service;

import com.gdg.jwtexample.domain.Exercise;
import com.gdg.jwtexample.dto.exercise.ExerciseCreateReq;
import com.gdg.jwtexample.dto.exercise.ExerciseInfoRes;
import com.gdg.jwtexample.dto.exercise.ExerciseUpdateReq;
import com.gdg.jwtexample.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final UserService userService;

    @Transactional
    public ExerciseInfoRes create(Principal principal, ExerciseCreateReq request) {
        Exercise exercise = exerciseRepository.save(
                Exercise.builder()
                        .name(request.name())
                        .weight(request.weight())
                        .repeat(request.repeats())
                        .user(userService.getByEmail(principal.getName())) // ★ email로 조회
                        .build()
        );
        return ExerciseInfoRes.fromEntity(exercise);
    }

    @Transactional(readOnly = true)
    public ExerciseInfoRes get(Long id) {
        return ExerciseInfoRes.fromEntity(getExercise(id));
    }

    @Transactional
    public ExerciseInfoRes update(Principal principal, Long id, ExerciseUpdateReq request) {
        Exercise exercise = getExercise(id);
        validateOwner(principal, exercise);

        exercise.update(
                request.name() != null ? request.name() : exercise.getName(),
                request.weight() != 0 ? request.weight() : exercise.getWeight(),
                request.repeats() != 0 ? request.repeats() : exercise.getRepeats()
        );
        return ExerciseInfoRes.fromEntity(exercise);
    }

    @Transactional
    public void delete(Principal principal, Long id) {
        Exercise exercise = getExercise(id);
        validateOwner(principal, exercise);
        exerciseRepository.delete(exercise);
    }

    private Exercise getExercise(Long id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("운동 기록을 찾을 수 없습니다."));
    }

    private void validateOwner(Principal principal, Exercise exercise) {
        if (!exercise.getUser().getEmail().equals(principal.getName())) { // ★ email 비교
            throw new RuntimeException("본인의 운동 기록만 수정/삭제할 수 있습니다.");
        }
    }
}
