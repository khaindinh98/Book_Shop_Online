package com.example.demo.controller.admin;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.model.Order;
import com.example.demo.response.AuthorRest;
import com.example.demo.response.BookRest;
import com.example.demo.response.CategoryRest;
import com.example.demo.response.OrderRest;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.OrderService;

import org.json.simple.JSONValue;
import org.json.simple.JSONArray; 

@RestController
@RequestMapping(value="/admin/search")
public class AdminRestController {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value="/productsOfInvoice", method = RequestMethod.GET)
	public ResponseEntity<Object> searchProductsOfInvoices(@RequestParam(name="key") String key, @RequestParam(name="detailInvoices") String detailInovices, RedirectAttributes redirect) {
		JSONArray jsonDetailInvoices =  (JSONArray)JSONValue.parse(new String(Base64.getDecoder().decode(detailInovices)));
		List<Book> books = bookService.getBooksBesideIds(key, jsonDetailInvoices);
		List<BookRest> booksRest = new ArrayList<BookRest>();
		for(Book book : books)
			booksRest.add(new BookRest(book));
		return new ResponseEntity<Object>(booksRest, HttpStatus.OK);
	}
	
	@RequestMapping(value="/products", method = RequestMethod.GET)
	public ResponseEntity<Object> searchProducts(@RequestParam(name="key") String key, RedirectAttributes redirect) {
		List<Book> books = bookService.findBooksByName(key);
		List<BookRest> booksRest = new ArrayList<BookRest>();
		for(Book book : books)
			booksRest.add(new BookRest(book));
		return new ResponseEntity<Object>(booksRest, HttpStatus.OK);
	}
	
	@RequestMapping(value="/categories", method = RequestMethod.GET)
	public ResponseEntity<Object> searchCategories(@RequestParam(name="key") String key, RedirectAttributes redirect) {
		List<Category> categories = categoryService.findCategoriesByTitle(key);
		List<CategoryRest> categoriesRest = new ArrayList<CategoryRest>();
		for(Category category : categories)
			categoriesRest.add(new CategoryRest(category));
		return new ResponseEntity<Object>(categoriesRest, HttpStatus.OK);
	}
	
	@RequestMapping(value="/authors", method = RequestMethod.GET)
	public ResponseEntity<Object> searchAuthors(@RequestParam(name="key") String key, RedirectAttributes redirect) {
		List<Author> authors = authorService.findAuthorsByName(key);
		List<AuthorRest> authorsRest = new ArrayList<AuthorRest>();
		for(Author author : authors)
			authorsRest.add(new AuthorRest(author));
		return new ResponseEntity<Object>(authorsRest, HttpStatus.OK);
	}
	
	@RequestMapping(value="/orders", method = RequestMethod.GET)
	public ResponseEntity<Object> searchOrders(@RequestParam(name="key") String key, RedirectAttributes redirect) {
		List<Order> orders = orderService.findOrdersByUsername(key);
		List<OrderRest>ordersRest = new ArrayList<OrderRest>();
		for(Order order : orders)
			ordersRest.add(new OrderRest(order));
		return new ResponseEntity<Object>(ordersRest, HttpStatus.OK);
	}
	
}
