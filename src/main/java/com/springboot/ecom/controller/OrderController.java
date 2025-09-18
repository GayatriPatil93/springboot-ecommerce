package com.springboot.ecom.controller;

import com.springboot.ecom.model.Cart;
import com.springboot.ecom.model.Order;
import com.springboot.ecom.model.OrderItem;
import com.springboot.ecom.repository.CartRepository;
import com.springboot.ecom.repository.OrderItemRepository;
import com.springboot.ecom.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/place/{userId}")
    public Order placeOrder(@PathVariable Long userId) {
        List<Cart> cartItems = cartRepository.findAll();

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(cartItems.get(0).getUser());
        order.setOrderDate(LocalDateTime.now());

        double total =0.0;
        order = orderRepository.save(order);

        for(Cart cart : cartItems) {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(cart.getProduct());
            item.setQuantity(cart.getQuantity());
            item.setPrice(cart.getProduct().getPrice() * cart.getQuantity());

            total += item.getPrice();
            orderItemRepository.save(item);
        }
        order.setTotalprice(total);
        orderRepository.save(order);

        cartRepository.deleteAll();

        return order;
    }


}
