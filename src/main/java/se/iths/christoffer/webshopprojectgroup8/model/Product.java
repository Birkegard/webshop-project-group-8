package se.iths.christoffer.webshopprojectgroup8.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String imageURL;

    public Product() {
    }

    public Product(String name, String category,
                   BigDecimal price, String imageURL) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.imageURL = imageURL;
    }
}
