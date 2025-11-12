package com.gdg.jwtonlinelecture.repository;

import com.gdg.jwtonlinelecture.domain.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}