package com.spring.pageable.controller;

import com.spring.pageable.model.Book;
import com.spring.pageable.repo.BookRepository;
import com.spring.pageable.repo.BookRepository2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepository bookRepository;
    private final BookRepository2 bookRepository2;

    public BookController(BookRepository bookRepository, BookRepository2 bookRepository2) {
        this.bookRepository = bookRepository;
        this.bookRepository2 = bookRepository2;
    }

    @GetMapping("/v1")
    public Page<Book> pagination(@RequestParam int size,
                                 @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable);
        //it also returns the count of all records.
    }

    @GetMapping("/v2")
    public Page<Book> pagination2(Pageable pageable) {
        return bookRepository.findAll(pageable);
        //it also returns the count of all records.
    }

    @GetMapping("/v3")
    public Slice<Book> paginationSlice(Pageable pageable) {
        return bookRepository.findAll(pageable);
        //It is not concerned with the countu of all records.it has more performance.Example: twitter
    }

}
