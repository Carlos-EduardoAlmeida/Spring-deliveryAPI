package com.example.crud.repository;

import com.example.crud.domain.Order;
import com.example.crud.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllByUser(User user);

    Order findOrderByUserAndOrders(User user, String orders);
}
