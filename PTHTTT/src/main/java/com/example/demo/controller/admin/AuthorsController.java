package com.example.demo.controller.admin;

import java.sql.Date;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.model.Author;
import com.example.demo.service.AuthorService;

@Controller
@RequestMapping(value = "/admin/authors")
public class AuthorsController {
	
	@Autowired
    private AuthorService authorService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String manange(Model model) {
		model.addAttribute("authors", authorService.getAllAuthor());
		return "admin/ManageAuthors";
	}
	
	@RequestMapping(value = "/addauthor", method = RequestMethod.GET)
	public String addAuthor(Model model) {
		model.addAttribute("title", "Add New Author");
		Date now = new Date(System.currentTimeMillis());
		Author author = new Author();
		author.setBirthday(now);
		model.addAttribute("author", author);
		return "admin/Author";
	}
	
	@RequestMapping(value = "/editauthor/{id}", method = RequestMethod.GET)
	public String editAuthor(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("title", "Edit New Author");
		model.addAttribute("author", authorService.findById(id));
		return "admin/Author";
	}
	
}
