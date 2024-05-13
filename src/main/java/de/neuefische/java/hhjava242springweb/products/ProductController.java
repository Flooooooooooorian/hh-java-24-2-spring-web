package de.neuefische.java.hhjava242springweb.products;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public List<ProductLite> getProducts() {
        return productService.getAllProducts()
                .stream()
                .map(p -> new ProductLite(p.id(), p.title()))
                .toList();
    }

    @GetMapping("{productId}")
    public Product getProducts(@PathVariable String productId) {
        return productService.getProductById(productId);
    }

    @PostMapping()
    public Product postProduct(@RequestBody NewProductDto newProduct) {
        Product productToSave = new Product(null, newProduct.title(), newProduct.price());

        return productService.saveProduct(productToSave);
    }

}
