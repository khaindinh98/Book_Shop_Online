package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Book;
import com.example.demo.model.DetailOrder;
import com.example.demo.model.Order;
import com.example.demo.service.BookService;
import com.example.demo.service.OrderService;

@Controller
@RequestMapping(value = "/admin/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	BookService bookService;
	
	@RequestMapping(value = "/check/{id}", method = RequestMethod.GET)
	public String check(@PathVariable("id") Integer id, RedirectAttributes redirect) {
		Order order = orderService.getOrderById(id);
		order.setStatusOrder("resolved");
		for(DetailOrder detailOrder: order.getDetailOrders()) {
			Book book = bookService.getBookById(detailOrder.getBook().getId());
			book.setQuantity(book.getQuantity()-detailOrder.getQuantity());
			bookService.saveBook(book);
		}
		orderService.saveOrder(order);
		return "redirect:/admin/orders/";
	}
	
//	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//	public String deleteCategory(@PathVariable Integer id, RedirectAttributes redirect) {
//		orderService.delete(id);
//		return "redirect:/admin/orders/";
//	}
}
