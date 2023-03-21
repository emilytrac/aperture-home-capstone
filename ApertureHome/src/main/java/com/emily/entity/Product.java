package com.emily.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table(name="product")
public class Product {
	
	/* Same POJO class as in the product service no need to map to database
	as all interaction handled within the product service */
//	@Id
	private int productId;
	private String productName;
	private String productCategory;
	private int quantityAvailable;
	private double pricePerItem;
	private int quantitySold;
	
	// id not required as it is an auto increment

	public Product(String productName, String productCategory, int quantityAvailable, double pricePerItem, int quantitySold) {
		//super();
		this.productName = productName;
		this.productCategory = productCategory;
		this.quantityAvailable = quantityAvailable;
		this.pricePerItem = pricePerItem;
		this.quantitySold = quantitySold;
	}
	
}
