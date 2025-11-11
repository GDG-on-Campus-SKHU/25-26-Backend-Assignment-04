package com.gdg.convenience.dto;

import com.gdg.convenience.domain.Work;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class WorkDto {

    private Long id;
    private Long userId;
    private LocalDateTime scheduledStart;
    private LocalDateTime scheduledEnd;

    @Builder
    public WorkDto(Long id, Long userId, LocalDateTime scheduledStart, LocalDateTime scheduledEnd) {
        this.id = id;
        this.userId = userId;
        this.scheduledStart = scheduledStart;
        this.scheduledEnd = scheduledEnd;
    }

    public static WorkDto from(Work work) {
        return WorkDto.builder()
                .id(work.getId())
                .userId(work.getUser().getId())
                .scheduledStart(work.getScheduledStart())
                .scheduledEnd(work.getScheduledEnd())
                .build();
    }
}
