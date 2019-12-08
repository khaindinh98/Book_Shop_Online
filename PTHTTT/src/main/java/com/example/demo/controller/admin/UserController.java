package com.example.demo.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.example.demo.service.UserService;

@Controller
@RequestMapping(value = "/admin/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	public Role getRoleById(Integer id) {
		return roleRepository.getOne(id);
	}
	public List<Role> getAllRole() {
		return roleRepository.findAll();
	}
	
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	
	@RequestMapping(value = {"/","/**"}, method = RequestMethod.GET)
	public String outline(Model model) {
		return "redirect:/admin/users/";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(UserForm userForm, RedirectAttributes redirect) {
		User user;
		if (userForm.getId() == -1){
			user = new User();
			user.setFullName(userForm.getFullname());
			user.setEmail(userForm.getEmail());
			user.setPhone(userForm.getPhone());
			user.setUsername(userForm.getUsername());
			user.setPassword(passwordEncoder.encode(userForm.getPassword()));
			
			List<Role> roles = new ArrayList<Role>();
			Integer[] intRoles = userForm.getRoles();
			if (intRoles!=null && intRoles.length !=0) {
				for (Integer id : intRoles) {
					roles.add(getRoleById(id));
				}
			}
			user.setRoles(roles);
			userRepository.save(user);
		}
		return "redirect:/admin/users/";
	}
	
//	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//	public String deleteAuthor(@PathVariable Integer id, RedirectAttributes redirect) {
//		//userService.delete(id);
//		return "redirect:/admin/users/";
//	}
	
//	@RequestMapping(value = "/save", method = RequestMethod.POST)
//	public String saveAuthor(Author author, RedirectAttributes redirect) {
//		author.setStatus("activated");
//		return "redirect:/admin/users/";
//	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editProduct(UserForm userForm, RedirectAttributes redirect) {
		User user;
		if (userForm.getId() != -1) {
			user = userRepository.findById(userForm.getId());
			user.setFullName(userForm.getFullname());
			user.setEmail(userForm.getEmail());
			user.setPhone(userForm.getPhone());
			user.setUsername(userForm.getUsername());
			user.setPassword(passwordEncoder.encode(userForm.getPassword()));
			
			List<Role> roleUsers = user.getRoles();
			Integer[] intRoles = userForm.getRoles();
			System.out.println(intRoles.length);
			if (intRoles != null) {
				user.setRole();
				//userRepository.save(user);
				List<Role> listRole = new ArrayList<Role>();
				for (int i = 0; i < intRoles.length; i ++) {
					listRole.add(getRoleById(intRoles[i]));
				}
				user.setRoles(listRole);
				userRepository.save(user);
				
			}
//			if (intRoles != null && roleUsers.size() > intRoles.length) {
//				List<Role> removeRoles = new ArrayList<Role>();
//				System.out.println("Test update user 1");
//				for (Role role : roleUsers) {
//					if(!containsIntAuthors(role.getId(), intRoles)) {
//						removeRoles.add(role);
//					}
//				}
//				roleUsers.removeAll(removeRoles);
//			} else if (intRoles != null && roleUsers.size() < intRoles.length){
//				List<Role> addRoles = new ArrayList<Role>();
//				System.out.println("Test update product 2");
//				for (Integer intRole : intRoles) {
//					if(!containsRoles(intRole, roleUsers)){
//						addRoles.add(getRoleById(intRole));
//					}
//				}
//				roleUsers.addAll(addRoles);
//			}
			
			userRepository.save(user);
			
		}
		return "redirect:/admin/users/";
	}
	
	private boolean containsIntAuthors(int roleId, Integer[] intRoles) {
		for(Integer intAuthor : intRoles)
			if(intAuthor.equals(roleId))
				return true;
		return false;
	}
	
	private boolean containsRoles(int roleId, List<Role> roles) {
		for(Role role : roles)
			if(role.getId() == roleId)
				return true;
		return false;
	}
	

}
