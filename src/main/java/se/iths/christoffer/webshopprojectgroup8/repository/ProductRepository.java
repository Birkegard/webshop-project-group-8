package se.iths.christoffer.webshopprojectgroup8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.christoffer.webshopprojectgroup8.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryOrderByName(String category);
}
