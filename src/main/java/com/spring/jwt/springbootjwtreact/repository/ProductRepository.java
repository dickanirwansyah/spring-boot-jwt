package com.spring.jwt.springbootjwtreact.repository;

import com.spring.jwt.springbootjwtreact.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{

    Product findByProductId(String productId);
    Product findByName(String name);
}
