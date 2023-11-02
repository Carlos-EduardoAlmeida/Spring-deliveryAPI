package com.example.crud.controllers;

import com.example.crud.domain.Order;
import com.example.crud.domain.User;
import com.example.crud.domain.request.RequestEmail;
import com.example.crud.domain.request.RequestPostOrder;
import com.example.crud.repository.OrderRepository;
import com.example.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity registerOrder(@RequestBody @Valid RequestPostOrder data){
        User userSource = userRepository.findByEmail(data.email());
        Order newOrder = new Order();
        newOrder.setOrders(data.orders());
        newOrder.setUser(userSource);
        newOrder.setQuantity(data.quantity());
        orderRepository.save(newOrder);
        return ResponseEntity.ok("pedido cadastrado: "+newOrder.getOrders());
    }

    @PostMapping("/email")
    public ResponseEntity allOrdersForEmail(@RequestBody @Valid RequestEmail data){
        User user = userRepository.findByEmail(data.email());
        return ResponseEntity.ok(user.getOrders());
    }
}
