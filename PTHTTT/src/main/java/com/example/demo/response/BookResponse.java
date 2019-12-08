package com.example.demo.response;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.example.demo.model.Category;
import com.example.demo.model.DetailCart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

	private int id;
	
	private String name;

	private int price;

	private String description;
	
	private String category;
	
	private String pathImages;
	
	//private Set<DetailsCart> detailsCarts;
	
	//private Set<BookAuthor> bookAuthor;
	
	private String authorName;

}
