package com.musings.sdata.app;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.musings.sdata.entities.Book;
import com.musings.sdata.repository.BookRepository;

public class Application {

	public static void main(String[] args) {

		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml")) {
			BookRepository repository = context.getBean(BookRepository.class);

			List<Book> books = repository.findByTitle("Design Patterns");
			printList(books, System.out::println, "Printing Books method: findByTitle()");
			
			books = repository.findByTitleContaining("of");
			printList(books, System.out::println, "Printing Books method: findByTitleContaining()");
			
			books = repository.findByTitleLike("The Grapes of Wrath");
			printList(books, System.out::println, "Printing Books method: findByTitleLike()");
			
			books = repository.findByTitleStartsWith("One");
			printList(books, System.out::println, "Printing Books method: findByTitleStartsWith()");
			
			books = repository.findByTitleEndsWith("Cities");
			printList(books, System.out::println, "Printing Books method: findBytitleEndsWith()");
			
			books = repository.findByTitleIgnoreCase("of mice and men");
			printList(books, System.out::println, "Printing Books method: findByTitleIgnoreCase()");
		}

	}

	public static <T, U> void printList(List<T> ts, Consumer<T> consumer, String u) {
		System.out.println(u);
		for (T t : ts) {
			consumer.accept(t);
		}
	}

}
