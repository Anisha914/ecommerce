package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Product;

public interface JpaRepository<T, T1> {
    Object findAll();

    Product save(Product product);

    ScopedValue<Object> findById(Long id);
}
