package com.musings.sdata.app;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.musings.sdata.entities.Book;
import com.musings.sdata.repository.BookRepository;

public class RelationalQueryTestApplication {

	public static void main(String[] args) {
		
		try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml")) {
			
			BookRepository repository = context.getBean(BookRepository.class);
			
			List<Book> books = repository.findByPageCountEquals(400);
			printList(books, System.out::println, "Printing Books method: findByPageCountEquals()");
			
			books = repository.findByPageCountGreaterThan(150, null);
			printList(books, System.out::println, "Printing Books method: findByPageCountGreaterThan()");
			
			books = repository.findByPageCountGreaterThanEqual(300);
			printList(books, System.out::println, "Printing Books method: findByPageCountGreaterThanEquals()");
			
			books = repository.findByPageCountLessThan(200, null);
			printList(books, System.out::println, "Printing Books method: findByPageCountLessThan()");
			
			books = repository.findByPageCountLessThanEqual(300);
			printList(books, System.out::println, "Printing Books method: findByPageCountLessThanEquals()");
			
			books = repository.findByPageCountBetween(100, 200);
			printList(books, System.out::println, "Printing Books method: findByPageCountBetween()");
			
		}
	}

	public static <T, U> void printList(List<T> ts, Consumer<T> consumer, String u) {
		System.out.println(u);
		for (T t : ts) {
			consumer.accept(t);
		}
	}

}
