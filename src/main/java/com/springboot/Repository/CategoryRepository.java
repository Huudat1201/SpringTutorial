package com.springboot.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	@Query("select c from Category c where c.categoryName like %?1%")
	List<Category> searchCategory(String keyword);
}
