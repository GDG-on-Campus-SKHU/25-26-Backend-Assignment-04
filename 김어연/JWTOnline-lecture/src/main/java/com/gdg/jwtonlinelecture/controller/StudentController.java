package com.gdg.jwtonlinelecture.controller;

import com.gdg.jwtonlinelecture.dto.StudentRequestDto;
import com.gdg.jwtonlinelecture.dto.StudentResponseDto;
import com.gdg.jwtonlinelecture.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/{instructorId}")
    public ResponseEntity<StudentResponseDto> addStudent(
            @PathVariable Long instructorId,
            @RequestBody StudentRequestDto studentRequestDto) {
        StudentResponseDto responseDto = studentService.addStudent(instructorId, studentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{instructorId}")
    public ResponseEntity<List<StudentResponseDto>> getStudentsByInstructor(@PathVariable Long instructorId) {
        List<StudentResponseDto> students = studentService.getStudentsByInstructor(instructorId);
        return ResponseEntity.ok(students);
    }

    @PatchMapping("/{studentId}")
    public ResponseEntity<StudentResponseDto> updateStudent(
            @PathVariable Long studentId,
            @RequestBody StudentRequestDto studentRequestDto) {
        StudentResponseDto updatedStudent = studentService.updateStudent(studentId, studentRequestDto);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }
}