package com.spring.data.jpa.envers.controller;

import com.spring.data.jpa.envers.model.Book;
import com.spring.data.jpa.envers.repo.BookRepo;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    private final BookRepo bookRepo;

    public BookController(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @PostMapping("/saveBook")
    public Book saveBook(@RequestBody Book book) {
        return bookRepo.save(book);
    }

    @PutMapping("/update/{id}/{pages}")
    public String updateBook(@PathVariable int id, @PathVariable int pages) {
        Book book = bookRepo.findById(id).get();
        book.setPages(pages);
        bookRepo.save(book);
        return "book updated";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        bookRepo.deleteById(id);
        return "book deleted";
    }
    @GetMapping("/getInfo/{id}")
    public void getInfo(@PathVariable  int id){
        System.out.println(bookRepo.findLastChangeRevision(id));
    }

}
