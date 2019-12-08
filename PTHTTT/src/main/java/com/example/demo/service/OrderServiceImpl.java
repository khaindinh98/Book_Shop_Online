package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order getOrderById(Integer id) {
		return orderRepository.findById(id).orElse(null);
	}

	@Override
	public List<Order> getAllOrder() {
		return orderRepository.findAll();
	}

	@Override
	public void saveOrder(Order order) {
		orderRepository.save(order);
		
	}

	@Override
	public void delete(Integer id) {
		orderRepository.deleteById(id);
		
	}

//	@Override
//	public void saveAndFlush(Order order) {
//		orderRepository.saveAndFlush(order);
//	}

	@Override
	public List<Order> findOrdersByUsername(String key) {
		List<Order> orders = orderRepository.findByUserUsername(key);
		List<Order> containOrders = orderRepository.findByUserUsernameContaining(key);
		if(orders.size()!=0) {
			for(Order order : orders) {
				if(!orders.contains(order)) {
					orders.add(order);
				}
			}
			return orders;
		}
		return containOrders;
	}
}
