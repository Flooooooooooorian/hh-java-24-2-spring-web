package de.neuefische.java.hhjava242springweb.products;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product with id: " + id + " not found!"));
    }

    public Product saveProduct(Product newProduct) {
        Product productToSave = new Product(UUID.randomUUID().toString(), newProduct.title(), newProduct.price());

        return productRepository.save(productToSave);
    }
}
