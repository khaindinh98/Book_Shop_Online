package com.example.demo.response;

import java.sql.Date;

import com.example.demo.model.Author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRest {

	private int id;
	
	private String name;
	
	private Date birthDay;

	private String description;

	public AuthorRest(Author author) {
		super();
		if(author!=null) {
			this.id = author.getId();
			this.name = author.getName();
			this.birthDay = author.getBirthday();
			this.description = author.getDescription();
		}
	}
	
	
}
