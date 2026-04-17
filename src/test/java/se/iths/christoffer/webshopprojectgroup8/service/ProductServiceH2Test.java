package se.iths.christoffer.webshopprojectgroup8.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import se.iths.christoffer.webshopprojectgroup8.model.Product;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class ProductServiceH2Test {

    @Autowired
    private ProductService productService;

    @Test
    void createAndGetProduct_shouldWork() {
        Product product = new Product();
        product.setName("Phone");
        product.setCategory("Tech");
        product.setPrice(BigDecimal.valueOf(100));
        product.setImageURL("img");

        Product saved = productService.createProduct(product);

        Product found = productService.getProductById(saved.getId());

        assertEquals("Phone", found.getName());
    }
}
