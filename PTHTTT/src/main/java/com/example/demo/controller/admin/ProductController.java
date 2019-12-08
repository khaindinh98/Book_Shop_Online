package com.example.demo.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.form.BookForm;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.response.BookRest;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;

@Controller
@RequestMapping(value = "/admin/product")
public class ProductController {

	public static final String uploadingDir = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\static\\";

	@Autowired
	private BookService bookService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AuthorService authorService;

	@RequestMapping(value = { "/", "/**" }, method = RequestMethod.GET)
	public String outline(Model model) {
//		model.addAttribute("product", new Book());
		return "redirect:/admin/products/";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addProduct(BookForm bookForm, RedirectAttributes redirect) {
		Book book;
		if (bookForm.getId() == -1){
			book = new Book();
			book.setName(bookForm.getName());
			book.setDescription(bookForm.getDescription());
			book.setPriceBuy(bookForm.getPriceBuy());
			book.setPriceSell(bookForm.getPriceSell());
			book.setSize(bookForm.getSize());
			book.setPages(bookForm.getPages());
			book.setQuantity(bookForm.getQuantity());
			book.setPathImages("/images/"+bookForm.getFileData().getOriginalFilename());
			if (bookForm.getCategory() == -1)
				book.setCategory(null);
			else
				book.setCategory(categoryService.getCategoryById(bookForm.getCategory()));
			
			List<Author> authors = new ArrayList<Author>();
			Integer[] intAuthors = bookForm.getAuthors();
			if (intAuthors!=null && intAuthors.length !=0) {
				for (Integer id : intAuthors) {
					authors.add(authorService.findById(id));
				}
			}
			book.setAuthors(authors);
			
			File file = new File(uploadingDir + "images\\" + bookForm.getFileData().getOriginalFilename());
			try {
				if (!bookForm.getFileData().isEmpty()) {
					bookForm.getFileData().transferTo(file);
				}
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				bookService.saveBook(book);
			}

		}
		return "redirect:/admin/products/";
	}

	private boolean containsIntAuthors(int authorId, Integer[] intAuthors) {
		for(Integer intAuthor : intAuthors)
			if(intAuthor.equals(authorId))
				return true;
		return false;
	}
	
	private boolean containsAuthors(int authorId, List<Author> authors) {
		for(Author author : authors)
			if(author.getId() == authorId)
				return true;
		return false;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editProduct(BookForm bookForm, RedirectAttributes redirect) {
		Book book;
		if (bookForm.getId() != -1) {
			book = bookService.getBookById(bookForm.getId());
			book.setName(bookForm.getName());
			book.setDescription(bookForm.getDescription());
			book.setPriceBuy(bookForm.getPriceBuy());
			book.setPriceSell(bookForm.getPriceSell());
			book.setSize(bookForm.getSize());
			book.setPages(bookForm.getPages());
			book.setQuantity(bookForm.getQuantity());
			
			if (bookForm.getCategory() == -1)
				book.setCategory(null);
			else
				book.setCategory(categoryService.getCategoryById(bookForm.getCategory()));
			List<Author> authorBooks = book.getAuthors();
			Integer[] intAuthors = bookForm.getAuthors();
			if (intAuthors!=null && authorBooks.size() > intAuthors.length) {
				List<Author> removeAuthors = new ArrayList<Author>();
				System.out.println("Test update product 1");
				for (Author author : authorBooks) {
					if(!containsIntAuthors(author.getId(), intAuthors)) {
						removeAuthors.add(author);
					}
				}
				authorBooks.removeAll(removeAuthors);
			} else if (intAuthors!=null && authorBooks.size() < intAuthors.length){
				List<Author> addAuthors = new ArrayList<Author>();
				System.out.println("Test update product 2");
				for (Integer intAuthor : intAuthors) {
					if(!containsAuthors(intAuthor, authorBooks)){
						addAuthors.add(authorService.findById(intAuthor));
					}
				}
				authorBooks.addAll(addAuthors);
			}
			
			File oldFile = new File(uploadingDir + book.getPathImages());
			File newFile = new File(uploadingDir + "images\\" + bookForm.getFileData().getOriginalFilename());
			try {
				if (!bookForm.getFileData().isEmpty()) {
					System.out.println("Test update product");

					if (oldFile.isFile())
						oldFile.delete();
					bookForm.getFileData().transferTo(newFile);
					book.setPathImages("/images/"+bookForm.getFileData().getOriginalFilename());

				}
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				bookService.saveBook(book);
			}
		}
		return "redirect:/admin/products/";
	}
	
	@RequestMapping(value = "/activated/{id}", method = RequestMethod.GET)
	public String activatedProduct(@PathVariable Integer id, RedirectAttributes redirect) {
		Book book = bookService.getBookById(id);
		book.setStatus("activated");
		bookService.saveBook(book);
		return "redirect:/admin/products/";
	}
	
	@RequestMapping(value = "/unactivated/{id}", method = RequestMethod.GET)
	public String unactivatedProduct(@PathVariable Integer id, RedirectAttributes redirect) {
		Book book = bookService.getBookById(id);
		book.setStatus("unactivated");
		bookService.saveBook(book);
		return "redirect:/admin/products/";
	}
	
	@RequestMapping(value = "/getProductById/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BookRest getProduct(@PathVariable Integer id, RedirectAttributes redirect) {
		return new BookRest(bookService.getBookById(id));
	}
}
