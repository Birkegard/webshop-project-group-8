package se.iths.christoffer.webshopprojectgroup8.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Column(nullable = false)
    private LocalDate orderDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {
    }

    public Order(Long id, String username,
                 BigDecimal totalPrice, LocalDate orderDate,
                 List<OrderItem> orderItems) {
        this.id = id;
        this.username = username;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
    }
}
