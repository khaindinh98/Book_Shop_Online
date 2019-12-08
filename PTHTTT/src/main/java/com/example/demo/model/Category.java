package com.example.demo.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
public class Category implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
		
	@Column(name = "status")
	private String status;
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
//	private Set<Book> books;
	
//	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER) 
	@OneToMany(mappedBy = "category")
	@EqualsAndHashCode.Exclude 
	@ToString.Exclude
    private List<Book> books;
	
	@Column(name = "img")
	private String img;

	@Override
	public String toString() {
		return  "Category: " + title;
	}
	

}
