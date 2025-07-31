package com.example.ecommerce.repository;

import com.example.ecommerce.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
}
