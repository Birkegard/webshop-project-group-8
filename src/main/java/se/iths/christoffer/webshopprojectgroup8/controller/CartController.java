package se.iths.christoffer.webshopprojectgroup8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.christoffer.webshopprojectgroup8.cart.Cart;
import se.iths.christoffer.webshopprojectgroup8.model.Order;
import se.iths.christoffer.webshopprojectgroup8.model.Product;
import se.iths.christoffer.webshopprojectgroup8.service.OrderService;
import se.iths.christoffer.webshopprojectgroup8.service.ProductService;

import java.security.Principal;

@Controller
@RequestMapping("/cart")
@SessionAttributes("cart")
public class CartController {

    private final ProductService productService;
    private final OrderService orderService;

    public CartController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @ModelAttribute("cart")
    public Cart createCart() {
        return new Cart();
    }

    @GetMapping
    public String showCart(@ModelAttribute("cart") Cart cart, Model model) {
        model.addAttribute("activePage", "cart");
        return "cart";
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable Long id,
                            @ModelAttribute("cart") Cart cart) {

        Product product = productService.getProductById(id);
        cart.addItem(product);

        return "redirect:/cart";
    }

    @PostMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id,
                                 @ModelAttribute("cart") Cart cart) {

        cart.removeItem(id);
        return "redirect:/cart";
    }

    @PostMapping("/decrease/{id}")
    public String decreaseCart(@PathVariable Long id,
                               @ModelAttribute("cart") Cart cart) {

        cart.decreaseQuantity(id);
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart(@ModelAttribute("cart") Cart cart) {
        cart.clear();
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(@ModelAttribute("cart") Cart cart,
                           Model model,
                           Principal principal) {

        String username = principal.getName();

        Order order = orderService.createOrder(username, cart.getItems());
        
        model.addAttribute("order", order);

        cart.clear();

        return "order-confirmation";
    }
}
