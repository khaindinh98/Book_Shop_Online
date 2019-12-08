package com.example.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class Book { //implements Serializable
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "price_buy")
	private int priceBuy;
	
	@Column(name = "price_sell")
	private int priceSell;
	
	@Column(name = "quantity")
	private int quantity;	

	@Column(name = "description", columnDefinition="TEXT")
	private String description;
	
	
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<DetailCart> detailsCart = new ArrayList<DetailCart>();
	
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<DetailOrder> detailOrders = new ArrayList<DetailOrder>();
	
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetailInvoice> detailInvoices = new ArrayList<DetailInvoice>();
	
	@Column(name = "path_images")
	private String pathImages;
	
	@Column(name = "status")
	private String status="activated";
	
	@Column(name = "size")
	private String size;
	
	
	@Column(name = "pages")
	private String pages;

	@ManyToMany(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "book_author", //Tạo ra một join Table tên là "address_person"
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),  // TRong đó, khóa ngoại chính là address_id trỏ tới class hiện tại (Address)
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")) //Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới (Person)
	private List<Author> authors;
	
//	@ManyToMany(mappedBy = "books")
//	@EqualsAndHashCode.Exclude
//	@Exclude
//	private Collection<Author> authors;
	
	@ManyToOne 
	@JoinColumn(name = "category_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Category category;
	
	
}
