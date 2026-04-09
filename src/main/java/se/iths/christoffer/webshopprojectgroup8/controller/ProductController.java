package se.iths.christoffer.webshopprojectgroup8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.iths.christoffer.webshopprojectgroup8.model.Product;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProductController {

    @GetMapping("/products")
    public String products(Model model) {

        List<Product> products = List.of(
                new Product("Product 1", "Category A", new BigDecimal("10.00"), "https://via.placeholder.com/150"),
                new Product("Product 2", "Category B", new BigDecimal("20.00"), "https://via.placeholder.com/150"),
                new Product("Product 3", "Category C", new BigDecimal("30.00"), "https://via.placeholder.com/150")
        );

        model.addAttribute("products", products);

        return "products";
    }
}