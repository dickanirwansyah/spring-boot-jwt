package com.spring.jwt.springbootjwtreact.repository;

import com.spring.jwt.springbootjwtreact.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String>{

    Optional<Category> findByCategoryIdIn(String categoryId);
    Optional<Category> findByNameIn(String name);
    Category findByCategoryId(String categoryId);
    Category findByName(String name);
}
