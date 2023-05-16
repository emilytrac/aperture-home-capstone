package com.emily.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductList {

    // Same POJO class as in the product service to be able to reference
    List<Product> products;

}
