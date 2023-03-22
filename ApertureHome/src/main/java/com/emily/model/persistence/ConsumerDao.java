package com.emily.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emily.entity.Product;

// Placed here for potential application extension later
@Repository
public interface ConsumerDao extends JpaRepository<Product, Integer> {
	

}
