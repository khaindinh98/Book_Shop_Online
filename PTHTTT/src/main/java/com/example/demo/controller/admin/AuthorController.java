package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.service.AuthorService;

@Controller
@RequestMapping(value = "/admin/author")
public class AuthorController {
	
	@Autowired
    private AuthorService authorService;
	
	@RequestMapping(value = {"/","/**"}, method = RequestMethod.GET)
	public String outline(Model model) {
		return "redirect:/admin/authors/";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteAuthor(@PathVariable Integer id, RedirectAttributes redirect) {
		authorService.delete(id);
		return "redirect:/admin/authors/";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveAuthor(Author author, RedirectAttributes redirect) {
		author.setStatus("activated");
		authorService.save(author);
		return "redirect:/admin/authors/";
	}
	@RequestMapping(value = "/activated/{id}", method = RequestMethod.GET)
	public String activatedAuthor(@PathVariable Integer id, RedirectAttributes redirect) {
		Author author = authorService.findById(id);
		author.setStatus("activated");
		authorService.save(author);
		return "redirect:/admin/authors/";
	}
	
	@RequestMapping(value = "/unactivated/{id}", method = RequestMethod.GET)
	public String unactivatedAuthor(@PathVariable Integer id, RedirectAttributes redirect) {
		System.out.println("vo ne");
		Author author = authorService.findById(id);
		author.setStatus("unactivated");
		authorService.save(author);
		return "redirect:/admin/authors/";
	}
	
}
