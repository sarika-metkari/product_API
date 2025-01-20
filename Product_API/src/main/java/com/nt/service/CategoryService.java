package com.nt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nt.entity.Category;
import com.nt.entity.Product;
import com.nt.reposetory.CategoryReposetory;

@Service
public class CategoryService {

	@Autowired
	private CategoryReposetory categoryReposetory;
	
	public Category saveCategoey(Category category) {
		return categoryReposetory.save(category);
	}
	
	public Optional<Category> getCategoryById(long id) {
		return categoryReposetory.findById(id);
		
	}
	public void deleteProductById(long id) {
		categoryReposetory.deleteById(id);
	}
	public List<Category> getAllCatagory(int page,int size) {
		Pageable pageable=PageRequest.of(page, size);
		Page<Category> pages= categoryReposetory.findAll(pageable);
		
		List<Category>list=new ArrayList<Category>();
		for(Category c:pages) {
			list.add(c);
		}
		return list;
	}
}
