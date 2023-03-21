package com.emily.resource;

import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emily.entity.Product;
import com.emily.entity.ProductList;
import com.emily.service.ProductService;

@RestController
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
	@GetMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductList getAllProductsResource() {
		return productService.getAllProducts();
	}
	
	/* Creating an API path to retrieve (GET) select products from the database
	 * Will return a ProductList with items containing keyword or null
	 * ProductList object can be mapped to a JSON format
	 */
	@GetMapping(path = "/searches/{keyword}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductList getProductsResource(@PathVariable("keyword") String keyword) {
		return productService.getByKeyword(keyword);
	}
	 
	/* Creating an API path to add (POST) a product to the database
	 * Will return a String signifying the outcome
	 * Potential SQL error thrown if product already exists - handled in service
	 * Product object can be mapped to a JSON format, String returned is plain text
	 */
	@PostMapping(path = "/products", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
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
	@DeleteMapping(path = "/products/{productName}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String deleteProductResource(@PathVariable("productName") String productName) {
		if(productService.deleteProductByName(productName))
			return "Product deleted";
		else
			return "Product not deleted";
	}
	
	/* Creating an API path to update (PUT) a product in the database
	 * Will return either a Product object or null
	 * Product object can be mapped to a JSON format
	 */
	@PutMapping(path = "/products/{productName}/{quantity}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Product updateProductResource(@PathVariable("productName") String productName, @PathVariable("quantity") int quantity) {
		return productService.updateQuantityByName(productName, quantity);
	}
	
	/* Creating an API path to retrieve (GET) all products from the database
	 * Calls stock order service method
	 * Will return either a ProductList object or null
	 * ProductList object can be mapped to a JSON format
	 */
	@GetMapping(path = "/reports", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductList reportsResource() {
		return productService.quantityAscending();
	}
	
}
