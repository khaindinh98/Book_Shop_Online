package com.example.demo.form;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Book;
import com.example.demo.model.User;

import lombok.Data;

@Data
public class UserForm {
	private int id = -1;

	private String fullname;
	
	private String email;
	
	private String phone;
	
	private String username;
	
	private String password;
	
	private Integer[] roles;
	
	public UserForm() {
		super();
	}
	
	public UserForm(User user) {
		super();
		this.id = user.getId();
		this.fullname = user.getFullName();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.username = user.getUsername();
		this.password = user.getPassword();
				
		
		this.roles = new Integer[user.getRoles().size()];
		for (int i = 0; i < user.getRoles().size(); i++)
			this.roles[i] = user.getRoles().get(i).getId();
		
	}	

}
