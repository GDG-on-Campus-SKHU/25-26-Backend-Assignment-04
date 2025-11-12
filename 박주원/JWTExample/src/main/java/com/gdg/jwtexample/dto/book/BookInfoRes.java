package com.gdg.jwtexample.dto.book;

import lombok.Builder;
import com.gdg.jwtexample.domain.Book;

import java.time.LocalDateTime;

@Builder
public record BookInfoRes(
        Long id,
        String title,
        String author,
        String description,
        String createdBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static BookInfoRes fromEntity(Book book) {
        return BookInfoRes.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .createdBy(book.getUser().getName())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();
    }
}
