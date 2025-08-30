package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.Order;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserEmail(String userEmail);

}