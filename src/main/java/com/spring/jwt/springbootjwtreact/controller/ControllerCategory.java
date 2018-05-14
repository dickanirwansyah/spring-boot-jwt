package com.spring.jwt.springbootjwtreact.controller;

import com.spring.jwt.springbootjwtreact.entity.Category;
import com.spring.jwt.springbootjwtreact.request.CreatedCategoryRequest;
import com.spring.jwt.springbootjwtreact.request.GetDetailCategoryIdRequest;
import com.spring.jwt.springbootjwtreact.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/category")
public class ControllerCategory {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<Category>> listCategory(){
        return Optional.ofNullable(categoryService.listCategory())
                .map(callbackJSON -> new ResponseEntity<>(callbackJSON, HttpStatus.OK))
                .orElse(new ResponseEntity<List<Category>>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/created", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Category> createdCategory(@Valid @RequestBody CreatedCategoryRequest request){
        return Optional.ofNullable(categoryService.createdCategory(request))
                .map(callbackJSON -> new ResponseEntity<>(callbackJSON, HttpStatus.CREATED))
                .orElse(new ResponseEntity<Category>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping(value = "/{categoryId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Category> getDetailedCategoryId(@PathVariable String categoryId){
        GetDetailCategoryIdRequest request = GetDetailCategoryIdRequest
                .builder().categoryId(categoryId).build();
        Category category = categoryService.findByCategoryId(request);
        if(category == null){
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }
}
