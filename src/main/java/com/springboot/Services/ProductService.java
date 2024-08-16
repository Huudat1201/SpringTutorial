package com.springboot.Services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.springboot.Entity.Product;

public interface ProductService {
	// Lay ra tat ca cac san pham
	List<Product> listProduct();

	// Lay 1 san pham theo ID
	Product product(int id);

	// Luu san pham moi
	Product saveProduct(Product product);

	// Cap nhat san pham
	Product updateProduct(Product product, int id);

	// Xoa 1 san pham
	void deleteProduct(int id);

	// Tim kiem san pham theo tu khoa
	List<Product> searchProduct(String keyword);

	// Phan trang san pham
	Page<Product> getAll(Integer pageNo);

	// Phan trang ket hop tim kiem san pham
	Page<Product> searchProduct(String keyword, Integer pageNo);

}
