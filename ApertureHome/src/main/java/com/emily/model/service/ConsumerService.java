package com.emily.model.service;

import com.emily.entity.Product;
import com.emily.entity.ProductList;
import com.emily.entity.User;

public interface ConsumerService {
	
	public User loginCheck(String userEmail, String userPassword);
	
	public ProductList showAllProducts();
	
	public Product addNewProduct(String productName, String productCategory, 
			int quantityAvailable, double pricePerItem, int quantitySold);
	
	public boolean deleteProduct(String productName);
	
	public Product updateProduct(String productName, int quantity);
	
	public ProductList searchByKeyword(String keyword);
	
	ProductList generateProductReport();

}
