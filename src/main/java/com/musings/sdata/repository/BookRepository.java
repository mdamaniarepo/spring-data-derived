package com.musings.sdata.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.musings.sdata.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	/************************** String Operators *****************************/

	/*
	 * select book0_.BOOK_ID as BOOK_ID1_0_, book0_.PAGE_COUNT as PAGE_COU2_0_,
	 * book0_.PRICE as PRICE3_0_, book0_.PUBLISH_DATE as PUBLISH_4_0_, book0_.TITLE
	 * as TITLE5_0_ from BOOK book0_ where book0_.TITLE=?
	 * 
	 * 
	 * methods can return page, slice, iterable, collection
	 */
	List<Book> findByTitle(String title);

	/*
	 * select book0_.BOOK_ID as BOOK_ID1_0_, book0_.PAGE_COUNT as PAGE_COU2_0_,
	 * book0_.PRICE as PRICE3_0_, book0_.PUBLISH_DATE as PUBLISH_4_0_, book0_.TITLE
	 * as TITLE5_0_ from BOOK book0_ where book0_.TITLE like ?
	 * 
	 * Note: All the queries below use like clause but like matches the whole title
	 * and not just a part of the title as its predicate
	 */

	List<Book> findByTitleLike(String title);

	List<Book> findByTitleContaining(String keyword);

	List<Book> findByTitleStartsWith(String keyword);

	List<Book> findByTitleEndsWith(String keyword);

	/* This will convert the string to upper case and then compare */
	List<Book> findByTitleIgnoreCase(String title);

	/****************************** Relational Operator *****************************************/
	List<Book> findByPageCountEquals(Integer count);

	List<Book> findByPageCountGreaterThan(Integer count, Pageable pageable);

	List<Book> findByPageCountGreaterThanEqual(Integer count);

	List<Book> findByPageCountLessThan(Integer count, Sort sort);

	List<Book> findByPageCountLessThanEqual(Integer count);

	List<Book> findByPageCountBetween(Integer min, Integer max);
	
	/***************************** Logical Operator ***********************************************/
	
	List<Book> findByTitleContainingAndPageCountGreaterThan(String title, Integer pageCount);
	
	List<Book> findByTitleContainingOrTitleContaining(String title, String title2);
	
	List<Book> findByTitleNot(String title);
	
	
	/***************************** Date Comparison ***********************************************/
	
	List<Book> findByPublishDateAfter(Date publishDate);
	
	List<Book> findByPublishDateBefore(Date publishDate);
	
	List<Book> findByPublishDateBetween(Date dateAfter, Date dateBefore);
	
	/***************************** Order By ***********************************************/
	
	List<Book> findByTitleContainingOrderByTitleAsc(String title);
	
	List<Book> findByPublishDateAfterOrderByPriceDesc(Date publishDate);
	
	
	/***************************** Limiting Query ***********************************************/
	
	List<Book> findFirstByOrderByPriceDesc();
	
	List<Book> findFirst5ByTitleContainingOrderByPageCountDesc(String title);
	
	/***************************** Nested Query ***********************************************/
	
	List<Book> findByAuthorLastNameContaining(String lastName);
	
	List<Book> findByAuthor_Country(String country);
	
	
	/***************************** Query Annotation***********************************************/
	
	// The naming convention to be followed is the object name and field names and not the db column names
	// The ordinality of params is 1 based
	@Query("select b from Book b where b.title like ?1 order by b.price desc")
	List<Book> findByTitleOrderByPriceDesc(String title);
	
	@Query("select b from Book b where b.id IN :ids order by b.price desc")
	List<Book> findByIds(@Param("ids") Long...ids);
	
	List<Book> findByTitleOrderByPriceDescNamedQuery(String title);
	
	List<Book> findByIdsNamedQuery(@Param("id") List<Long> id);
}
