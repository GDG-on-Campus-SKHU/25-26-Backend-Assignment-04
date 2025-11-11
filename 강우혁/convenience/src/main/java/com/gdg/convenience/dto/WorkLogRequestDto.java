package com.gdg.convenience.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class WorkLogRequestDto {
    private Long workId;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
}
