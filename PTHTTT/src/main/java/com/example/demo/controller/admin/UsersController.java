package com.example.demo.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.form.BookForm;
import com.example.demo.form.UserForm;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthorService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping(value = "/admin/users")
public class UsersController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Role getRoleById(int id) {
		return roleRepository.findById(id);
	}
	public List<Role> getAllRole() {
		return roleRepository.findAll();
	}
	
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String manange(Model model) {
		model.addAttribute("users", getAll());
		return "admin/ManageUsers";
	}
	
	
	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	public String addProduct(Model model) {
		UserForm userForm = new UserForm();
		model.addAttribute("title", "Add New User");
		model.addAttribute("url", "/admin/user/add/");
		//model.addAttribute("listCategories", categoryService.getAllCategory());
		model.addAttribute("listRoles", getAllRole());
		model.addAttribute("userForm", userForm);
		return "admin/User";
	}
	
	@RequestMapping(value = "/edituser/{id}", method = RequestMethod.GET)
	public String editProduct(@PathVariable("id") Integer id, Model model) {
		UserForm userForm = new UserForm(userRepository.findById(id));
		model.addAttribute("title", "Edit New User");
		model.addAttribute("url", "/admin/user/edit/");
		//model.addAttribute("listCategories", categoryService.getAllCategory());
		model.addAttribute("listRoles", getAllRole());
		model.addAttribute("userForm", userForm);
		return "admin/User";
	}

}

	


