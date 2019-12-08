package com.example.demo.form;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Category;

import lombok.Data;

@Data
public class CategoryForm {

	private int id = -1;

	private String title;

	private String description;

	private MultipartFile fileData;
	
	public CategoryForm() {
		super();
	}
	
	public CategoryForm(Category category) {
		super();
		this.id = category.getId();
		this.title = category.getTitle();
		this.description = category.getDescription();
	}
}
