package com.gdg.convenience.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Work {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID")
    private User user;

    private LocalDateTime scheduledStart;
    private LocalDateTime scheduledEnd;

    @Builder
    public Work(User user, LocalDateTime scheduledStart, LocalDateTime scheduledEnd) {
        this.user = user;
        this.scheduledStart = scheduledStart;
        this.scheduledEnd = scheduledEnd;
    }

    public void update(LocalDateTime scheduledStart, LocalDateTime scheduledEnd) {
        this.scheduledStart = scheduledStart;
        this.scheduledEnd = scheduledEnd;
    }
}