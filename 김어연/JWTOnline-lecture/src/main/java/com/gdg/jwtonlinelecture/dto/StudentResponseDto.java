package com.gdg.jwtonlinelecture.dto;

import com.gdg.jwtonlinelecture.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
public class StudentResponseDto {
    private Long id;
    private String name;
    private String email;

    public StudentResponseDto(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.email = student.getEmail();
    }
}