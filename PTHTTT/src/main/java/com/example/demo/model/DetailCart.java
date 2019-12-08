package com.example.demo.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detail_cart")
@Getter
@Setter
@Data
public class DetailCart {

	
	@EmbeddedId
	private DetailCartId id;
	
	@MapsId(value = "cart_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id", referencedColumnName = "id")
	private Cart cart;
	
	@MapsId(value = "book_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id", referencedColumnName = "id")
	private Book book;
	
	@Column(name = "quantity")
	private int quanlity;

	public DetailCart() {
		
	}	

}
