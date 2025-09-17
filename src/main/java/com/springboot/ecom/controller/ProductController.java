package com.springboot.ecom.controller;


import com.springboot.ecom.model.Product;
import com.springboot.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public Product addproduct(Product product) {
        return productRepository.save(product);
    }

    @GetMapping
    public List<Product> getAllproducts() {
        return productRepository.findAll();
    }
}
