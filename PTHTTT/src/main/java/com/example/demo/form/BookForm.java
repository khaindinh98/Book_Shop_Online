package com.example.demo.form;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Book;

import lombok.Data;

@Data
public class BookForm {

	private int id = -1;

	private String name;

	private String description;

	private int priceSell = 0;

	private int priceBuy = 0;
	
	private int quantity = 0;
	
	private String size;
	
	private String pages;

	private Integer[] authors;

	private Integer category = -1;

	private MultipartFile fileData;

	public BookForm() {
		super();
	}
	
	public BookForm(Book book) {
		super();
		this.id = book.getId();
		this.name = book.getName();
		this.description = book.getDescription();
		this.size = book.getSize();
		this.pages = book.getPages();
		this.priceBuy = book.getPriceBuy();
		this.priceSell = book.getPriceSell();
		this.quantity = book.getQuantity();
		this.authors = new Integer[book.getAuthors().size()];
		for (int i = 0; i < book.getAuthors().size(); i++)
			this.authors[i] = book.getAuthors().get(i).getId();
		if (book.getCategory() != null)
			this.category = book.getCategory().getId();
	}	

}
