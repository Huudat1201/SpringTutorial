package com.springboot.Services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.Entity.Product;
import com.springboot.Repository.ProductRepostiroty;
import com.springboot.Services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepostiroty productRepostiroty;
	
	@Override
	public List<Product> listProduct() {
		// TODO Auto-generated method stub
		return productRepostiroty.findAll();
	}

	@Override
	public Product product(int id) {
		// TODO Auto-generated method stub
		return productRepostiroty.findById(id).get();
	}

	@Override
	public Product saveProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepostiroty.save(product);
	}

	@Override
	public Product updateProduct(Product product, int id) {
		// TODO Auto-generated method stub
		Product old = productRepostiroty.findById(id).get();
		old.setCategory(product.getCategory());
		old.setDescription(product.getDescription());
		old.setImage(product.getImage());
		old.setPrice(product.getPrice());
		old.setProductName(product.getProductName());
		return productRepostiroty.save(old);
	}

	@Override
	public void deleteProduct(int id) {
		// TODO Auto-generated method stub
		productRepostiroty.deleteById(id);
	}

	@Override
	public List<Product> searchProduct(String keyword) {
		// TODO Auto-generated method stub
		return productRepostiroty.searchCategory(keyword);
	}

	@Override
	public Page<Product> getAll(Integer pageNo) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo - 1, 2);
		return productRepostiroty.findAll(pageable);
	}

	@Override
	public Page<Product> searchProduct(String keyword, Integer pageNo) {
		List<Product> list = this.searchProduct(keyword);
		Pageable pageable = PageRequest.of(pageNo - 1, 2);
		Integer start = (int) pageable.getOffset();
		Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size() : pageable.getOffset() + pageable.getPageSize());
		list = list.subList(start, end);
		return new PageImpl<Product>(list, pageable, this.searchProduct(keyword).size());
	}

}
