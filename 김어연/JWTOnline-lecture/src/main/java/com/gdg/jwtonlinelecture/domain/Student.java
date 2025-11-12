package com.gdg.jwtonlinelecture.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    @JsonIgnore
    private Instructor instructor;

    @Builder
    public Student(Long id, String name, String email, Instructor instructor) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.instructor = instructor;
    }

    public Student update(String name, String email, Instructor instructor) {
        this.name = name;
        this.email = email;
        this.instructor = instructor;
        return this;
    }
}