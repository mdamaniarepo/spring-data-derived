package com.musings.sdata.query;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.musings.sdata.entities.Book;
import com.musings.sdata.repository.BookRepository;

public class SortingTestApp {
	
	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml")) {

			BookRepository repository = context.getBean(BookRepository.class);

			List<Book> books = repository.findAll(new Sort(Direction.ASC, "price", "pageCount"));
			printList(books, System.out::println, "Printing Books method: findAll()");
			
			books = repository.findAll(new Sort(Direction.ASC, "price").and(new Sort(Direction.DESC, "pageCount")));
			printList(books, System.out::println, "Printing Books method: findAll()");
			
			books = repository.findByPageCountLessThan(400, new Sort(Direction.DESC, "price"));
			printList(books, System.out::println, "Printing Books method: findAll()");
		}
	}

	public static <T, U> void printList(List<T> ts, Consumer<T> consumer, String u) {
		System.out.println(u);
		for (T t : ts) {
			consumer.accept(t);
		}
	}

}
