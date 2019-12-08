package com.example.demo.response;

import java.sql.Date;

import com.example.demo.model.Category;
import com.example.demo.model.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRest {

	private int id;
	
	private UserRest user;
	
	private int totalPrice;
	
	private String statusOrder;
	
	private Date dateOrder;
	
	private String addressDetail;
	
	public OrderRest(Order order) {
		super();
		if(order!=null) {
			this.id = order.getId();
			this.user = new UserRest(order.getUser());
			this.totalPrice = order.getTotalPrice();
			this.statusOrder = order.getStatusOrder();
//			this.dateOrder = order.getDateOrder();
			this.addressDetail = order.getAddressDetails();
		}
	}

}
