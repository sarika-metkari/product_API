package com.nt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.Category;
import com.nt.service.CategoryService;
import com.nt.service.ProductService;

@RestController
@RequestMapping("/api/categories")
public class categoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<String> createCategory(@RequestBody Category category) {
		categoryService.saveCategoey(category);
		return ResponseEntity.ok("category saved successfully");
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable long id) {
		Optional<Category> category =categoryService.getCategoryById(id);
		if(category.isPresent()) {
			return ResponseEntity.ok(category.get());
		}else {
			return ResponseEntity.status(404).body("category not found with ID: " +id );
		}

	}
	@DeleteMapping("/{id}")
	 public ResponseEntity<?> deleteCategoryById(@PathVariable long id ) {
		Optional<Category> category=categoryService.getCategoryById(id);
		if(category.isPresent()){
            categoryService.deleteProductById(id);
            return ResponseEntity.ok("delete category by ID");
		}else {
			return ResponseEntity.status(404).body("category not found with ID :"+ id);
		}
	}
	@GetMapping
	public ResponseEntity<Object> getAllCategory(@RequestParam (value = "page", defaultValue = "0",required = false)int page,
			                                      @RequestParam (value = "size",defaultValue = "5",required = false)int size) {
		List<Category> catagory=categoryService.getAllCatagory(page,size);
		if(catagory.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("record not found");
		}
		return ResponseEntity.ok(catagory);
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<String> updateCategoryById(@PathVariable long id ,@RequestBody Category updateCategoryById) {
		Optional<Category> category=categoryService.getCategoryById(id);
		if(category.isPresent()) {
			Category existingcategory=category.get();
			existingcategory.setName(updateCategoryById.getName());
			categoryService.saveCategoey(existingcategory);
			return ResponseEntity.ok("category updated successfully");
			
		}else {
			return ResponseEntity.status(404).body("catagory is not found with ID:" +id);
		}
		
		
	}
}
