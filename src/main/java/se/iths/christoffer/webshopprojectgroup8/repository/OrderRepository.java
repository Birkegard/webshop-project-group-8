package se.iths.christoffer.webshopprojectgroup8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.christoffer.webshopprojectgroup8.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUsernameOrderByName(String username);
}
