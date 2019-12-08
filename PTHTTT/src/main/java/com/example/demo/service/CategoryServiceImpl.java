package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category getCategoryById(Integer id) {
		return categoryRepository.findById(id).orElse(null);
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public void saveCategory(Category category) {
		categoryRepository.save(category);
		
	}

	@Override
	public void delete(Integer id) {
		categoryRepository.deleteById(id);
		
	}

	@Override
	public void saveAndFlush(Category category) {
		categoryRepository.saveAndFlush(category);
	}

	@Override
	public List<Category> findCategoriesByTitle(String key) {
//		List<Category> categeories = categoryRepository.findByTitle(key);
		return categoryRepository.findByTitleContaining(key);
//		if(categeories.size()!=0) {
//			for(Category category : containCategories) {
//				if(!categeories.contains(category)) {
//					categeories.add(category);
//				}
//			}
//			return categeories;
//		}
//		return containCategories;
	}


}
