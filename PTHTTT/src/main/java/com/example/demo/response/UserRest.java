package com.example.demo.response;

import com.example.demo.model.Order;
import com.example.demo.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRest {
	
	private String username;
	private String fullName;

	public UserRest(User user) {
		super();
		if(user!=null) {
			this.username = user.getUsername();
			this.fullName = user.getFullName();
		}
	}
	
}
