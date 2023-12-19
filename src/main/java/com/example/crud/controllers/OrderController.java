package com.example.crud.controllers;

import com.example.crud.domain.Order;
import com.example.crud.domain.User;
import com.example.crud.domain.request.ListRequestPostOrder;
import com.example.crud.domain.request.RequestEmail;
import com.example.crud.domain.request.RequestId;
import com.example.crud.domain.request.RequestPostOrder;
import com.example.crud.repository.OrderRepository;
import com.example.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity registerOrder(@RequestBody @Valid ListRequestPostOrder listData) {
        List<RequestPostOrder> requestPostOrders = listData.listRequestPostOrder();
        List<Order> resultOrders = new ArrayList<>();

        for (RequestPostOrder data : requestPostOrders) {
            try {
                User userSource = userRepository.findUserById(listData.id());
                Order orderExists = orderRepository.findOrderByUserAndOrders(userSource, data.orders());
                if (orderExists != null) {
                    orderExists.setQuantity(orderExists.getQuantity() + data.quantity());
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

                resultOrders.add(orderRepository.findOrderByUserAndOrders(userSource, data.orders()));
            } catch (RuntimeException exception) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(resultOrders);
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
