package com.emily.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emily.entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
	
	// custom JPA method to return product from the name
	public Product findByProductName(String productName);

	// JPA Native SQL query to search by either product or category using keyword
	@Query(value = "select * from product where productName like %:keyword% or productCategory like %:keyword%", nativeQuery = true)
	public List<Product> findByKeyword(@Param("keyword") String keyword);
	
}
