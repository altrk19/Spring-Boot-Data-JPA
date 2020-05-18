package com.spring.searchspecification.controller;

import com.spring.searchspecification.model.Book;
import com.spring.searchspecification.search.SearchSpecification;
import com.spring.searchspecification.search.SearchType;
import com.spring.searchspecification.repo.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> pagination(@RequestParam(required = false) Integer id,
                                 @RequestParam(required = false) String isbn) {
        SearchSpecification.Builder<Book> specBuilder = new SearchSpecification.Builder<>();

        if (Objects.nonNull(id)) {
            specBuilder.and("id", SearchType.EQUALS, id, SearchType.OPERATOR_AND);
        }

        if (Objects.nonNull(isbn)) {
            specBuilder.and("isbn", SearchType.EQUALS, isbn, SearchType.OPERATOR_AND);
        }

        return bookRepository.findAll(specBuilder.build());
    }


}
