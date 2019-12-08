package com.example.demo.response;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Author;
import com.example.demo.model.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRest{

	private int id;
	
	private String name;

	private int priceBuy;
	
	private int priceSell;

	private String description;
	
	private CategoryRest category;
	
	private String pathImages;
	
	private List<AuthorRest> bookAuthor;
	
	private int quantity;

	public BookRest(Book book) {
		super();
		if(book!=null) {
			this.id = book.getId();
			this.name = book.getName();
			this.priceBuy = book.getPriceBuy();
			this.priceSell = book.getPriceSell();
			this.description = book.getDescription();
			this.category = new CategoryRest(book.getCategory());
			this.pathImages = book.getPathImages();
			bookAuthor = new ArrayList<AuthorRest>();
			for(Author author : book.getAuthors())
				this.bookAuthor.add(new AuthorRest(author));
			this.quantity = book.getQuantity();
		}
	}

}
