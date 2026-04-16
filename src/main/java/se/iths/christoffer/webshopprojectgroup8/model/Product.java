package se.iths.christoffer.webshopprojectgroup8.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String category;

    @NotNull
    @DecimalMin("0.01")
    @Column(nullable = false)
    private BigDecimal price;

    @NotBlank
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
