package com.springboot.Services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.springboot.Entity.Category;

public interface CategoryService {
	// lay ra tat ca cac danh muc
	List<Category> listCategory();
	// lay 1 danh muc
	Category category(int id);
	// luu danh muc xuong
	Category saveCategory(Category category);
	// chinh sua danh muc
	Category updateCategory(Category category, int id);
	// xoa 1 danh muc
	void deleteCategory(int id);
	// tim kiem san pham
	List<Category> searchCategory(String keyword);
	// phan trang danh muc
	Page<Category> getAll(Integer pageNo);
	// phan trang ket hop tim kiem
	Page<Category> searchCategory(String keyword, Integer pageNo);
}
