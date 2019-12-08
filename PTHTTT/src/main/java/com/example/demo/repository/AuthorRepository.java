package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{
	Author findById(int id);
	List<Author> findByNameContaining(String key);
	List<Author> findByName(String name);
}
