package com.emily.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductList {
	
	// Same POJO class as in the product service to be able to reference
	List<Product> products;
	
}
