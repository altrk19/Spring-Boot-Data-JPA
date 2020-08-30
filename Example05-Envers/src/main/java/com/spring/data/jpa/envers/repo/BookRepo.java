package com.spring.data.jpa.envers.repo;

import com.spring.data.jpa.envers.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;


public interface BookRepo extends RevisionRepository<Book, Integer, Integer>, JpaRepository<Book, Integer> {
}
