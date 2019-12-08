package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.User;
import com.example.demo.service.BookService;

@Controller
@RequestMapping("/admin")
public class MainAdminController {
	
//	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
//	public String index(Model model) {
////		model.addAttribute("user", new User());
//		return "admin/ManageProducts";
//	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String statistic(Model model) {
		return "admin/Index";
	}
	

}
