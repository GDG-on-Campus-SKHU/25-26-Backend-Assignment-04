package com.gdg.jwtexample.dto.book;

public record BookCreateReq(
        String title,
        String author,
        String description
) {}
