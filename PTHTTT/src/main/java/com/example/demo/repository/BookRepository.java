package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	Book findById(int id);
	List<Book>findByNameContainingAndStatus(String name, String status);
	List<Book> findByNameContainingOrderByStatus(String key);
	List<Book> findByNameContaining(String key);
	List<Book> findByName(String name);
}
