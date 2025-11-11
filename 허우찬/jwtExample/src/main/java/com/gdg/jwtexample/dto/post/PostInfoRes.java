package com.gdg.jwtexample.dto.post;

import com.gdg.jwtexample.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class PostInfoRes {
    private Long id;
    private String title;
    private String content;
    private String createdAt;
    private String updatedAt;
    private Long userId;
    private String userName;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static PostInfoRes fromEntity(Post post) {
        return PostInfoRes.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt().format(FORMATTER))
                .updatedAt(post.getUpdatedAt().format(FORMATTER))
                .userId(post.getUser().getId())
                .userName(post.getUser().getName())
                .build();
    }
}
