package com.musings.sdata.app;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.musings.sdata.entities.Book;
import com.musings.sdata.repository.BookRepository;

public class LimitingQueryTestApplication {

	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml")) {

			BookRepository repository = context.getBean(BookRepository.class);

			List<Book> books = repository.findFirstByOrderByPriceDesc();
			printList(books, System.out::println, "Printing Books method: findFirstByOrderByPriceDesc()");

			books = repository.findFirst5ByTitleContainingOrderByPageCountDesc("The");
			printList(books, System.out::println, "Printing Books method: findFirst5ByTitleContainingOrderByPageCountDesc()");

		}
	}

	public static <T, U> void printList(List<T> ts, Consumer<T> consumer, String u) {
		System.out.println(u);
		for (T t : ts) {
			consumer.accept(t);
		}
	}

}
