package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	Category findById(int id);
	List<Category> findByTitleContaining(String key);
	Category findByTitle(String title);
}
