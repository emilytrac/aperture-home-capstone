package com.emily.app.model.persistence;

import com.emily.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Placed here for potential application extension later
@Repository
public interface ConsumerDao extends JpaRepository<Product, Integer> {


}
