package se.iths.christoffer.webshopprojectgroup8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import se.iths.christoffer.webshopprojectgroup8.model.Product;
import se.iths.christoffer.webshopprojectgroup8.service.ProductService;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("activePage", "products");

        return "products";
    }

    @GetMapping("/products/{id}")
    public String getProductById(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);

        return "product-details";
    }
}