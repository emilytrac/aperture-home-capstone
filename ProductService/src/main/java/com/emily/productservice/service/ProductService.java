package com.emily.productservice.service;

import com.emily.productservice.entity.Product;
import com.emily.productservice.entity.ProductList;

import java.sql.SQLIntegrityConstraintViolationException;

public interface ProductService {

    // method to return all products in a list
    ProductList getAllProducts();

    // method to return a list of products that match keyword
    ProductList getByKeyword(String keyword);

    // method to add a product to the database
    Product addProduct(Product product) throws SQLIntegrityConstraintViolationException;

    // method to delete a product by name
    boolean deleteProductByName(String productName);

    // method to update quantity by name
    Product updateQuantityByName(String productName, int quantity);

    // method to return a list of products will increase stock levels
    ProductList quantityAscending();
}
