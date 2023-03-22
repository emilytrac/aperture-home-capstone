package com.emily.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductList {
	
	/* Lombok handles creation of the constructor - reduce boilerplate code
	having an ProductList object assists in calling RESTful services in the 
	main application controller */
	List<Product> products;
	
}
