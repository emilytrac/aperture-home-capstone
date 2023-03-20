package com.emily.service;

import java.sql.SQLIntegrityConstraintViolationException;

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

	@Override
	public ProductList getAllProducts() {
		ProductList productList = new ProductList();
		productList.setProducts(productDao.findAll());
		return productList;
	}

//	@Override
//	public Product getProductByName(String productName) {
//		return productDao.findByProductName(productName);
//	}

//	@Override
//	public ProductList getProductByCategory(String productCategory) {
//		ProductList productList = new ProductList();
//		productList.setProducts(productDao.findByProductCategory(productCategory));
//		return productList;
//	}
	
	@Override
	public ProductList getByKeyword(String keyword) {
		ProductList productList = new ProductList();
		productList.setProducts(productDao.findByKeyword(keyword));
		return productList;
	}

	// checking the product doesn't already exist before adding - unique constraint on product name
	@Override
	public Product addProduct(Product product) throws SQLIntegrityConstraintViolationException{
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

	@Override
	public boolean deleteProductByName(String productName) {
		Product product = productDao.findByProductName(productName);
		if(product != null) {
			productDao.delete(product);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Product updateQuantityByProduct(String productName, int quantity) {
		Product product = productDao.findByProductName(productName);
		if(product != null) {
			product.setQuantityAvailable(product.getQuantityAvailable() + quantity);
			productDao.save(product);
			return product;
		} else {
			return null;
		}
	}

	// could potentially use maps here
//	@Override
//	public ProductReport generateProductReport(String productName) {
//		Product product = productDao.findByProductName(productName);
//		if(product != null) {
//			int quantityAvailable = product.getQuantityAvailable();
//			int quantitySold = product.getQuantitySold();
//			double pricePerItem = product.getPricePerItem();
//			double totalSales = product.getPricePerItem() * product.getQuantitySold();
//			String needToReorder = "No";
//			if (quantityAvailable < 50) 
//				needToReorder = "Reorder now";
//			if (quantityAvailable > 50 && quantityAvailable < 100)
//				needToReorder = "Reorder soon";
//			
//			ProductReport productReport = new ProductReport(productName, quantityAvailable,
//					quantitySold, pricePerItem, totalSales, needToReorder);
//			return productReport;
//		}
//		return null;
//	}

	@Override
	public ProductList generateProductReport() {
		ProductList productList = new ProductList();
		productList.setProducts(productDao.findAll(Sort.by("quantityAvailable").ascending()));
		return productList;
	}

}
