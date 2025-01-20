package com.nt.controller;

import java.util.Optional;

import javax.validation.Valid;

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

import com.nt.entity.Product;
import com.nt.service.ProductService;

import antlr.collections.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<String> createProduct(@RequestBody  @Valid Product product) {
		Product saveProduct=productService.saveProduct(product);
		return ResponseEntity.ok("product added successfully with ID :" + saveProduct.getId());
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable long id) {
		Optional<Product>product=productService.getById(id);
	
		if(product.isPresent()){
			return ResponseEntity.ok(product.get());
		}else {
			return ResponseEntity.status(404).body("product not found with ID:" +id);
		}
	     
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProductById(@PathVariable long id) {
		Optional<Product> product=productService.getById(id);
		if(product.isPresent()) {
			productService.deleteProductById(id);
			return ResponseEntity.ok("product delete successfully");
		}else {
			return ResponseEntity.status(404).body("product not found with ID:"+id);
		}
		 
	}
	@GetMapping
	public ResponseEntity<Object> getAllProducts(@RequestParam (value = "page",defaultValue = "0",required = false) int page,
			                                     @RequestParam (value = "size" ,defaultValue ="5",required = false)int size){
		java.util.List<Product> products=productService.getAllProducts(page,size);
		if(products.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("records not present");
		}
	
		return ResponseEntity.ok(products);
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProductByID(@PathVariable long id,@RequestBody Product updateProductByID) {
		Optional<Product> existingProduct=productService.getById(id);
		if(existingProduct.isPresent()) {
			Product product=existingProduct.get();
			product.setName(updateProductByID.getName());
			product.setDescription(updateProductByID.getDescription());
			product.setPrice(updateProductByID.getPrice());
			product.setCategory(updateProductByID.getCategory());
			productService.saveProduct(product);
			return ResponseEntity.ok("product updated successfully with ID:"+id);
			
			
		}else {
			return ResponseEntity.status(404).body("product not found with ID:" +id);
		}
		
		
	}
}
