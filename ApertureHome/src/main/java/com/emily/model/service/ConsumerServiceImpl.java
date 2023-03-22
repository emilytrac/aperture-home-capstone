package com.emily.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.emily.entity.Product;
import com.emily.entity.ProductList;
import com.emily.entity.User;

@Service
public class ConsumerServiceImpl implements ConsumerService {
	
	// will allow RESTful API calls
	@Autowired 
	public RestTemplate restTemplate;

	@Override
	public User loginCheck(String userEmail, String userPassword) {
		// calling the API to return user
		User user = restTemplate.getForObject("http://localhost:8082/users/" + userEmail + "/" + userPassword, User.class);
		return user;
	}

	@Override
	public ProductList showAllProducts() {
		// calling the API to return all products
		ProductList products = restTemplate.getForObject("http://localhost:8084/products/", ProductList.class);
		return products;
	}

	@Override
	public Product addNewProduct(String productName, String productCategory, int quantityAvailable, double pricePerItem,
			int quantitySold) {
		// generating a new product instance
		Product product = new Product();
		
		// populating product object fields
		product.setProductName(productName);
		product.setProductCategory(productCategory);
		product.setQuantityAvailable(quantityAvailable);
		product.setPricePerItem(pricePerItem);
		product.setQuantitySold(quantitySold);
		
		// calling the API to add the product to the database
		String message = restTemplate.postForObject("http://localhost:8084/products/", product, String.class);
		
		// returning either the product or null based on response from the API
		if("Product added".equals(message))
			return product;
		else
			return null;
	}

	@Override
	public boolean deleteProduct(String productName) {
		HttpHeaders headers = new HttpHeaders();
		// setting response type of the HTTP request
	    HttpEntity<String> entity = new HttpEntity<String>(headers);
	    
	    /* using the exchange method rather than the delete method to call the API
	     allows a return value - in this case the value of the API String response 
	     - delete method had a void return type */
	    String deleted = restTemplate.exchange("http://localhost:8084/products/" + productName, HttpMethod.DELETE, entity, String.class).toString();
		
	    // returning either true or false based on the response from the API
	    if("Product deleted".equals(deleted))
	    	return true;
	    else
	    	return false;
	}

	@Override
	public Product updateProduct(String productName, int quantity) {
		 HttpHeaders headers = new HttpHeaders();
		// setting response type of the HTTP request
	     HttpEntity<Product> entity = new HttpEntity<Product>(headers);
	   
	     /* using the exchange method to call the API to update the product stock,
	     allows a return value - in this case the object body of the API response  */
	     Product product = restTemplate.exchange("http://localhost:8084/products/" + productName + "/" + quantity, HttpMethod.PUT, entity, Product.class).getBody();
	     return product;
	}
	
	@Override
	public ProductList searchByKeyword(String keyword) {
		// calling the API to get a list of products matching keyword
		ProductList products = restTemplate.getForObject("http://localhost:8084/searches/" + keyword, ProductList.class);
		return products;
	}

	@Override
	public ProductList generateProductReport() {
		// calling the API to return all products in order of stock level
		ProductList products = restTemplate.getForObject("http://localhost:8084/reports/", ProductList.class);
		return products;
	}

}
