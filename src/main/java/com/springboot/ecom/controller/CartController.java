package com.springboot.ecom.controller;

import com.springboot.ecom.model.Cart;
import com.springboot.ecom.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartRepository cartRepository;

    @PostMapping
    public Cart addToCart(@RequestBody Cart cart) {
    return cartRepository.save(cart);
    }
    @GetMapping
    public List<Cart> viewCart() {
        return cartRepository.findAll();
    }
}
