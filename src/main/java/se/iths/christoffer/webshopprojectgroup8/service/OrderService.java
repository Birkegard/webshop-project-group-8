package se.iths.christoffer.webshopprojectgroup8.service;


import org.springframework.stereotype.Service;
import se.iths.christoffer.webshopprojectgroup8.cart.Cart;
import se.iths.christoffer.webshopprojectgroup8.cart.CartItem;
import se.iths.christoffer.webshopprojectgroup8.model.Order;
import se.iths.christoffer.webshopprojectgroup8.model.OrderItem;
import se.iths.christoffer.webshopprojectgroup8.repository.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

    }

    public Order createOrder(String username, Cart cart){
        Order order = new Order();
        order.setUsername(username);
        order.setOrderDate(LocalDate.now());

        List<OrderItem> orderItems = mapCartItems(cart);
        order.setOrderItems(orderItems);

        order.setTotalPrice(cart.getTotalPrice());

        Order savedOrder = orderRepository.save(order);

        cart.clear();

        return savedOrder;
    }

    private List<OrderItem> mapCartItems(Cart cart) {
        List<OrderItem> items = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {

            OrderItem item = new OrderItem();
            item.setProductName(cartItem.getProductName());
            item.setPrice(cartItem.getPrice());
            item.setQuantity(cartItem.getQuantity());

            items.add(item);
        }

        return items;
    }

    public List<Order> getOrdersByUsername(String username) {
        return orderRepository.findByUsernameOrderByOrderDateDesc(username);
    }





}
