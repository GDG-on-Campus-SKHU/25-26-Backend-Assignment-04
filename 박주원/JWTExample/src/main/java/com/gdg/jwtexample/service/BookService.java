package com.gdg.jwtexample.service;

import com.gdg.jwtexample.domain.Book;
import com.gdg.jwtexample.dto.book.BookCreateReq;
import com.gdg.jwtexample.dto.book.BookInfoRes;
import com.gdg.jwtexample.dto.book.BookUpdateReq;
import com.gdg.jwtexample.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final UserService userService;

    @Transactional
    public BookInfoRes createBook(Principal principal, BookCreateReq req) {
        Book book = bookRepository.save(Book.builder()
                .title(req.title())
                .author(req.author())
                .description(req.description())
                .user(userService.getUserEntity(Long.parseLong(principal.getName())))
                .build());

        return BookInfoRes.fromEntity(book);
    }

    @Transactional(readOnly = true)
    public BookInfoRes getBook(Long bookId) {
        return BookInfoRes.fromEntity(findBook(bookId));
    }

    @Transactional
    public BookInfoRes updateBook(Principal principal, Long bookId, BookUpdateReq req) {
        Book book = findBook(bookId);
        validateOwner(principal, book);

        book.update(
                req.title() == null ? book.getTitle() : req.title(),
                req.author() == null ? book.getAuthor() : req.author(),
                req.description() == null ? book.getDescription() : req.description()
        );

        return BookInfoRes.fromEntity(book);
    }

    @Transactional
    public void deleteBook(Principal principal, Long bookId) {
        Book book = findBook(bookId);
        validateOwner(principal, book);
        bookRepository.delete(book);
    }

    private Book findBook(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 책입니다."));
    }

    private void validateOwner(Principal principal, Book book) {
        if (!book.getUser().getId().equals(Long.parseLong(principal.getName()))) {
            throw new RuntimeException("해당 책에 접근할 권한이 없습니다.");
        }
    }
}
