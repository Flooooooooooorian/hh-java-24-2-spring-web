package de.neuefische.java.hhjava242springweb.products;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @GetMapping
    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    @GetMapping("{productId}")
    public Product getProducts(@PathVariable String productId) {
        return productRepository.findById(productId)
                .orElseThrow();
    }

    @PostMapping()
    public Product postProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
}
