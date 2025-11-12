package com.gdg.jwtonlinelecture.repository;

import com.gdg.jwtonlinelecture.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByInstructorId(Long instructorId);
}