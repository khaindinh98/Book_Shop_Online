package com.example.demo.model;

import java.io.Serializable;
import java.sql.Date;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
public class Author implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "birthday")
	private Date birthday;
	
	@Column(name = "description", columnDefinition="TEXT")
	private String description;
	
	@Column(name = "status")
	private String status;
	
	@ManyToMany(mappedBy = "authors")
	@EqualsAndHashCode.Exclude
	@Exclude
	private List<Book> books;	
	
//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @JoinTable(name = "book_author", //Tạo ra một join Table tên là "address_person"
//            joinColumns = @JoinColumn(name = "author_id"),  // TRong đó, khóa ngoại chính là address_id trỏ tới class hiện tại (Address)
//            inverseJoinColumns = @JoinColumn(name = "book_id")) //Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới (Person)
//	private Collection<Book> books;	
	
	

	public Author(String name, Date birthday, String description) {
		super();
		this.name = name;
		this.birthday = birthday;
		this.description = description;
	}
	
}
