package com.emily.resource;

import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emily.entity.Product;
import com.emily.entity.ProductList;
import com.emily.service.ProductService;

@RestController
public class ProductResource {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductList getAllProductsResource() {
		return productService.getAllProducts();
	}
	
//	@GetMapping(path = "/products/{productName}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public Product getProductResource(@PathVariable("productName") String productName) {
//		return productService.getProductByName(productName);
//	}
	
//	@GetMapping(path = "/categories/{productCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ProductList getProductCategory(@PathVariable("productCategory") String productCategory) {
//		return productService.getProductByCategory(productCategory);
//	}
	
	@GetMapping(path = "/searches/{keyword}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductList getProductsResource(@PathVariable("keyword") String keyword) {
		return productService.getByKeyword(keyword);
	}
	
	@PostMapping(path = "/products", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addProductResource(@RequestBody Product product) throws SQLIntegrityConstraintViolationException{
		try {
			if(productService.addProduct(product) != null) 
				return "Product added";
			else
				return "Product not added";
		} catch (SQLIntegrityConstraintViolationException e) {
			return "Product not added";
		}
	}
	
	// message for testing that api is working
	@DeleteMapping(path = "/products/{productName}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String deleteProduct(@PathVariable("productName") String productName) {
		if(productService.deleteProductByName(productName))
			return "Product deleted";
		else
			return "Product not deleted";
	}
	
	// message for testing that api is working
	@PutMapping(path = "/products/{productName}/{quantity}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Product updateProductQuantity(@PathVariable("productName") String productName, @PathVariable("quantity") int quantity) {
//		if(productService.updateQuantityByProduct(productName, quantity) != null)
//			return "Product updated";
//		else
//			return "Product not updated";
		return productService.updateQuantityByProduct(productName, quantity);
	}
	
	
	@GetMapping(path = "/reportprods", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductList reportsResource() {
		return productService.generateProductReport();
	}
	
}
