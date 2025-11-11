package com.gdg.jwtexample.repository;

import com.gdg.jwtexample.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
