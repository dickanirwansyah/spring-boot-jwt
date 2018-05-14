package com.spring.jwt.springbootjwtreact.service;

import com.spring.jwt.springbootjwtreact.entity.Category;
import com.spring.jwt.springbootjwtreact.request.CreatedCategoryRequest;
import com.spring.jwt.springbootjwtreact.request.GetDetailCategoryIdRequest;
import com.spring.jwt.springbootjwtreact.request.UpdatedCategoryRequest;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category createdCategory(CreatedCategoryRequest request);
    Category updatedCategory(UpdatedCategoryRequest request);
    List<Category> listCategory();
    Optional<Category> findByCategoryIdIn(String categoryId);
    Category findByCategoryId(GetDetailCategoryIdRequest request);
    Optional<Category> findByCategoryByNameIn(String name);
    Category findByCategoryByName(String name);
}
