package com.gdg.jwtexample.dto.book;

public record BookUpdateReq(
        String title,
        String author,
        String description
) {}
