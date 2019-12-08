package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detail_order")
@Getter
@Setter
@Data
public class DetailOrder {

	@EmbeddedId
	private DetailOrderId detailOrderId;
	
	@MapsId(value = "order_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private Order order;
	
	@MapsId(value = "book_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id", referencedColumnName = "id")
	private Book book;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "unit_price")
	private int unitPrice;
	
//	public void addOrder(Order order) {
//		this.order = order;
//		order.getOrderDetails().add(this);
//	}
	

}
