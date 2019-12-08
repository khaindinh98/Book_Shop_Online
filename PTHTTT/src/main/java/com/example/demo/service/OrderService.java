package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Order;

public interface OrderService {
	
	Order getOrderById(Integer id);
	
	List<Order> getAllOrder();
	
	List<Order> findOrdersByUsername(String key);
	
	void saveOrder(Order order);
	
//	void saveAndFlush(Order order);
	
	void delete(Integer id);

}
