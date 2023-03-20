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
	
	@Autowired 
	public RestTemplate restTemplate;

	@Override
	public User loginCheck(String userEmail, String userPassword) {
		User user = restTemplate.getForObject("http://localhost:8082/users/" + userEmail + "/" + userPassword, User.class);
		return user;
	}

	@Override
	public ProductList showAllProducts() {
		ProductList products = restTemplate.getForObject("http://localhost:8084/products/", ProductList.class);
		return products;
	}

	@Override
	public Product addNewProduct(String productName, String productCategory, int quantityAvailable, double pricePerItem,
			int quantitySold) {
		Product product = new Product();
		
		product.setProductName(productName);
		product.setProductCategory(productCategory);
		product.setQuantityAvailable(quantityAvailable);
		product.setPricePerItem(pricePerItem);
		product.setQuantitySold(quantitySold);
		
		String message = restTemplate.postForObject("http://localhost:8084/products/", product, String.class);
		
		if("Product added".equals(message))
			return product;
		else
			return null;
	}

	@Override
	public void deleteProduct(String productName) {
		restTemplate.delete("http://localhost:8084/products/" + productName);
	}

	@Override
	public Product updateProduct(String productName, int quantity) {
		 HttpHeaders headers = new HttpHeaders();
	     HttpEntity<Product> entity = new HttpEntity<Product>(headers);
	   
	     Product product = restTemplate.exchange("http://localhost:8084/products/" + productName + "/" + quantity, HttpMethod.PUT, entity, Product.class).getBody();
	     return product;
	}
	
	@Override
	public ProductList searchByKeyword(String keyword) {
		ProductList products = restTemplate.getForObject("http://localhost:8084/searches/" + keyword, ProductList.class);
		return products;
	}

	@Override
	public ProductList generateProductReport() {
		ProductList products = restTemplate.getForObject("http://localhost:8084/reportprods/", ProductList.class);
		return products;
	}

}
