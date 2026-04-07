package se.iths.christoffer.webshopprojectgroup8.cart;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartItem {

    private Long productId;
    private String productName;
    private BigDecimal price;
    private int quantity;

    public CartItem() {
    }

    public CartItem(Long productId, String productName,
                    BigDecimal price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }
}
