package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Category;

public interface CategoryService {
	
	Category getCategoryById(Integer id);
	
	List<Category> getAllCategory();
	
	List<Category> findCategoriesByTitle(String key);
	
	void saveCategory(Category category);
	
	void saveAndFlush(Category category);
	
	void delete(Integer id);

}
