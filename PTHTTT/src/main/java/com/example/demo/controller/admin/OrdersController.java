package com.example.demo.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.form.BookForm;
import com.example.demo.form.OrderForm;
import com.example.demo.model.Author;
import com.example.demo.model.Category;
import com.example.demo.model.Order;
import com.example.demo.service.CategoryService;
import com.example.demo.service.OrderService;

@Controller
@RequestMapping(value = "/admin/orders")
public class OrdersController {
	
	@Autowired
    private OrderService orderService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String manange(Model model) {
		model.addAttribute("orders", orderService.getAllOrder());
		return "admin/ManageOrders";
	}
	
	@RequestMapping(value = "/detailorder/{id}", method = RequestMethod.GET)
	public String addCategory(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("order", orderService.getOrderById(id));
		return "admin/Order";
	}
//	
//	@RequestMapping(value = "/editcategory/{id}", method = RequestMethod.GET)
//	public String editCategory(@PathVariable("id") Integer id, Model model) {
//		model.addAttribute("title", "Edit New Category");
//		model.addAttribute("category", categoryService.getCategoryById(id));
//		return "admin/Category";
//	}
	
}
