package com.emily.app.model.service;

import com.emily.app.entity.Product;
import com.emily.app.entity.ProductList;
import com.emily.app.entity.User;

public interface ConsumerService {

    /* all methods will use a RestTemplate instance to connect to the APIs
     * created in the user and product services */

    // checking for valid user
    User loginCheck(String userEmail, String userPassword);

    // showing all products
    ProductList showAllProducts();

    // adding a new product
    Product addNewProduct(String productName, String productCategory,
                                 int quantityAvailable, double pricePerItem, int quantitySold);

    // deleting a product
    boolean deleteProduct(String productName);

    // updating stock level
    Product updateQuantity(String productName, int quantity);

    // searching products
    ProductList searchByKeyword(String keyword);

    // generating a product report to allow the user to download a PDF
    ProductList generateProductReport();

}
