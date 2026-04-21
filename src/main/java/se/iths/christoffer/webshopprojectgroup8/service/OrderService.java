package se.iths.christoffer.webshopprojectgroup8.service;


import org.springframework.stereotype.Service;
import se.iths.christoffer.springmessenger.model.Email;
import se.iths.christoffer.springmessenger.service.MessageService;
import se.iths.christoffer.webshopprojectgroup8.cart.Cart;
import se.iths.christoffer.webshopprojectgroup8.cart.CartItem;
import se.iths.christoffer.webshopprojectgroup8.model.Order;
import se.iths.christoffer.webshopprojectgroup8.model.OrderItem;
import se.iths.christoffer.webshopprojectgroup8.repository.OrderRepository;

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

    public Order createOrder(String username, Cart cart) {
        if (cart == null || cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }

        Order order = new Order();
        order.setUsername(username);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalPrice(cart.getTotalPrice());

        List<OrderItem> orderItems = mapCartItems(cart.getItems());
        order.setOrderItems(orderItems);

        Order savedOrder = orderRepository.save(order);

        sendOrderConfirmation(savedOrder);

        cart.clear();

        return savedOrder;
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

    private void sendOrderConfirmation(Order order) {
        StringBuilder message = new StringBuilder();
        message.append("Thank you for your order ").append(order.getUsername()).append("!\n");
        message.append("Here are your ordered products:\n");

        for (OrderItem orderItem : order.getOrderItems()) {
            message.append(orderItem.getProductName())
                    .append(" x ")
                    .append(orderItem.getQuantity())
                    .append(" : ")
                    .append(orderItem.getPrice())
                    .append("\n");
        }
        message.append("\nTotal: ").append(order.getTotalPrice());

        Email email = new Email();
        email.setRecipient(order.getUsername());
        email.setSubject("Thank you for your order at CAYA Clothing!");
        email.setMessage(message.toString());

        messageService.send(email);
    }

    public List<Order> getOrdersByUsername(String username) {
        return orderRepository.findByUsernameOrderByOrderDateDesc(username);
    }
}
