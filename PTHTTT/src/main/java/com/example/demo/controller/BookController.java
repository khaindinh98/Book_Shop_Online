package com.example.demo.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.internal.util.xml.DTDEntityResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Cart;
import com.example.demo.model.Category;
import com.example.demo.model.DetailCart;
import com.example.demo.model.DetailCartId;
import com.example.demo.model.Order;
import com.example.demo.model.DetailOrder;
import com.example.demo.model.DetailOrderId;
import com.example.demo.model.User;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.response.BookResponse;
import com.example.demo.response.CartResponse;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import com.example.demo.service.CartService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CartService cartService;

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@GetMapping("/detailsCart/{idCart}")
	public String detailCart(@PathVariable("idCart") int id, Model model) {
		Cart cart = cartService.getById(id);
		if (cart == null) {
			return "redirect:/";
		} else {
			List<DetailCart> list = new ArrayList<>();
			list = cart.getDetailCarts();

			List<CartResponse> cartReponse = new ArrayList<>();
			for (DetailCart d : list) {
				int number = d.getQuanlity();
				Book book = bookService.getBookById(d.getBook().getId());
				CartResponse c = new CartResponse(id, book.getId(), book.getName(), book.getPathImages(),
						book.getPriceSell(), number);
				cartReponse.add(c);
			}
			model.addAttribute("list", cartReponse);
			model.addAttribute("idCarttt", id);
			model.addAttribute("idCart", id);
			List<Category> listCategory = categoryService.getAllCategory();
			model.addAttribute("listCategory", listCategory);
			return "detailsCart";
		}
	}

	@GetMapping("/order/{idCart}")
	public String order(@PathVariable("idCart") int id, Model model) {
		Cart cart = cartService.getById(id);
		if (cart == null) {
			return "redirect:/";
		} else {			
			List<DetailCart> list = cart.getDetailCarts();
			List<CartResponse> cartReponse = new ArrayList<>();
			int tongtien = 0;
			for (DetailCart d : list) {
				int number = d.getQuanlity();

				Book book = bookService.getBookById(d.getBook().getId());
				CartResponse c = new CartResponse(id, book.getId(), book.getName(), book.getPathImages(),
						book.getPriceSell(), number);
				cartReponse.add(c);
				tongtien += number * book.getPriceSell();
			}
			User user = userService.findById(id);

			model.addAttribute("list", cartReponse);
			model.addAttribute("tongtien", tongtien);
			model.addAttribute("khachhang", user.getFullName());
			model.addAttribute("phonee", user.getPhone());
			model.addAttribute("idCarttt", id);
			model.addAttribute("idCart", id);
			List<Category> listCategory = categoryService.getAllCategory();
			model.addAttribute("listCategory", listCategory);
			return "order";
		}

	}

	@GetMapping(value = {"", "/", "/index", "/home"})
	public String root(Model model, Principal prin) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		int idCart = 0;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		if (!username.equals("anonymousUser")) {
			User user = userService.findByUsername(username);
			Cart cart = new Cart();
			if (cartService.getById(user.getId()) == null) {
				cart.setUser(user);
				cartService.save(cart);
			}
			idCart = user.getId();

			List<Category> listCategory = categoryService.getAllCategory();
			List<Book> listBook = bookService.getBooksByStatus("activated");
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("idCart", idCart);
			model.addAttribute("numberCategory", listCategory);
			model.addAttribute("listBook", listBook);
			return "index";
		} else {
			List<Category> listCategory = categoryService.getAllCategory();
			List<Book> listBook = bookService.getBooksByStatus("activated");
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("countCart", 0);
			model.addAttribute("idCart", 0);
			model.addAttribute("numberCategory", listCategory);
			model.addAttribute("listBook", listBook);
			return "index";
		}
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	@GetMapping("/403")
    public String accessDenied() {
        return "403";
    }

	@GetMapping("/book/{id}")
	public String detailsBook(@PathVariable("id") Integer id, Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		int idCart = 0;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		if (!username.equals("anonymousUser")) {
			User user = userService.findByUsername(username);
			Cart cart = new Cart();

			if (cartService.getById(user.getId()) == null) {
				cart.setUser(user);
				cartService.save(cart);

			}
			idCart = user.getId();
		} else {
			idCart = userService.findByUsername(username).getId();
		}

		Book b = bookService.getBookById(id);
		if (b == null) {
			return "redirect:/";
		}
		ArrayList<Author> listAuthor = new ArrayList<>(b.getAuthors());
		String authors = "";
		for (Author a : listAuthor) {
			
			authors += a.getName() + "\t";
		}
		model.addAttribute("idCart", idCart);
		model.addAttribute("book", b);
		model.addAttribute("author", authors);
		List<Category> listCategory = categoryService.getAllCategory();
		model.addAttribute("listCategory", listCategory);
		return "detailsBook";
	}

	@GetMapping("/category/{id}")
	public String category(@PathVariable("id") Integer id, Model model) {
		if (categoryService.getCategoryById(id) == null)
			return "redirect:/";
		else {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = "";
			int idCart = 0;
			if (principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();
			} else {
				username = principal.toString();
			}
			if (!username.equals("anonymousUser")) {
				User user = userService.findByUsername(username);
				idCart =  user.getId();
			}
			model.addAttribute("idCart", idCart);
			
			//ArrayList<Book> set = new ArrayList<>(categoryService.getCategoryById(id).getBooks());
			List<Book> list = new ArrayList<Book>();
			list = categoryService.getCategoryById(id).getBooks();
			
			List<Book> set = new ArrayList<Book>();
			for (Book b : list) {
				if(b.getStatus().equalsIgnoreCase("activated")) {
					set.add(b);
				}
			}
			
			model.addAttribute("listBook", set);
			model.addAttribute("categoryName", categoryService.getCategoryById(id).getTitle().toUpperCase());
			List<Category> listCategory = categoryService.getAllCategory();
			model.addAttribute("listCategory", listCategory);
			return "category";
		}
	}

	@GetMapping("/addToCart")
	@ResponseBody
	public List<CartResponse> addItemToCart(@RequestParam("cartId") int cartId, @RequestParam("bookId") int bookId,
			@RequestParam("quantity") int quantity) {

		DetailCart details = new DetailCart();
		details.setQuanlity(quantity);

		Cart cart = cartService.getById(cartId);
		Book b = bookService.getBookById(bookId);

		DetailCartId id = new DetailCartId(cart.getId(), b.getId());
		details.setId(id);
		details.setBook(b);
		details.setCart(cart);

		for (DetailCart i : cart.getDetailCarts()) {
			if (i.getBook().getId() == details.getBook().getId()) {
				i.setQuanlity(quantity + i.getQuanlity());
				cartService.save(cart);

				List<DetailCart> list = cart.getDetailCarts();
				List<CartResponse> cartReponse = new ArrayList<>();
				for (DetailCart d : list) {
					int number = d.getQuanlity();
					Book book = bookService.getBookById(d.getBook().getId());
					CartResponse c = new CartResponse(cartId, book.getId(), book.getName(), book.getPathImages(),
							book.getPriceSell(), number);
					cartReponse.add(c);
				}

				return cartReponse;

			}
		}
		cart.addDetailCart(details);
		cartService.save(cart);
		List<DetailCart> list = new ArrayList<>();
		list = cart.getDetailCarts();
		List<CartResponse> cartReponse = new ArrayList<>();
		if (list.size() != 0) {

			for (DetailCart d : list) {
				int number = d.getQuanlity();
				Book book = bookService.getBookById(d.getBook().getId());
				CartResponse c = new CartResponse(cartId, book.getId(), book.getName(), book.getPathImages(),
						book.getPriceSell(), number);
				cartReponse.add(c);
			}

		}
		return cartReponse;
	}

	@GetMapping("/numOfCart")
	@ResponseBody
	public List<CartResponse> getNumOfCart() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		int idCart = 0;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		if (!username.equals("anonymousUser")) {
			User user = userService.findByUsername(username);
			
			Cart cartt = cartService.getById(user.getId());
			List<DetailCart> list = new ArrayList<>();
			list = cartt.getDetailCarts();
			List<CartResponse> cartReponse = new ArrayList<>();
			if (list.size() != 0) {
				
				for (DetailCart d : list) {
					int number = d.getQuanlity();
					Book book = bookService.getBookById(d.getBook().getId());
					CartResponse c = new CartResponse(user.getId(), book.getId(), book.getName(), book.getPathImages(),
							book.getPriceSell(), number);
					cartReponse.add(c);
				}
			}
			return cartReponse;
		} else {
			List<CartResponse> cartReponse = new ArrayList<>();
			return cartReponse;
		}
	
	}

	@GetMapping("/changeQuantity")
	@ResponseBody
	public List<CartResponse> changeQuantity(@RequestParam("cartId") int cartId, @RequestParam("bookId") int bookId,
			@RequestParam("quantity") int quantity) {
		DetailCart details = new DetailCart();
		details.setQuanlity(quantity);

		Cart cart = cartService.getById(cartId);
		Book b = bookService.getBookById(bookId);

		DetailCartId id = new DetailCartId(cart.getId(), b.getId());
		details.setId(id);
		details.setBook(b);
		details.setCart(cart);
		cart.addDetailCart(details);
		cartService.save(cart);

		List<DetailCart> list = cartService.getById(cart.getId()).getDetailCarts();
		List<CartResponse> cartReponse = new ArrayList<CartResponse>();
		for (int i = 0; i < list.size(); i++) {
			CartResponse c = new CartResponse();
			c.setIdCart(cartId);
			c.setIdBook(list.get(i).getBook().getId());
			c.setImages(list.get(i).getBook().getPathImages());
			c.setName(list.get(i).getBook().getName());
			c.setNumber(list.get(i).getQuanlity());
			c.setPriceSell(list.get(i).getBook().getPriceSell());
			cartReponse.add(c);
		}

		return cartReponse;
	}

	@GetMapping("/deleteBookInCart")
	@ResponseBody
	public List<CartResponse> deleteBookInCart(@RequestParam("cartId") int cartId, @RequestParam("bookId") int bookId) {
		Cart cart = cartService.getById(cartId);
		Book b = bookService.getBookById(bookId);
		//List<DetailCart> listDetailCart = cart.getDetailCarts();
		List<DetailCart> listDetailCart = new ArrayList<DetailCart>();  //Edit 16/11
		
		System.out.println("size of listDetailCart :::: " + listDetailCart.size());
		listDetailCart =  cart.getDetailCarts();
		
		List<CartResponse> cartReponse = new ArrayList<CartResponse>();

		if (listDetailCart.size() > 0) {
			for (DetailCart i : listDetailCart) {
				if (i.getBook().getId() == b.getId()) {
					cart.removeDetailCart(i);
					cartService.save(cart);
					break;

				}

			}
			//List<DetailCart> list = cartService.getById(cartId).getDetailCarts();
			List<DetailCart> list = new ArrayList<DetailCart>();
			list = cartService.getById(cartId).getDetailCarts();
			
			if (list.size() > 0) {
				for (DetailCart d : list) {
					int number = d.getQuanlity();
					Book book = bookService.getBookById(d.getBook().getId());
					CartResponse c = new CartResponse(cartId, book.getId(), book.getName(), book.getPathImages(),
							book.getPriceSell(), number);
					cartReponse.add(c);
				}
			}
			
			
		}
		return cartReponse;
	}

	@GetMapping("/book/search")
	public String search(@RequestParam("name") String name, Model model) {
		if (StringUtils.isEmpty(name)) {
			return "redirect:/";
		}
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		int idCart = 0;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		if (!username.equals("anonymousUser")) {
			User user = userService.findByUsername(username);
			idCart =  user.getId();
		}
		model.addAttribute("idCart", idCart);
		List<Category> listCategory = categoryService.getAllCategory();
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("listBook", bookService.searchBook(name));
		return "category";

	}

	@GetMapping("/dathang")
	@ResponseBody
	public String orderr(@RequestParam("address") String address, @RequestParam("idCart") int id, @RequestParam("phone") String phone) { 
		Cart cart = cartService.getById(id);
		User user = userService.findById(id);
		
		//String phone = user.getPhone();

		List<DetailCart> list = new ArrayList<DetailCart>();
		list = cart.getDetailCarts();
		Order order = new Order();
		order.setStatusOrder("unresolved");
		order.setUser(user);
		order.setDateOrder(LocalDateTime.now());
		order.setAddressDetails(address);
		order.setPhone(phone);
		List<DetailOrder> orderDetails = new ArrayList<DetailOrder>();
		order.setDetailOrders(orderDetails);
		int total = 0;
		List<DetailOrder> listOrderDetails = new ArrayList<DetailOrder>();
		for (DetailCart i : list) {
			total += i.getQuanlity() * i.getBook().getPriceSell();
			DetailOrder details = new DetailOrder();
			DetailOrderId idDetails = new DetailOrderId(order.getId(), i.getBook().getId());
			details.setDetailOrderId(idDetails);
			details.setBook(i.getBook());
			details.setOrder(order);

			// decrease quantity -- of Book
			Book book = bookService.getBookById(i.getBook().getId());
			int sl = book.getQuantity();
			book.setQuantity(sl - i.getQuanlity());
			bookService.saveBook(book);

			details.setQuantity(i.getQuanlity());
			details.setUnitPrice(i.getBook().getPriceSell());
			listOrderDetails.add(details);
		}
		order.setTotalPrice(total);
		order.setDetailOrders(listOrderDetails);
		orderService.saveOrder(order);

		cart.setDetailCarts();
		cartService.save(cart);
		return "ok";

	}
	@GetMapping("/managerOrder/{orderID}")
	public String managerOrder(@PathVariable("orderID") Integer id, Model model) {
		
		if (id == 0) {
			return "redirect:/";
		}
		if (userService.findById(id) == null) {
			return "redirect:/";
		}
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		int idCart = 0;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		if (!username.equals("anonymousUser")) {
			User user = userService.findByUsername(username);
			idCart =  user.getId();
		}
		model.addAttribute("idCart", idCart);
		
		List<Category> listCategory = categoryService.getAllCategory();
		model.addAttribute("listCategory", listCategory);
		
		User user = userService.findById(id);
		List<Order> listOrder = new ArrayList<Order>();
		listOrder = user.getOrder();
		
		model.addAttribute("listOrder", listOrder);
		return "managerOrder";

	}
	
	@GetMapping("/order/viewDetails/{id}")
	public String viewOrder(@PathVariable("id") int id, Model model) {
		
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		int idCart = 0;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		if (!username.equals("anonymousUser")) {
			User user = userService.findByUsername(username);
			idCart =  user.getId();
		}
		model.addAttribute("idCart", idCart);
		
		
		Order order = orderService.getOrderById(id);
		List<DetailOrder> listDetailOrders = new ArrayList<DetailOrder>();
		listDetailOrders = order.getDetailOrders();
		
		if (order != null) {
			model.addAttribute("list", listDetailOrders);
			model.addAttribute("idOrder", order.getId());
			
		}
		model.addAttribute("totalPrice", order.getTotalPrice());
		model.addAttribute("customer", username);
		model.addAttribute("status", order.getStatusOrder());
		model.addAttribute("phone", order.getPhone());
		model.addAttribute("address", order.getAddressDetails());
		List<Category> listCategory = categoryService.getAllCategory();
		model.addAttribute("listCategory", listCategory);
		return "detailsOrder";
		
	}
	
	@GetMapping("/order/edit/{id}")
	public String editOrder(@PathVariable("id") int id, Model model) {
		
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		int idCart = 0;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		if (!username.equals("anonymousUser")) {
			User user = userService.findByUsername(username);
			idCart =  user.getId();
		}
		model.addAttribute("idCart", idCart);
		
		
		Order order = orderService.getOrderById(id);
		List<DetailOrder> listDetailOrders = new ArrayList<DetailOrder>();
		listDetailOrders = order.getDetailOrders();
		
		if (order != null) {
			model.addAttribute("list", listDetailOrders);
			model.addAttribute("idOrder", order.getId());
			
		}
		model.addAttribute("totalPrice", order.getTotalPrice());
		model.addAttribute("customer", username);
		model.addAttribute("status", order.getStatusOrder());
		model.addAttribute("phone", order.getPhone());
		model.addAttribute("address", order.getAddressDetails());
		List<Category> listCategory = categoryService.getAllCategory();
		model.addAttribute("listCategory", listCategory);
		return "detailsOrder";
		
	}
	
	@GetMapping("/order/remove/{id}")
	public String removeOrder(@PathVariable("id") int id, Model model) {
		Order order = orderService.getOrderById(id);
		
		List<DetailOrder> listDetail = new ArrayList<DetailOrder>();
		listDetail = order.getDetailOrders();
		
		if(listDetail.size() > 0) {
			for(DetailOrder d : listDetail) {
				int quantityCurrent = 0;
				Book book = bookService.getBookById(d.getBook().getId());
				quantityCurrent = book.getQuantity();
				book.setQuantity(quantityCurrent + d.getQuantity());
				bookService.saveBook(book);
			}
		}		
		orderService.delete(id);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		int userId = 0;
		int idCart = 0;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		if (!username.equals("anonymousUser")) {
			User user = userService.findByUsername(username);
			userId =  user.getId();
			
		}
		
		return "redirect:/managerOrder/" + userId;
	}
	
	@GetMapping("update/order")
	@ResponseBody
	public Order updateOrder(@RequestParam("address") String address, @RequestParam("phone") String phone, @RequestParam("idOrder") int id) {
		Order order =  orderService.getOrderById(id);
		if(order != null) {
			order.setAddressDetails(address);
			order.setPhone(phone);
			orderService.saveOrder(order);
		}
		return order;
		
	}
}
