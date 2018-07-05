package com.musings.sdata.query;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.musings.sdata.entities.Book;
import com.musings.sdata.repository.BookRepository;

public class QueryAnnotationTestApp {

	@SuppressWarnings("serial")
	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml")) {

			BookRepository repository = context.getBean(BookRepository.class);

			List<Book> books = repository.findByTitleOrderByPriceDesc("%The%");
			printList(books, System.out::println, "Printing Books method: findByTitleOrderByPriceDesc()");
			
			books = repository.findByIds(1L, 2L, 3L);
			printList(books, System.out::println, "Printing Books method: findByIds()");
			
			books = repository.findByTitleOrderByPriceDescNamedQuery("%The%");
			printList(books, System.out::println, "Printing Books method: findByTitleOrderByPriceDescNamedQuery()");
			
			books = repository.findByIdsNamedQuery(new ArrayList<Long>() {{
				add(1L);
				add(2L);
				add(3L);
			}});
			printList(books, System.out::println, "Printing Books method: findByIdsNamedQuery()");
			
		}
	}

	public static <T, U> void printList(List<T> ts, Consumer<T> consumer, String u) {
		System.out.println(u);
		for (T t : ts) {
			consumer.accept(t);
		}
	}

}
