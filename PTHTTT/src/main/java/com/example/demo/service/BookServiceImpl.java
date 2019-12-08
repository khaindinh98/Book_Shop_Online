package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book getBookById(Integer id) {
//		return bookRepository.getOne(id);
		return bookRepository.findById(id).orElse(null);
	}

	@Override
	public List<Book> getAllBook() {
//		return bookRepository.findAll();
		return bookRepository.findByNameContainingOrderByStatus("");
	}

	@Override
	public void saveBook(Book book) {
		bookRepository.save(book);
		
	}

	@Override
	public void delete(Integer id) {
		bookRepository.deleteById(id);
	}

	@Override
	public List<Book> searchBook(String term) {
		return bookRepository.findByNameContainingOrderByStatus(term);
	}
	
	@Override
	public List<Book> findBooksByName(String key) {
		List<Book> books = bookRepository.findByName(key);
		List<Book> containBooks = bookRepository.findByNameContaining(key);
		if(books.size()!=0) {
			for(Book book : containBooks) {
				if(!books.contains(book)) {
					books.add(book);
				}
			}
			return books;
		}
		return containBooks;
	}
	
	@Override
	public List<Book> getBooksBesideIds(String name, JSONArray bookIds) {
		System.out.println(bookIds);
		List<Book> books = bookRepository.findByNameContainingAndStatus("", "activated");
		if(bookIds!=null&&bookIds.size()!=0) {
			for(Object bookId :  bookIds.toArray()){
				Book book = bookRepository.getOne(Integer.parseInt(""+bookId));
				books.remove(book);		
			}
		}
		return books;
	}
	
	@Override
	public List<Book> getBooksByStatus(String status) {
		 return bookRepository.findByNameContainingAndStatus("", status);
	}

}
