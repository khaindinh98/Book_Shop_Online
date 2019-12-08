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
import com.example.demo.form.CategoryForm;
import com.example.demo.model.Author;
import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;

@Controller
@RequestMapping(value = "/admin/categories")
public class CategoriesController {
	
	@Autowired
    private CategoryService categoryService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String manange(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		return "admin/ManageCategories";
	}
	
	@RequestMapping(value = "/addcategory", method = RequestMethod.GET)
	public String addCategory(Model model) {
		model.addAttribute("title", "Add New Category");
		model.addAttribute("category", new CategoryForm());
		return "admin/Category";
	}
	
	@RequestMapping(value = "/editcategory/{id}", method = RequestMethod.GET)
	public String editCategory(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("title", "Edit New Category");
		model.addAttribute("category", new CategoryForm(categoryService.getCategoryById(id)));
		return "admin/Category";
	}
	
}
