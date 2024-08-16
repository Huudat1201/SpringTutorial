package com.springboot.Controllers.Admin;

import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import com.springboot.Entity.Category;
import com.springboot.Entity.Product;
import com.springboot.Services.CategoryService;
import com.springboot.Services.ProductService;
import com.springboot.Services.StorageService;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

	@Autowired
	private StorageService storageService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;
	
	// lay tat ca cac danh muc
	@GetMapping("")
	public String index(Model model, @Param("keyword") String keyword,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		Page<Product> list = this.productService.getAll(pageNo);
		if(keyword != null) {
			list = productService.searchProduct(keyword, pageNo);
			model.addAttribute("keyword", keyword);
		}
		model.addAttribute("totalPage", list.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("list", list);
		return "admin/product/index";
	}

	// them moi san pham
	@GetMapping("/add")
	public String add(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		List<Category> listCate = this.categoryService.listCategory();
		model.addAttribute("listCate", listCate);
		return "admin/product/add";
	}

	@PostMapping("/add")
	public String add(@ModelAttribute("product") Product product, @RequestParam("fileImage") MultipartFile file) {
		this.storageService.store(file);
		String fileName = file.getOriginalFilename();
		product.setImage(fileName);
		productService.saveProduct(product);
		return "redirect:/admin/product";
	}

	// chinh sua san pham
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		Product product = this.productService.product(id);
		model.addAttribute("product", product);
		List<Category> listCate = this.categoryService.listCategory();
		model.addAttribute("listCate", listCate);
		return "admin/product/edit";
	}

	@PostMapping("/edit")
	public String update(@ModelAttribute("product") Product product, @RequestParam("fileImage") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		boolean isEmpty = fileName == null || fileName.trim().length() == 0;
		if (!isEmpty) {
			this.storageService.store(file);
			product.setImage(fileName);
		}
		this.productService.saveProduct(product);
		return "redirect:/admin/product";
	}

}
