package com.emily.productservice.repository;

import com.emily.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    // custom JPA method to return product from the name
    Product findByProductName(String productName);

    /* JPA Native SQL query to search by either product or category using keyword
     * will return a list of product objects which can contain as many or as little
     * products as are found */
    @Query(value = "select * from product where productName like %:keyword% or productCategory like %:keyword%", nativeQuery = true)
    List<Product> findByKeyword(@Param("keyword") String keyword);
}
