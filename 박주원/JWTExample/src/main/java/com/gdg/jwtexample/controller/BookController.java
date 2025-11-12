package com.gdg.jwtexample.controller;

import com.gdg.jwtexample.dto.book.BookCreateReq;
import com.gdg.jwtexample.dto.book.BookInfoRes;
import com.gdg.jwtexample.dto.book.BookUpdateReq;
import com.gdg.jwtexample.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookInfoRes> createBook(Principal principal, @RequestBody BookCreateReq req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.createBook(principal, req));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookInfoRes> getBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.getBook(bookId));
    }

    @PatchMapping("/{bookId}")
    public ResponseEntity<BookInfoRes> updateBook(Principal principal,
                                                  @PathVariable Long bookId,
                                                  @RequestBody BookUpdateReq req) {
        return ResponseEntity.ok(bookService.updateBook(principal, bookId, req));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(Principal principal, @PathVariable Long bookId) {
        bookService.deleteBook(principal, bookId);
        return ResponseEntity.noContent().build();
    }
}
