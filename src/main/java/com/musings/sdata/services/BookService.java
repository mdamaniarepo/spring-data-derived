package com.musings.sdata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musings.sdata.entities.Book;
import com.musings.sdata.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;

	public void save(Book book) {
		repository.save(book);
	}

}
