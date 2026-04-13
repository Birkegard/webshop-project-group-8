package se.iths.christoffer.webshopprojectgroup8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.christoffer.webshopprojectgroup8.cart.Cart;
import se.iths.christoffer.webshopprojectgroup8.model.Product;
import se.iths.christoffer.webshopprojectgroup8.repository.ProductRepository;

@Controller
@RequestMapping("/cart")
@SessionAttributes("cart")
public class CartController {
    private final ProductRepository productRepository;

    public CartController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @ModelAttribute("cart")
    public Cart createCart() {
        return new Cart();
    }

    @GetMapping
    public String showCart(@ModelAttribute("cart") Cart cart, Model model) {
        return "cart";
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable Long id,
                            @ModelAttribute("cart") Cart cart) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        cart.addItem(product);

        return "redirect:/cart";
    }

    @PostMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id,
                                 @ModelAttribute("cart") Cart cart) {
        cart.removeItem(id);
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart(@ModelAttribute("cart") Cart cart) {
        cart.clear();
        return "redirect:/cart";
    }
}
