package com.musings.sdata.app;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.musings.sdata.entities.Book;
import com.musings.sdata.repository.BookRepository;

public class NestedQueryTestApplication {

	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml")) {

			BookRepository repository = context.getBean(BookRepository.class);

			List<Book> books = repository.findByAuthorLastNameContaining("Hemm");
			printList(books, System.out::println,
					"Printing Books method: findByAuthorLastNameContaining()");

			books = repository.findByAuthor_Country("England");
			printList(books, System.out::println, "Printing Books method: findByAuthor_Country()");
		}
	}

	public static <T, U> void printList(List<T> ts, Consumer<T> consumer, String u) {
		System.out.println(u);
		for (T t : ts) {
			consumer.accept(t);
		}
	}

}
