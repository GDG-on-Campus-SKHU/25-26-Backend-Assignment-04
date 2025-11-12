package com.gdg.jwtexample.repository;

import com.gdg.jwtexample.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
