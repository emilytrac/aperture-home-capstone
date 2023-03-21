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
	
	@GetMapping(path = "/searches/{keyword}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductList getProductsResource(@PathVariable("keyword") String keyword) {
		return productService.getByKeyword(keyword);
	}
	
	// potential error thrown if product already exists - handled in service
	
	@PostMapping(path = "/products", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addProductResource(@RequestBody Product product) throws SQLIntegrityConstraintViolationException {
		if(productService.addProduct(product) != null) 
			return "Product added";
		else
			return "Product not added";
	}

	@DeleteMapping(path = "/products/{productName}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String deleteProductResource(@PathVariable("productName") String productName) {
		if(productService.deleteProductByName(productName))
			return "Product deleted";
		else
			return "Product not deleted";
	}
	
	@PutMapping(path = "/products/{productName}/{quantity}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Product updateProductResource(@PathVariable("productName") String productName, @PathVariable("quantity") int quantity) {
		return productService.updateQuantityByName(productName, quantity);
	}
	
	@GetMapping(path = "/reports", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductList reportsResource() {
		return productService.quantityAscending();
	}
	
}
