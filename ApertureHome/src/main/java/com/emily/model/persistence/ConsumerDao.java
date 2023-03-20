package com.emily.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emily.entity.Product;

@Repository
public interface ConsumerDao extends JpaRepository<Product, Integer> {
	

}
