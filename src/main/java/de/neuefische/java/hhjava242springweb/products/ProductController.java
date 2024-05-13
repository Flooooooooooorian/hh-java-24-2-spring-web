package de.neuefische.java.hhjava242springweb.products;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<ProductLite> getProduct() {
        return productRepository.findAll()
                .stream()
                .map(p -> new ProductLite(p.id(), p.title()))
                .toList();
    }

    @GetMapping("{productId}")
    public Product getProducts(@PathVariable String productId) {
        return productRepository.findById(productId)
                .orElseThrow();
    }

    @PostMapping()
    public Product postProduct(@RequestBody NewProductDto product) {
        Product productToSave = new Product(UUID.randomUUID().toString(), product.title(), product.price());

        return productRepository.save(productToSave);
    }

}
