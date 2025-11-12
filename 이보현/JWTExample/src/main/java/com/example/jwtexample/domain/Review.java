package com.example.jwtexample.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "snack_id")
    @JsonIgnore
    private Snack snack;

    private String content;

    @Column(nullable = false)
    private int score;

    @Builder
    public Review(User user, Snack snack, String content, int score) {
        this.user = user;
        this.snack = snack;
        this.content = content;
        this.score = score;
    }
}
