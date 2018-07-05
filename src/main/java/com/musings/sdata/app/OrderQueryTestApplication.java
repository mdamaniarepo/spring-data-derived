package com.musings.sdata.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.musings.sdata.entities.Book;
import com.musings.sdata.repository.BookRepository;

public class OrderQueryTestApplication {

	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml")) {

			BookRepository repository = context.getBean(BookRepository.class);

			List<Book> books = repository.findByTitleContainingOrderByTitleAsc("The");
			printList(books, System.out::println,
					"Printing Books method: findByTitleContainingOrderByTitleAsc()");
			
			books = repository.findByPublishDateAfterOrderByPriceDesc(new SimpleDateFormat("MM/dd/yyyy").parse("01/01/1960"));
			printList(books, System.out::println, "Printing Books method: findByPublishDateAfterOrderByPriceDesc()");

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static <T, U> void printList(List<T> ts, Consumer<T> consumer, String u) {
		System.out.println(u);
		for (T t : ts) {
			consumer.accept(t);
		}
	}

}
