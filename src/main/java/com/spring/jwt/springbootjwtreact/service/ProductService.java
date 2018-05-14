package com.spring.jwt.springbootjwtreact.service;

import com.spring.jwt.springbootjwtreact.entity.Product;
import com.spring.jwt.springbootjwtreact.request.CreatedProductRequest;
import com.spring.jwt.springbootjwtreact.request.GetDetailProductRequest;

import java.util.List;

public interface ProductService {

    Product createdProduct(CreatedProductRequest request);
    Product findByProductId(GetDetailProductRequest request);
    List<Product> listProduct();
}
