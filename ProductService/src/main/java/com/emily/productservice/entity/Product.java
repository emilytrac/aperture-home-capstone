package com.emily.productservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")
public class Product {

    /* productId is the primary key in MySQL table
    field names match up with fields in the database */
    @Id
    private int productId;
    private String productName;
    private String productCategory;
    private int quantityAvailable;
    private double pricePerItem;
    private int quantitySold;

    /* custom constructor
    id not required as it is an auto increment - product name must be unique */
    public Product(String productName, String productCategory, int quantityAvailable, double pricePerItem, int quantitySold) {
        //super();
        this.productName = productName;
        this.productCategory = productCategory;
        this.quantityAvailable = quantityAvailable;
        this.pricePerItem = pricePerItem;
        this.quantitySold = quantitySold;
    }
}
