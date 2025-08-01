package com.example.ecommerce.controller;

import java.util.Map;

public interface HttpSession {
    Object getAttribute(String reservedStock);

    void setAttribute(String reservedStock, Map<Long, Integer> reservedStock1);
}
