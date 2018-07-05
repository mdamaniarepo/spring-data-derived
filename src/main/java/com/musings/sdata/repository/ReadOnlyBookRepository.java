package com.musings.sdata.repository;

import org.springframework.stereotype.Repository;

import com.musings.sdata.entities.Book;

@Repository
public interface ReadOnlyBookRepository extends ReadOnlyRepository<Book, Long> {

}
