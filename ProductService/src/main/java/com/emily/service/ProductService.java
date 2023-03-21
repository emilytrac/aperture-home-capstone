package com.emily.service;

import java.sql.SQLIntegrityConstraintViolationException;

import com.emily.entity.Product;
import com.emily.entity.ProductList;

public interface ProductService {
	
	// method to return all products in a list
	public ProductList getAllProducts();
	
	// method to return a list of products that match keyword
	public ProductList getByKeyword(String keyword);
	
	// method to add a product to the database
	public Product addProduct(Product product) throws SQLIntegrityConstraintViolationException;
	
	// method to delete a product by name
	public boolean deleteProductByName(String productName);
	
	// method to update stock by name
	public Product updateQuantityByName(String productName, int quantity);
	
	// method to return a list of products will increasing stock levels
	public ProductList quantityAscending();

}
