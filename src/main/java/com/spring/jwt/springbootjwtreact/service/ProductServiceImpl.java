package com.spring.jwt.springbootjwtreact.service;

import com.spring.jwt.springbootjwtreact.entity.Category;
import com.spring.jwt.springbootjwtreact.entity.Product;
import com.spring.jwt.springbootjwtreact.repository.ProductRepository;
import com.spring.jwt.springbootjwtreact.request.CreatedProductRequest;
import com.spring.jwt.springbootjwtreact.request.GetDetailProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createdProduct(CreatedProductRequest request) {
        Product product = newProduct(
                request.getName(),
                request.getStock(),
                request.getPrice(),
                request.getCategory()
        );
        productRepository.save(product);
        return product;
    }

    @Override
    public Product findByProductId(GetDetailProductRequest request) {
        return productRepository.findByProductId(request.getProductId());
    }

    private Product newProduct(String name, int stock, int price, Category category){
        return Product.builder()
                .name(name)
                .stock(stock)
                .price(price)
                .category(category)
                .build();
    }

    @Override
    public List<Product> listProduct() {
        List<Product> listProduct = new ArrayList<>();
        for(Product product: productRepository.findAll()){
            listProduct.add(product);
        }
        return listProduct;
    }
}
