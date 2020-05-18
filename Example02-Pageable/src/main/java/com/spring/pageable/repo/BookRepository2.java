package com.spring.pageable.repo;

import com.spring.pageable.model.Book;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository2 extends PagingAndSortingRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
}
