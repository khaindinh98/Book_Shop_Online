package com.example.demo.service;

import com.example.demo.model.Cart;

public interface CartService {
	Cart getById(Integer id);
	void save(Cart cart);

}
