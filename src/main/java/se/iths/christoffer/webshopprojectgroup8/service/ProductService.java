package se.iths.christoffer.webshopprojectgroup8.service;


import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import se.iths.christoffer.webshopprojectgroup8.model.Product;
import se.iths.christoffer.webshopprojectgroup8.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public List<Product> getAllProductsByCategoryOrderByName(String category) {
        return productRepository.findByCategoryOrderByName(category);
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, Product updated) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existing.setName(updated.getName());
        existing.setCategory(updated.getCategory());
        existing.setPrice(updated.getPrice());
        existing.setImageURL(updated.getImageURL());

        return productRepository.save(existing);
    }

}
