package com.nt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nt.entity.Product;
import com.nt.reposetory.ProductReposetory;

@Service
public class ProductService {

	@Autowired
	private ProductReposetory productResposetory;
	
	
	public Product saveProduct(Product product) {
		
		return productResposetory.save(product) ;
		
	}
	
	public Optional<Product> getById(long id) {
		
		return productResposetory.findById(id);
		
	}
	public void deleteProductById(long id) {
		productResposetory.deleteById(id);
		
	}
	public List<Product> getAllProducts(int page, int size) {
		
		Pageable pageable  = PageRequest.of(page, size);
		
		Page<Product> pages = productResposetory.findAll(pageable);
		
		List<Product> list = new ArrayList<Product>();
		for(Product p : pages) {
			list.add(p);
		}
		return list;
	}
	
		
	}

