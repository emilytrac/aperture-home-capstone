package com.emily.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReport {

	private String productName;
	private int quantityAvailable;
	private int quantitySold;
	private double pricePerItem;
	private double totalSales;
	private String needToReorder;
	
}
