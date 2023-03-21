package com.emily.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.emily.entity.Product;
import com.emily.entity.ProductList;
import com.emily.persistence.ProductDao;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;

	// getting all products in alphabetical order to display
	
	@Override
	public ProductList getAllProducts() {
		try {
			ProductList productList = new ProductList();
			productList.setProducts(productDao.findAll(Sort.by("productName").ascending()));
			return productList;
		} catch(Exception e) {
			return null;
		}
	}
	
	// searching by keyword
	
	@Override
	public ProductList getByKeyword(String keyword) {
		try {
			ProductList productList = new ProductList();
			productList.setProducts(productDao.findByKeyword(keyword));
			return productList;
		} catch(Exception e) {
			return null;
		}
	}

	// checking the product doesn't already exist before adding - unique constraint on product name
	
	@Override
	public Product addProduct(Product product) {
		try {
			if(productDao.findByProductName(product.getProductName()) == null) {
				productDao.save(product);
				return product;
			} else {
				return null;
			}
		} catch(Exception e) {
			return null;
		}
	}
	
	// deleting product by name - unique constraint on product name

	@Override
	public boolean deleteProductByName(String productName) {
		try {
			Product product = productDao.findByProductName(productName);
			if(product != null) {
				productDao.delete(product);
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			return false;
		}	
	}
	
	// updating quantity by name - unique constraint on product name

	@Override
	public Product updateQuantityByName(String productName, int quantity) {
		try {
			Product product = productDao.findByProductName(productName);
			if(product != null) {
				product.setQuantityAvailable(product.getQuantityAvailable() + quantity);
				productDao.save(product);
				return product;
			} else {
				return null;
			}
		} catch(Exception e) {
			return null;
		}
	}
	
	// getting all products in quantity ascending order - will show products which need to be reordered at the top

	@Override
	public ProductList quantityAscending() {
		try {
			ProductList productList = new ProductList();
			productList.setProducts(productDao.findAll(Sort.by("quantityAvailable").ascending()));
			return productList;
		} catch(Exception e) {
			return null;
		}
	}

}
