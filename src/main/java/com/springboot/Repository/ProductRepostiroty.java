package com.springboot.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.Entity.Product;

public interface ProductRepostiroty extends JpaRepository<Product, Integer>{
	@Query("select c from Product c where c.productName like %?1%")
	List<Product> searchCategory(String keyword);
}
