package com.example.demo.controller.admin;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.form.BookForm;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;

@Controller
@RequestMapping(value = "/admin/products")
public class ProductsController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AuthorService authorService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String manange(Model model) {
		model.addAttribute("products", bookService.getAllBook());
		return "admin/ManageProducts";
	}
	
	@RequestMapping(value = "/addproduct", method = RequestMethod.GET)
	public String addProduct(Model model) {
		BookForm bookForm = new BookForm();
		model.addAttribute("title", "Add New Product");
		model.addAttribute("url", "/admin/product/add/");
		model.addAttribute("listCategories", categoryService.getAllCategory());
		model.addAttribute("listAuthors", authorService.getAllAuthor());
		model.addAttribute("bookForm", bookForm);
		return "admin/Product";
	}
	
	@RequestMapping(value = "/editproduct/{id}", method = RequestMethod.GET)
	public String editProduct(@PathVariable("id") Integer id, Model model) {
		BookForm bookForm = new BookForm(bookService.getBookById(id));
		model.addAttribute("title", "Edit New Product");
		model.addAttribute("url", "/admin/product/edit/");
		model.addAttribute("listCategories", categoryService.getAllCategory());
		model.addAttribute("listAuthors", authorService.getAllAuthor());
		model.addAttribute("bookForm", bookForm);
		return "admin/Product";
	}
}
