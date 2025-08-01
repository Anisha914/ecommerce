package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> findAll();

    void save(Order order);

    List<Order> findByUserEmail(String name);
}
