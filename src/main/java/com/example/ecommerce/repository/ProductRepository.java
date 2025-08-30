package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    void deleteById(Long id);
}