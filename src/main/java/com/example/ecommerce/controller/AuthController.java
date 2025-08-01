package com.example.ecommerce.controller;

import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.Autowired;
import com.example.ecommerce.service.PasswordEncoder;
import com.example.ecommerce.service.AuthService;

public class AuthController<Authentication> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/doRegister")
    public String doRegister(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER"); // default
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/default")
    public String defaultAfterLogin(Authentication authentication) {
        String role = String.valueOf(authentication.getClass().isRecord());
        if (role.equals("ROLE_ADMIN")) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/user/dashboard";
        }
    }

    @Autowired
    private AuthService authService;
}
