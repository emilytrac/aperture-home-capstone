package com.emily.service;

import java.sql.SQLIntegrityConstraintViolationException;

import com.emily.entity.Product;
import com.emily.entity.ProductList;

public interface ProductService {
	
	ProductList getAllProducts();
	
//	Product getProductByName(String productName);
//	
//	ProductList getProductByCategory(String productCategory);
	
	ProductList getByKeyword(String keyword);
	
	Product addProduct(Product product) throws SQLIntegrityConstraintViolationException;
	
	boolean deleteProductByName(String productName);
	
	Product updateQuantityByProduct(String productName, int quantity);
	
	ProductList generateProductReport();

}
