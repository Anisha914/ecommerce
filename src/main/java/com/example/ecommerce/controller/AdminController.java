package com.example.ecommerce.controller;

public class AdminController {
    @Controller
    @RequestMapping("/admin")
    public class AdminController {

        @Autowired
        private ProductRepository productRepository;

        @GetMapping("/dashboard")
        public String adminDashboard(Model model) {
            model.addAttribute("products", productRepository.findAll());
            return "admin-dashboard";
        }

        @GetMapping("/product/add")
        public String showAddProductForm(Model model) {
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
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

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
        public String editProduct(@PathVariable Long id, Model model) {
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
        public String viewAllOrders(Model model) {
            List<Order> orders = orderRepository.findAll();
            model.addAttribute("orders", orders);
            return "admin-orders";
        }
    }
}
