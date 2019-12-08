package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Author;

public interface AuthorService {
	Author findById(Integer id);

	Author findOne(int id);
	
	List<Author> getAllAuthor();
	
	List<Author> findAuthorsByName(String key);

	void save(Author a);

	void delete(Integer id);

	void saveAndFlush(Author a);
	// void update(Author a);

}
