package se.iths.christoffer.webshopprojectgroup8.service;

import org.springframework.stereotype.Service;
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

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(String username, List<CartItem> cartItems) {

        Order order = new Order();
        order.setUsername(username);
        order.setOrderDate(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {

            OrderItem orderItem = new OrderItem();
            orderItem.setProductName(cartItem.getProductName());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());

            orderItems.add(orderItem);

            BigDecimal itemTotal = cartItem.getPrice()
                    .multiply(BigDecimal.valueOf(cartItem.getQuantity()));

            totalPrice = totalPrice.add(itemTotal);
        }

        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
    }
}