package com.example.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cart")
@Getter
@Setter
@NoArgsConstructor
public class Cart { //implements Serializable
	@Id
	private int id;
	
	@OneToOne
	@JoinColumn(name = "id",referencedColumnName = "id")
	@MapsId
	private User user;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<DetailCart> detailCarts = new ArrayList<DetailCart>();

	public void addDetailCart(DetailCart detailCart) {
		this.detailCarts.add(detailCart);
		detailCart.setCart(this);
	}
	
	public void setDetailCarts() {
		this.detailCarts.clear();
		//this.detailsCart.add(list);
	}
	public void removeDetailCart(DetailCart detailCart) {
		this.detailCarts.remove(detailCart);
		detailCart.setCart(null);
	}
	
	
	
	

}
