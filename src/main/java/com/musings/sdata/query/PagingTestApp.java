package com.musings.sdata.query;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.PageRequest;

import com.musings.sdata.entities.Book;
import com.musings.sdata.repository.BookRepository;

public class PagingTestApp {

	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml")) {

			BookRepository repository = context.getBean(BookRepository.class);

			List<Book> books = repository.findAll(new PageRequest(2, 3)).getContent();
			printList(books, System.out::println, "Printing Books method: findAll()");
			
			books = repository.findByPageCountGreaterThan(100, new PageRequest(1, 2));
			printList(books, System.out::println, "Printing Books method: findByPageCountGreaterThan()");
		}
	}

	public static <T, U> void printList(List<T> ts, Consumer<T> consumer, String u) {
		System.out.println(u);
		for (T t : ts) {
			consumer.accept(t);
		}
	}

}
