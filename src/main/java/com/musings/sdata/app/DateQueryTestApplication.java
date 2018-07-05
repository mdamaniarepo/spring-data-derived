package com.musings.sdata.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.musings.sdata.entities.Book;
import com.musings.sdata.repository.BookRepository;

public class DateQueryTestApplication {
	
	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml")) {

			BookRepository repository = context.getBean(BookRepository.class);

			Date publishDate = new SimpleDateFormat("MM/dd/yyyy").parse("10/01/1990");
			Date publishDate2 = new SimpleDateFormat("MM/dd/yyyy").parse("10/01/1960");
			List<Book> books = repository.findByPublishDateAfter(publishDate);
			printList(books, System.out::println, "Printing Books method: findByPublishDateAfter()");

			books = repository.findByPublishDateBetween(publishDate2, publishDate);
			printList(books, System.out::println, "Printing Books method: findByPublishDateBetween()");
			
			books = repository.findByPublishDateBefore(publishDate);
			printList(books, System.out::println, "Printing Books method: findByPublishDateBefore()");
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
