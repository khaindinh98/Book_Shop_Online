package com.example.demo.response;

import com.example.demo.model.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRest {

	private int id;

	private String title;
	
	private String description;
	
	private String img;
	
	public CategoryRest(Category category) {
		super();
		if(category!=null) {
			this.id = category.getId();
			this.title = category.getTitle();
			this.description = category.getDescription();
			this.img = category.getImg();
		}
	}
}
