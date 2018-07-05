package com.musings.sdata.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK")
@NamedQueries({
		@NamedQuery(name="Book.findByTitleOrderByPriceDescNamedQuery", query="select b from Book b where b.title like ?1 order by b.price desc"),
		@NamedQuery(name="Book.findByIdsNamedQuery", query="select b from Book b where b.id IN :id order by b.price desc")
		})
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOOK_ID")
	private Long id;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "PUBLISH_DATE")
	private Date publishDate;

	@Column(name = "PRICE")
	private BigDecimal price;

	@Column(name = "PAGE_COUNT")
	private Integer pageCount;
	
	@ManyToOne
	@JoinColumn(name="AUTHOR_ID")
	private Author author;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + id + ", title=" + title + ", publishDate=" + publishDate + ", pageCount="
				+ pageCount + ", price=" + price + ", author=" + author + "]";
	}

}
