package com.emily.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.emily.entity.Product;
import com.emily.entity.ProductList;
import com.emily.persistence.ProductDao;

@Service
public class ProductServiceImpl implements ProductService {
	
	// creating an instance of the userDao to access JPA methods
	@Autowired
	private ProductDao productDao;

	// getting all products in alphabetical order to display on homepage
	@Override
	public ProductList getAllProducts() {
		try {
			// create ProductList and populate with products alphabetically
			ProductList productList = new ProductList();
			productList.setProducts(productDao.findAll(Sort.by("productName").ascending()));
			return productList;
		} catch(Exception e) {
			// return null if an exception occurs
			return null;
		}
	}
	
	// searching by keyword for search bar
	@Override
	public ProductList getByKeyword(String keyword) {
		try {
			// create ProductList and populate with products that match keyword
			ProductList productList = new ProductList();
			productList.setProducts(productDao.findByKeyword(keyword));
			return productList;
		} catch(Exception e) {
			// return null if an exception occurs
			return null;
		}
	}
	
	@Override
	public Product addProduct(Product product) {
		try {
			// checking the product doesn't already exist before adding - unique constraint on product name
			if(productDao.findByProductName(product.getProductName()) == null) {
				// use built in JPA .save() method to update database
				productDao.save(product);
				return product;
			} else {
				// return null if product already exists
				return null;
			}
		} catch(Exception e) {
			// return null if an exception occurs
			return null;
		}
	}

	@Override
	public boolean deleteProductByName(String productName) {
		try {
			// checking that the product exists
			Product product = productDao.findByProductName(productName);
			if(product != null) {
				// use built in JPA to .delete if does exist
				productDao.delete(product);
				return true;
			} else {
				// return false if the product does not exist
				return false;
			}
		} catch(Exception e) {
			// return false if an exception occurs
			return false;
		}	
	}

	@Override
	public Product updateQuantityByName(String productName, int quantity) {
		try {
			// checking that the product exists
			Product product = productDao.findByProductName(productName);
			// if product does exist and quantity is greater than 0
			if(product != null && quantity > 0) {
				// set new value for quantity to reflect restock
				product.setQuantityAvailable(product.getQuantityAvailable() + quantity);
				// use built in JPA .save() method to update database
				productDao.save(product);
				return product;
				// if product does exist and quantity is less than 0
			} else if(product!= null && quantity < 0) {
				// update both available and sold to reflect that items have been sold
				product.setQuantityAvailable(product.getQuantityAvailable() + quantity);
				product.setQuantitySold(product.getQuantitySold() - quantity);
				// use built in JPA .save() method to update database
				productDao.save(product);
				return product;
			} else {
				// return null if the product does not exist or 0 entered
				return null;
			}
		} catch(Exception e) {
			// return null if an exception occurs
			return null;
		}
	}
	
	/* getting all products in quantity ascending order - will show products which need to be reordered 
	at the top of the generated report */
	@Override
	public ProductList quantityAscending() {
		try {
			// create ProductList and populate with products in increasing stock order
			ProductList productList = new ProductList();
			productList.setProducts(productDao.findAll(Sort.by("quantityAvailable").ascending()));
			// return list
			return productList;
		} catch(Exception e) {
			// return null if an exception occurs
			return null;
		}
	}

}
