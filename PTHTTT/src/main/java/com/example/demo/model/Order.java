package com.example.demo.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "total_price")
	private int totalPrice;
	
	@Column(name = "status_order")
	private String statusOrder;
	
	@Column(name = "date_order", columnDefinition = "TIMESTAMP")
	private LocalDateTime dateOrder;
	
	@Column(name = "address_details")
	private String addressDetails;
	
	//edit add new column phone
	@Column(name = "phone")
	private String phone;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<DetailOrder> detailOrders;
	
//	public void addDetails(OrderDetails details) {
//		this.orderDetails.add(details);
//		details.setOrder(this);
//	}
//	
	public void addUser(User user) {
		this.user = user;
		user.getOrder().add(this);
	}
	
}
