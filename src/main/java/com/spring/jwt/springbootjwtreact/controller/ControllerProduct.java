package com.spring.jwt.springbootjwtreact.controller;

import com.spring.jwt.springbootjwtreact.entity.Product;
import com.spring.jwt.springbootjwtreact.request.CreatedProductRequest;
import com.spring.jwt.springbootjwtreact.request.GetDetailProductRequest;
import com.spring.jwt.springbootjwtreact.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/product")
public class ControllerProduct {

    @Autowired
    private ProductService productService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<Product>> listProduct(){
        return Optional.ofNullable(productService.listProduct())
                .map(callbackJSON -> new ResponseEntity<>(callbackJSON, HttpStatus.OK))
                .orElse(new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<Product> findByProductId(@PathVariable String productId){
        GetDetailProductRequest request = GetDetailProductRequest
                .builder().productId(productId).build();
        Product product = productService.findByProductId(request);
        if(product == null){
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PostMapping(value = "/created")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Product> createdProduct(@Valid @RequestBody CreatedProductRequest request){
        return Optional.ofNullable(productService.createdProduct(request))
                .map(callbackJSON -> new ResponseEntity<>(callbackJSON, HttpStatus.CREATED))
                .orElse(new ResponseEntity<Product>(HttpStatus.BAD_REQUEST));
    }
}
