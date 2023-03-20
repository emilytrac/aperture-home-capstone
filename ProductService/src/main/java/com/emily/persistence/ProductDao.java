package com.emily.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emily.entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
	
	Product findByProductName(String productName);

//	List<Product> findByProductCategory(String productCategory);
	
	@Query(value = "select * from product where productName like %:keyword% or productCategory like %:keyword%", nativeQuery = true)
	List<Product> findByKeyword(@Param("keyword") String keyword);
	
}
