package com.spring.jwt.springbootjwtreact.service;

import com.spring.jwt.springbootjwtreact.entity.Category;
import com.spring.jwt.springbootjwtreact.repository.CategoryRepository;
import com.spring.jwt.springbootjwtreact.request.CreatedCategoryRequest;
import com.spring.jwt.springbootjwtreact.request.GetDetailCategoryIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createdCategory(CreatedCategoryRequest request) {
        Category category = newCategory(request.getName(), request.getDescription());
        categoryRepository.save(category);
        return category;
    }

    private Category newCategory(String name, String description){
        return Category.builder()
                .name(name)
                .description(description)
                .build();
    }

    @Override
    public List<Category> listCategory() {
        List<Category> listCategory = new ArrayList<>();
        for(Category category: categoryRepository.findAll()){
            listCategory.add(category);
        }
        return listCategory;
    }


    @Override
    public Optional<Category> findByCategoryIdIn(String categoryId) {
        return null;
    }

    @Override
    public Category findByCategoryId(GetDetailCategoryIdRequest request) {
        return categoryRepository.findByCategoryId(request.getCategoryId());
    }

    @Override
    public Optional<Category> findByCategoryByNameIn(String name) {
        return null;
    }

    @Override
    public Category findByCategoryByName(String name) {
        return null;
    }
}
