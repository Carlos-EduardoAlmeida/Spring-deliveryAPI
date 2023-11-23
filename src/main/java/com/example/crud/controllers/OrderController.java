package com.example.crud.controllers;

import com.example.crud.domain.Order;
import com.example.crud.domain.User;
import com.example.crud.domain.request.RequestEmail;
import com.example.crud.domain.request.RequestId;
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
        try{
            User userSource = userRepository.findUserById(data.id());
            Order orderExists = orderRepository.findOrderByUserAndOrders(userSource, data.orders());
            if(orderExists != null){
                orderExists.setQuantity(orderExists.getQuantity()+data.quantity());
                orderRepository.save(orderExists);
            } else {
                Order newOrder = new Order();
                newOrder.setOrders(data.orders());
                newOrder.setUser(userSource);
                newOrder.setQuantity(data.quantity());
                newOrder.setImage(data.image());
                newOrder.setPrice(data.price());
                orderRepository.save(newOrder);
            }
            return ResponseEntity.ok(orderRepository.findOrderByUserAndOrders(userSource, data.orders()));
        }catch (RuntimeException exception){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrder(@PathVariable("id") String id){
        try{
            orderRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (RuntimeException exception){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity allOrdersForEmail(@PathVariable("id") String id){
        User user = userRepository.findUserById(id);
        return ResponseEntity.ok(user.getOrders());
    }
}
