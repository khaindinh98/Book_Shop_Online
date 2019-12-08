package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService{
	
	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public Author findById(Integer id) {
		return authorRepository.getOne(id);
	}

	@Override
	public Author findOne(int id) {
		return authorRepository.getOne(id);
	}
	
	@Override
	public void save(Author a) {
		authorRepository.save(a);
		
	}

	@Override
	public void saveAndFlush(Author a) {
		authorRepository.saveAndFlush(a);
		
	}

	@Override
	public void delete(Integer id) {
		authorRepository.deleteById(id);
	}

	@Override
	public List<Author> getAllAuthor() {
		return authorRepository.findAll();
	}

	@Override
	public List<Author> findAuthorsByName(String key) {
		// TODO Auto-generated method stub
		List<Author> authors = authorRepository.findByName(key);
		List<Author> containAuthors = authorRepository.findByNameContaining(key);
		if(authors.size()!=0) {
			for(Author author : containAuthors) {
				if(!authors.contains(author)) {
					authors.add(author);
				}
			}
			return authors;
		}
		return containAuthors;
	}

//	@Override
//	public void update(Author a) {
//		Author t = authorRepository.getOne(a.getId());
//		if (t != null) {
//			
//		}
//		
//		
//		
//		
//		
//	}

}
