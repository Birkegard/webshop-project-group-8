package se.iths.christoffer.webshopprojectgroup8.service;

import org.springframework.stereotype.Service;
import se.iths.christoffer.springmessenger.model.Email;
import se.iths.christoffer.springmessenger.service.MessageService;
import se.iths.christoffer.webshopprojectgroup8.cart.CartItem;
import se.iths.christoffer.webshopprojectgroup8.model.Order;
import se.iths.christoffer.webshopprojectgroup8.model.OrderItem;
import se.iths.christoffer.webshopprojectgroup8.repository.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MessageService messageService;

    public OrderService(OrderRepository orderRepository, MessageService messageService) {
        this.orderRepository = orderRepository;
        this.messageService = messageService;
    }

    public Order createOrder(String username, List<CartItem> cartItems) {

        if (cartItems == null || cartItems.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }

        Order order = new Order();
        order.setUsername(username);
        order.setOrderDate(LocalDateTime.now());

        List<OrderItem> orderItems = mapCartItems(cartItems);
        order.setOrderItems(orderItems);

        BigDecimal totalPrice = calculateTotalPrice(cartItems);
        order.setTotalPrice(totalPrice);
        
        Email email = new Email();
        email.setRecipient(username);
        email.setSubject("Order confirmation");
        email.setMessage("Thank you for your order! Total: " + totalPrice);

        messageService.send(email);

        return orderRepository.save(order);
    }

    private List<OrderItem> mapCartItems(List<CartItem> cartItems) {
        List<OrderItem> items = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrderItem item = new OrderItem();
            item.setProductName(cartItem.getProductName());
            item.setPrice(cartItem.getPrice());
            item.setQuantity(cartItem.getQuantity());

            items.add(item);
        }

        return items;
    }

    private BigDecimal calculateTotalPrice(List<CartItem> cartItems) {
        BigDecimal total = BigDecimal.ZERO;

        for (CartItem item : cartItems) {
            total = total.add(
                    item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
            );
        }

        return total;
    }

    public List<Order> getOrdersByUsername(String username) {
        return orderRepository.findByUsernameOrderByOrderDateDesc(username);
    }
}