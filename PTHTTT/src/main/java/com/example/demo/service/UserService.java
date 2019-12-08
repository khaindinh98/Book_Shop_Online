package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.model.User;

public interface UserService extends UserDetailsService{
	User findByUsername(String username);
	User save(UserRegistrationDto userRegistrationDto);
	void saveCart(User user);
	User findById(Integer id);
}
