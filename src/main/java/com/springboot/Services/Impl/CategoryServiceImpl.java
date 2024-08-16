package com.springboot.Services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.Entity.Category;
import com.springboot.Repository.CategoryRepository;
import com.springboot.Services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> listCategory() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	@Override
	public Category category(int id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id).get();
	}
	

	@Override
	public Category saveCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryRepository.save(category);
	}
	

	@Override
	public Category updateCategory(Category category, int id) {
		// TODO Auto-generated method stub
		Category old = categoryRepository.findById(id).get();
		old.setCategoryName(category.getCategoryName());
		old.setCategoryStatus(category.getCategoryStatus());
		return categoryRepository.save(old);
	}

	@Override
	public void deleteCategory(int id) {
		// TODO Auto-generated method stub
		categoryRepository.deleteById(id);
	}

	@Override
	public List<Category> searchCategory(String keyword) {
		// TODO Auto-generated method stub
		return categoryRepository.searchCategory(keyword);
	}

	@Override
	public Page<Category> getAll(Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, 2);
		return this.categoryRepository.findAll(pageable);
	}

	@Override
	public Page<Category> searchCategory(String keyword, Integer pageNo) {
		List<Category> list = this.searchCategory(keyword);
		Pageable pageable = PageRequest.of(pageNo - 1, 2);
		Integer start = (int) pageable.getOffset();
		Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size() : pageable.getOffset() + pageable.getPageSize());
		list = list.subList(start, end);
		return new PageImpl<Category>(list, pageable, this.searchCategory(keyword).size());
	}
	
}
