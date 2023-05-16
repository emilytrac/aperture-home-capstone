package com.emily.productservice.resource;

import com.emily.productservice.entity.Product;
import com.emily.productservice.entity.ProductList;
import com.emily.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:8086")
public class ProductResource {

    /* creating an instance of the productService to access methods
    will automatically use productServiceImpl */
    @Autowired
    private ProductService productService;

    /* Creating an API path to retrieve (GET) all products from the database
     * Calls alphabetical service method
     * Will return either a ProductList object or null
     * ProductList object can be mapped to a JSON format
     */
    @GetMapping
    public ProductList getAllProductsResource() {
        return productService.getAllProducts();
    }

    /* Creating an API path to retrieve (GET) select products from the database
     * Will return a ProductList with items containing keyword or null
     * ProductList object can be mapped to a JSON format
     */
    @GetMapping(path = "/searches/{keyword}")
    public ProductList getProductsResource(@PathVariable("keyword") String keyword) {
        return productService.getByKeyword(keyword);
    }

    /* Creating an API path to add (POST) a product to the database
     * Will return a String signifying the outcome
     * Potential SQL error thrown if product already exists - handled in service
     * Product object can be mapped to a JSON format, String returned is plain text
     */
    @PostMapping
    public String addProductResource(@RequestBody Product product) throws SQLIntegrityConstraintViolationException {
        if(productService.addProduct(product) != null)
            return "Product added";
        else
            return "Product not added";
    }

    /* Creating an API path to delete (DELETE) a product in the database
     * Will return a String signifying the outcome
     * String returned is plain text
     */
    @DeleteMapping(path = "/{productName}")
    public String deleteProductResource(@PathVariable("productName") String productName) {
        if(productService.deleteProductByName(productName))
            return "Product deleted";
        else
            return "Product not deleted";
    }

    /* Creating API paths to update (PUT) a product in the database
     * Will return either a Product object or null
     * Product object can be mapped to a JSON format
     */
    @PutMapping(path = "/quantities/{productName}/{quantity}")
    public Product updateQuantityResource(@PathVariable("productName") String productName, @PathVariable("quantity") int quantity) {
        return productService.updateQuantityByName(productName, quantity);
    }

    /* Creating an API path to retrieve (GET) all products from the database
     * Calls stock order service method
     * Will return either a ProductList object or null
     * ProductList object can be mapped to a JSON format
     */
    @GetMapping(path = "/reports")
    public ProductList reportsResource() {
        return productService.quantityAscending();
    }
}
