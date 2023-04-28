package com.emily.productservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductList {

    /* Lombok handles creation of the constructor - reduce boilerplate code
    having an ProductList object assists in calling RESTful services in the
    main application controller */
    List<Product> products;

}

