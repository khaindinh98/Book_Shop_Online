package com.example.demo.form;

import java.util.List;

import com.example.demo.model.DetailOrder;

import lombok.Data;

@Data
public class OrderForm {

	private int id = -1;

	private String username;
	
	private String fullName;

	private String address;
	
	private String dateOrder;

	private String statusOrder;
	
	private int totalPrice;
	
	private List<DetailOrder> detailOrders;
	
}
