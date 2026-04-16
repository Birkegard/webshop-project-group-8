package se.iths.christoffer.webshopprojectgroup8.cart;

import se.iths.christoffer.webshopprojectgroup8.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(Product product) {
        for (CartItem item : items) {
            if (item.getProductId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        items.add(new CartItem(
                product.getId(),
                product.getName(),
                product.getPrice(),
                1
        ));
    }

    public void decreaseQuantity(Long productId) {
        for (CartItem item : items) {
            if (item.getProductId().equals(productId)) {
                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                } else {
                    removeItem(productId);
                }
                return;
            }
        }
    }

    public void removeItem(Long productId) {
        items.removeIf(item -> item.getProductId().equals(productId));
    }

    public BigDecimal getTotalPrice() {
        BigDecimal total = BigDecimal.ZERO;

        for (CartItem item : items) {
            total = total.add(item.getPrice().multiply(
                    BigDecimal.valueOf(item.getQuantity())));
        }
        return total;
    }

    public void clear() {
        items.clear();
    }

}
