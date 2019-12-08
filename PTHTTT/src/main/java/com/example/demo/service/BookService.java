package com.example.demo.service;

import java.util.List;

import org.json.simple.JSONArray;

import com.example.demo.model.Book;

public interface BookService {
	
	Book getBookById(Integer id);
	
	List<Book> getAllBook();
	
	List<Book> findBooksByName(String key);
	
	void saveBook(Book book);
	
	void delete(Integer id);
	
	List<Book> searchBook(String term);

	List<Book> getBooksBesideIds(String name, JSONArray bookIds);
	//edit
	List<Book> getBooksByStatus(String status);
}
