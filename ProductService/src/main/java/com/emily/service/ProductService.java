package com.emily.service;

import java.sql.SQLIntegrityConstraintViolationException;

import com.emily.entity.Product;
import com.emily.entity.ProductList;

public interface ProductService {
	
	// ProductList class aids later when calling Rest API to return object
	
	public ProductList getAllProducts();
	
	public ProductList getByKeyword(String keyword);
	
	public Product addProduct(Product product) throws SQLIntegrityConstraintViolationException;
	
	public boolean deleteProductByName(String productName);
	
	public Product updateQuantityByName(String productName, int quantity);
	
	public ProductList quantityAscending();

}
