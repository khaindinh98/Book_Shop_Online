package com.example.demo.controller.admin;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.form.DetailInvoiceForm;
import com.example.demo.form.InvoiceForm;
import com.example.demo.model.Book;
import com.example.demo.model.DetailInvoice;
import com.example.demo.model.Invoice;
import com.example.demo.model.User;
import com.example.demo.service.BookService;
import com.example.demo.service.InvoiceService;
import com.example.demo.service.UserService;
import org.json.simple.JSONArray; 

@Controller
@RequestMapping(value = "/admin/invoices")
public class InvoicesController {
	
	@Autowired
    private InvoiceService invoiceService;
	
	@Autowired
    private BookService bookService;
	
	@Autowired
    private UserService userService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String manange(Model model, HttpServletResponse response) {
		model.addAttribute("invoices", InvoiceForm.convertToInvoiceForms(invoiceService.getAllInvoices()));
		Cookie cookie = new Cookie("detailInvoices", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return "admin/ManageInvoices";
	}
	
	@RequestMapping(value = "/addinvoice", method = RequestMethod.GET)
	public String addInvoiceGET(Model model, HttpServletResponse response) {
		model.addAttribute("title", "Add Invoice");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		User user = userService.findByUsername(username);
		InvoiceForm invoiceForm = new InvoiceForm();	
		invoiceForm.setUsername(username);
		invoiceForm.setFullName(user.getFullName());
		model.addAttribute("invoiceForm", invoiceForm);
		model.addAttribute("url", "/admin/invoices/addinvoice/");
		JSONArray detailInvoices = new JSONArray(); 
		model.addAttribute("books", bookService.getBooksBesideIds("", detailInvoices));
		Cookie cookie = new Cookie("detailInvoices", Base64.getEncoder().encodeToString(("[]").getBytes()));
		cookie.setMaxAge(1 * 24 * 60 * 60);
		response.addCookie(cookie);
		return "admin/Invoice";
	}
	
	
	
	@RequestMapping(value = "/editinvoice/{id}", method = RequestMethod.GET)
	public String editInvoiceGET(@PathVariable("id") Integer id, Model model, HttpServletResponse response) {
		model.addAttribute("title", "Edit Invoice");
		Invoice invoice = invoiceService.getInvoiceById(id);
		
		InvoiceForm invoiceForm = new InvoiceForm(invoice);
		model.addAttribute("invoiceForm", invoiceForm);
		model.addAttribute("url", "/admin/invoices/editinvoice/"+id);
		JSONArray detailInvoices = new JSONArray(); 
		for(DetailInvoice detailInvoice : invoice.getDetailInvoices())
			detailInvoices.add(detailInvoice.getBook().getId());
		model.addAttribute("books", bookService.getBooksBesideIds("", detailInvoices));
		System.out.println(JSONArray.toJSONString(detailInvoices));
		Cookie cookie = new Cookie("detailInvoices", Base64.getEncoder().encodeToString((JSONArray.toJSONString(detailInvoices)).getBytes()));
		cookie.setMaxAge(1 * 24 * 60 * 60);
		response.addCookie(cookie);
		return "admin/Invoice";
	}
	
	@RequestMapping(value = "/removeinvoice/{invoiceId}", method = RequestMethod.GET)
	public String removeInvoice(@PathVariable Integer invoiceId, RedirectAttributes redirect) {
		//adjust when delete invoice
		Invoice invoice = invoiceService.getInvoiceById(invoiceId);
		for(DetailInvoice detailInvoice: invoice.getDetailInvoices()) {
			Book book = detailInvoice.getBook();
			book.setQuantity(book.getQuantity() - detailInvoice.getQuantity());
			bookService.saveBook(book);
		}
		invoiceService.delete(invoiceId);
		return "redirect:/admin/invoices/";
	}
	
	@RequestMapping(value = "/addinvoice", method = RequestMethod.POST)
	public String addInvoicePOST(InvoiceForm invoiceForm, Model model, RedirectAttributes redirect, HttpServletResponse response) {
		Invoice invoice = new Invoice();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		User user = userService.findByUsername(username);
		invoice.setUser(user);
		invoiceService.saveInvoice(invoice);
		for(DetailInvoiceForm detailInvoiceForm:invoiceForm.getDetailInvoiceForms()) {
			Book book = bookService.getBookById(detailInvoiceForm.getBookId());
			invoice.addBook(book, detailInvoiceForm.getQuantity(), detailInvoiceForm.getPriceBuy());
			book.setQuantity(book.getQuantity() + detailInvoiceForm.getQuantity());
			bookService.saveBook(book);
		}
		invoice.setDateModified(LocalDateTime.now());
		invoiceService.saveInvoice(invoice);
		return "redirect:/admin/invoices/";
	}
	
	@RequestMapping(value = "/editinvoice/{id}", method = RequestMethod.POST)
	public String editInvoicePOST(InvoiceForm invoiceForm, @PathVariable("id") Integer id, Model model, RedirectAttributes redirect, HttpServletResponse response) {
		Invoice invoice = invoiceService.getInvoiceById(invoiceForm.getId());
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		User user = userService.findByUsername(username);
		invoice.setUser(user);
		invoice.setTotalPrice(0);
		invoice.setDateModified(LocalDateTime.now());
		saveChange(invoice, invoiceForm);
		return "redirect:/admin/invoices/";
	}
	
	private boolean selfContainsRemove(int bookId, List<DetailInvoiceForm> detailInvoiceForms) {
		for(DetailInvoiceForm detailInvoiceForm : detailInvoiceForms) {
			if(bookId == detailInvoiceForm.getBookId())
				return true;
		}
		return false;
	}
	
	private boolean selfContainsAdd(int bookId, List<DetailInvoice> detailInvoices) {
		for(DetailInvoice detailInvoice : detailInvoices) {
			if(bookId == detailInvoice.getBook().getId())
				return true;
		}
		return false;
	}
	
	private DetailInvoiceForm getDetailInvoiceForm(int bookId, List<DetailInvoiceForm> detailInvoiceForms) {
		for(DetailInvoiceForm detailInvoiceForm : detailInvoiceForms) {
			if(bookId == detailInvoiceForm.getBookId())
				return detailInvoiceForm;
		}
		return null;
	}
	
	private void saveChange(Invoice invoice, InvoiceForm invoiceForm) {
		
		for(DetailInvoice detailInvoice: invoice.getDetailInvoices()) {
			Book book = bookService.getBookById(detailInvoice.getBook().getId());
			book.setQuantity(book.getQuantity() - detailInvoice.getQuantity());
			bookService.saveBook(book);			
		}
		invoice.getDetailInvoices().clear();
		for(DetailInvoiceForm detailInvoiceForm: invoiceForm.getDetailInvoiceForms()) {
			Book book = bookService.getBookById(detailInvoiceForm.getBookId());
			book.setQuantity(book.getQuantity() + detailInvoiceForm.getQuantity());
			bookService.saveBook(book);		
			invoice.addBook(book, detailInvoiceForm.getQuantity(), detailInvoiceForm.getPriceBuy());
		}
		
//		System.out.println(invoice.getDetailInvoices().size());
//		System.out.println(invoiceForm.getDetailInvoiceForms().size());
//		if(invoice.getDetailInvoices().size() > invoiceForm.getDetailInvoiceForms().size()) {
//			
//			List<DetailInvoice> removeDetailInvoices = new ArrayList<DetailInvoice>();
//			for(DetailInvoice detailInvoice : invoice.getDetailInvoices()) {
//				if(!selfContainsRemove(detailInvoice.getBook().getId(), invoiceForm.getDetailInvoiceForms())) {
//					removeDetailInvoices.add(detailInvoice);
//					Book book = detailInvoice.getBook();
//					book.setQuantity(book.getQuantity() - detailInvoice.getQuantity());
//					bookService.saveBook(book);
//				}
//				
//			}
//			for(DetailInvoice detailInvoice : removeDetailInvoices) {
//				invoice.removeBook(detailInvoice.getBook());
//			}
//			
//		} else if(invoice.getDetailInvoices().size() < invoiceForm.getDetailInvoiceForms().size()) {
//			
//			List<DetailInvoiceForm> addDetailInvoices = new ArrayList<DetailInvoiceForm>();
//			
//			for(DetailInvoiceForm detailInvoiceForm : invoiceForm.getDetailInvoiceForms()) {
//				if(!selfContainsAdd(detailInvoiceForm.getBookId(), invoice.getDetailInvoices())) {
//					
//					addDetailInvoices.add(detailInvoiceForm);
//					Book book = bookService.getBookById(detailInvoiceForm.getBookId());
//					book.setQuantity(book.getQuantity() + detailInvoiceForm.getQuantity());
//					bookService.saveBook(book);
//				}
//			}
//			
//			for(DetailInvoiceForm detailInvoiceForm : addDetailInvoices) {
//				invoice.addBook(bookService.getBookById(detailInvoiceForm.getBookId()), detailInvoiceForm.getQuantity(), detailInvoiceForm.getPriceBuy());
//			}
//		}
//
//		for(DetailInvoice detailInvoice : invoice.getDetailInvoices()) {
//			DetailInvoiceForm detailInvoiceForm = getDetailInvoiceForm(detailInvoice.getBook().getId(), invoiceForm.getDetailInvoiceForms());
//			if(detailInvoiceForm!=null) {
//				int difference =  detailInvoiceForm.getQuantity() - detailInvoice.getQuantity();
//				if(difference != 0) {
//					invoice.changeBookQuantity(detailInvoice.getBook(), difference);
//					
//					Book book = bookService.getBookById(detailInvoiceForm.getBookId());
//					book.setQuantity(book.getQuantity() + difference);
//					bookService.saveBook(book);
//				}
//			}
//		}
		invoiceService.saveInvoice(invoice);
	}
	

	
//	@RequestMapping(value = "/add/{invoiceId}/{bookId}/{quantity}", method = RequestMethod.GET)
//	@ResponseBody
//	public String testAddDetailInvoice(Model model, @PathVariable Integer invoiceId, @PathVariable Integer bookId, @PathVariable Integer quantity) {
//		Invoice invoice = invoiceService.getInvoiceById(invoiceId);
//		invoice.addBook(bookService.getBookById(bookId), 1, 10000);
//		invoiceService.saveInvoice(invoice);
//		return "Success";
//	}
	
//	@RequestMapping(value = "/remove/{invoiceId}/{bookId}", method = RequestMethod.GET)
//	@ResponseBody
//	public String testRemoveDetailInvoice(Model model, @PathVariable Integer invoiceId, @PathVariable Integer bookId) {
//		Invoice invoice = invoiceService.getInvoiceById(invoiceId);
//		Book book = bookService.getBookById(bookId);
//		invoice.removeBook(book);
//		System.out.println(invoice.getDetailInvoices().size());
//		invoiceService.saveInvoice(invoice);
//		return "Success";
//	}
	

	
//	@RequestMapping(value = "/changequantity/{invoiceId}/{bookId}/{difference}", method = RequestMethod.GET)
//	@ResponseBody
//	public String testChangeQuantity(Model model, @PathVariable Integer invoiceId, @PathVariable Integer bookId, @PathVariable Integer difference) {
//		Invoice invoice = invoiceService.getInvoiceById(invoiceId);
//		invoice.changeBookQuantity(bookService.getBookById(bookId), difference);
//		invoiceService.saveInvoice(invoice);
//		return "Success";
//	}
	

//	@RequestMapping(value = "/editcategory/{id}", method = RequestMethod.GET)
//	public String editCategory(@PathVariable("id") Integer id, Model model) {
//		model.addAttribute("title", "Edit New Category");
//		model.addAttribute("category", categoryService.getCategoryById(id));
//		return "admin/Category";
//	}
	
}
