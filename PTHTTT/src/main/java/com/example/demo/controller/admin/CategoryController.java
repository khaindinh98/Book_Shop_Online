package com.example.demo.controller.admin;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.form.CategoryForm;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;

@Controller
@RequestMapping(value = "/admin/category")
public class CategoryController {
	
	public static final String uploadingDir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\category\\";

	@Autowired
    private CategoryService categoryService;
	
	@RequestMapping(value = {"/","/**"}, method = RequestMethod.GET)
	public String outline(Model model) {
		return "redirect:/admin/categories/";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String Category(CategoryForm categoryForm, RedirectAttributes redirect) {
		Category category;
		if (categoryForm.getId() == -1) {
			category = new Category();
			category.setTitle(categoryForm.getTitle());
			category.setDescription(categoryForm.getDescription());
			category.setStatus("activated");
			File file = new File(uploadingDir + categoryForm.getFileData().getOriginalFilename());
			if(!categoryForm.getFileData().isEmpty()) {
				System.out.println("Test update product");
				try {
					categoryForm.getFileData().transferTo(file);
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			category.setImg("/images/category/"+categoryForm.getFileData().getOriginalFilename());
			categoryService.saveCategory(category);
		}
		else {
			category = categoryService.getCategoryById(categoryForm.getId());
			category.setTitle(categoryForm.getTitle());
			category.setDescription(categoryForm.getDescription());
			File oldFile = new File(uploadingDir + category.getImg());
			File newFile = new File(uploadingDir + categoryForm.getFileData().getOriginalFilename());
			if(!categoryForm.getFileData().isEmpty()) {
				System.out.println("Test update product");
				try {
					if(oldFile.isFile())
						oldFile.delete();
					categoryForm.getFileData().transferTo(newFile);
					category.setImg("/images/category/"+categoryForm.getFileData().getOriginalFilename());
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			categoryService.saveCategory(category);
		}
		return "redirect:/admin/categories/";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteCategory(@PathVariable Integer id, RedirectAttributes redirect) {
		categoryService.delete(id);
		return "redirect:/admin/categories/";
	}
	
	
	@RequestMapping(value = "/activated/{id}", method = RequestMethod.GET)
	public String activatedAuthor(@PathVariable Integer id, RedirectAttributes redirect) {
		Category category = categoryService.getCategoryById(id);
		category.setStatus("activated");
		categoryService.saveCategory(category);
		return "redirect:/admin/categories/";
	}
	
	@RequestMapping(value = "/unactivated/{id}", method = RequestMethod.GET)
	public String unactivatedAuthor(@PathVariable Integer id, RedirectAttributes redirect) {
		System.out.println("vo ne");
		Category category = categoryService.getCategoryById(id);
		category.setStatus("unactivated");
		categoryService.saveCategory(category);
		return "redirect:/admin/categories/";
	}
}
