package com.emily.model.service;

import com.emily.entity.Product;
import com.emily.entity.ProductList;
import com.emily.entity.User;

public interface ConsumerService {
	
	/* all methods will use a RestTemplate instance to connect to the APIs 
	 * created in the user and product services */
	
	// checking for valid user
	public User loginCheck(String userEmail, String userPassword);
	
	// showing all products
	public ProductList showAllProducts();
	
	// adding a new product
	public Product addNewProduct(String productName, String productCategory, 
			int quantityAvailable, double pricePerItem, int quantitySold);
	
	// deleting a product
	public boolean deleteProduct(String productName);
	
	// updating stock level
	public Product updateProduct(String productName, int quantity);
	
	// searching products
	public ProductList searchByKeyword(String keyword);
	
	// generating a product report to allow the user to download a PDF
	ProductList generateProductReport();

}
