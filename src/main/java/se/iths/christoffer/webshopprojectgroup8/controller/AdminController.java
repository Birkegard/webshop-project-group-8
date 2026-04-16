package se.iths.christoffer.webshopprojectgroup8.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.iths.christoffer.webshopprojectgroup8.model.Product;
import se.iths.christoffer.webshopprojectgroup8.service.ProductService;

@Controller
public class AdminController {
    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        if (!model.containsAttribute("product")) {
            model.addAttribute("product", new Product());
        }
        model.addAttribute("products", productService.getAllProducts());
        return "admin";
    }

    @PostMapping("/admin/products")
    public String createProduct(@Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("products", productService.getAllProducts());
            return "admin";
        }
        productService.createProduct(product);
        return "redirect:/admin";
    }
}
