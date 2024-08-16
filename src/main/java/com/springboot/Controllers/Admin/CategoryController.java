package com.springboot.Controllers.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.Entity.Category;
import com.springboot.Services.CategoryService;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	// lay tat ca cac danh muc
	@GetMapping("")
	public String categoryIndex(Model model, @Param("keyword") String keyword,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		Page<Category> list = this.categoryService.getAll(pageNo);
		if(keyword != null) {
			list = categoryService.searchCategory(keyword, pageNo);
			model.addAttribute("keyword", keyword);
		}
		model.addAttribute("totalPage", list.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("list", list);
		return "admin/category/index";
	}
	// them moi danh muc
	@GetMapping("/add")
	public String addCategory(Model model) {
		Category category = new Category();
		category.setCategoryStatus(true);
		model.addAttribute("category", category);
		return "admin/category/add";
	}
	
	@PostMapping("/add")
	public String addCategory(@ModelAttribute("category") Category category) {
		categoryService.saveCategory(category);
		return "redirect:/admin/category";
	}
	
	// update danh muc
	@GetMapping("/edit/{id}")
	public String editCategory(Model model, @PathVariable("id") int id) {
		Category category = categoryService.category(id);
		model.addAttribute("category", category);
		return "admin/category/edit";
	}
	
	@PostMapping("/edit")
	public String updateCategory(@ModelAttribute("category") Category category) {
		categoryService.saveCategory(category);
		return "redirect:/admin/category";
	}
	
	// xoa danh muc
	@GetMapping("/delete/{id}")
	public String deleteCategory(@PathVariable("id") Integer id) {
		categoryService.deleteCategory(id);
		return "redirect:/admin/category";
	}
	
}
