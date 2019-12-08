package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepository cartRepository;

	@Override
	public Cart getById(Integer id) {
		return cartRepository.findById(id).orElse(null);
	}

	@Override
	public void save(Cart cart) {
		cartRepository.save(cart);
		
	}

}
