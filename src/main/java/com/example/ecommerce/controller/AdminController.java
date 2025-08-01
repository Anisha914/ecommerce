package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.Product;
import jdk.internal.classfile.impl.BufferedCodeBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import static java.nio.file.Files.copy;

public class AdminController<MultipartFile> {
    @Controller
    @RequestMapping("/admin")


        @Autowired
        private ProductRepository productRepository;

        @GetMapping("/dashboard")
        public String adminDashboard(BufferedCodeBuilder.Model model) {
            model.addAttribute("products", productRepository.findAll());
            return "admin-dashboard";
        }

        @GetMapping("/product/add")
        public String showAddProductForm(BufferedCodeBuilder.Model model) {
            model.addAttribute("product", new Product());
            return "add-product";
        }

        @PostMapping("/product/save")
        public String saveProduct(
                @ModelAttribute Product product,
                @RequestParam("imageFile") MultipartFile imageFile
        ) {
            try {
// File system path (outside classpath)
                String uploadDir = "D:\\lnctu\\ecommerce\\src\\main\\resources\\static\\images";
                File uploadFolder = new File(uploadDir);
                if (!uploadFolder.exists()) {
                    uploadFolder.mkdirs(); // create folder if it doesn't exist
                }

// Generate unique file name
                String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();

// Save file
                Path filePath = Paths.get(uploadFolder.getAbsolutePath(), fileName);
                System.out.println(filePath);
                copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

// Store only file name in DB
                product.setImageName(fileName);
                productRepository.save(product);

            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/admin/product/add?error=true";
            }

            return "redirect:/admin/product/add?success=true";
        }

        @GetMapping("/product/edit/{id}")
        public String editProduct(@PathVariable Long id, BufferedCodeBuilder.Model model) {
            Product product = productRepository.findById(id).orElse(null);
            model.addAttribute("product", product);
            return "add-product";
        }

        @GetMapping("/product/delete/{id}")
        public String deleteProduct(@PathVariable Long id) {
            productRepository.deleteById(id);
            return "redirect:/admin/dashboard";
        }


        @Autowired
        private OrderRepository orderRepository;

        @GetMapping("/orders")
        public String viewAllOrders(BufferedCodeBuilder.Model model) {
            List<Order> orders = orderRepository.findAll();
            model.addAttribute("orders", orders);
            return "admin-orders";
        }
    }

