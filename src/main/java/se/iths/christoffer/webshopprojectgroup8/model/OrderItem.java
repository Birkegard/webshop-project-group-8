package se.iths.christoffer.webshopprojectgroup8.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Integer quantity;

    @DecimalMin("0.01")
    @Column(nullable = false)
    private BigDecimal price;

    public OrderItem() {
    }

    public OrderItem(Long id, String productName,
                     Integer quantity, BigDecimal price) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
}
