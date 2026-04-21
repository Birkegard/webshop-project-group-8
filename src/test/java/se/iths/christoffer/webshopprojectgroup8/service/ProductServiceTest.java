package se.iths.christoffer.webshopprojectgroup8.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.christoffer.webshopprojectgroup8.model.Product;
import se.iths.christoffer.webshopprojectgroup8.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void getProductById_shouldReturnProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test");
        product.setPrice(BigDecimal.TEN);

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        Product result = productService.getProductById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Test", result.getName());
    }

    @Test
    void createProduct_shouldSaveProduct() {
        Product product = new Product();

        productService.createProduct(product);

        verify(productRepository).save(product);
    }

    @Test
    void deleteProduct_shouldCallRepository() {
        productService.deleteProduct(1L);

        verify(productRepository).deleteById(1L);
    }
}